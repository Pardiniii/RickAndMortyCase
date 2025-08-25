import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortycase.R
import com.example.rickandmortycase.data.repository.CharacterRepositoryImpl
import com.example.rickandmortycase.databinding.FragmentRickAndMortyHomeBinding
import com.example.rickandmortycase.di.CharacterViewModelFactory
import com.example.rickandmortycase.ui.CharacterViewModel
import com.example.rickandmortycase.ui.adapter.CharacterAdapter
import com.example.rickandmortycase.ui.adapter.NavigationAdapter
import com.example.rickandmortycase.ui.characterdetails.CharacterDetailsFragment

class CharacterListFragment : Fragment(R.layout.fragment_rick_and_morty_home) {
    private lateinit var adapter: CharacterAdapter
    private lateinit var navAdapter: NavigationAdapter
    lateinit var binding: FragmentRickAndMortyHomeBinding
    private val repository = CharacterRepositoryImpl()
    val startPage = 1
    private val viewModel by activityViewModels<CharacterViewModel> {
        CharacterViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRickAndMortyHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setUpCharacterAdapter()
        setUpObserver()
        goToCharacterDetails()

        if (viewModel.characters.value.isNullOrEmpty()) {
            viewModel.fetchCharacters(viewModel.currentPage.value ?: startPage)
        }
    }

    private fun goToCharacterDetails() {
        adapter.whenItenClicked = { character ->
            val fragment =
                CharacterDetailsFragment().apply {
                    arguments =
                        Bundle().apply {
                            putString("name", character.name)
                            putString("status", character.status)
                            putString("imageUrl", character.image)
                            putString("origin", character.origin?.name)
                            putString("location", character.location?.name)
                            putString("gender", character.gender)
                            putString("species", character.species)
                        }
                }

            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setUpObserver() {
        viewModel.totalPages.observe(viewLifecycleOwner) { total ->
            val pages = (startPage..total).toList()
            setUpNavBar(pages)
        }

        viewModel.currentPage.observe(viewLifecycleOwner) { current ->
            if (::navAdapter.isInitialized) {
                navAdapter.setSelectedPage(current)
            }
        }

        viewModel.characters.observe(viewLifecycleOwner) { list ->
            adapter.refresh(list)
            binding.characterListRV.smoothScrollToPosition(0)
        }
    }

    private fun setUpNavBar(pages: List<Int>) {
        if (!::navAdapter.isInitialized) {
            navAdapter =
                NavigationAdapter(pages) { page ->
                    viewModel.fetchCharacters(page)
                }
            binding.recyclerNavigation.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerNavigation.adapter = navAdapter
        } else {
            navAdapter.updatePages(pages)
        }
    }

    private fun setUpCharacterAdapter() {
        adapter = CharacterAdapter(requireContext(), emptyList())
        binding.characterListRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.characterListRV.adapter = adapter
    }
}
