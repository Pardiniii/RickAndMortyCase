import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortycase.R
import com.example.rickandmortycase.data.repository.CharacterRepositoryImpl
import com.example.rickandmortycase.databinding.FragmentRickAndMortyHomeBinding
import com.example.rickandmortycase.di.CharacterViewModelFactory
import com.example.rickandmortycase.ui.characterdetails.CharacterDetailsFragment
import com.example.rickandmortycase.ui.CharacterViewModel
import com.example.rickandmortycase.ui.adapter.CharacterAdapter
import com.example.rickandmortycase.ui.adapter.NavigationAdapter

class CharacterListFragment : Fragment(R.layout.fragment_rick_and_morty_home) {

    private lateinit var adapter: CharacterAdapter
    lateinit var binding: FragmentRickAndMortyHomeBinding
    private val repository = CharacterRepositoryImpl()
    val startPage = 1
    private val viewModel by viewModels<CharacterViewModel> {
        CharacterViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRickAndMortyHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCharacterAdapter()
        setUpObserver()
        goToCharacterDetails()

        viewModel.fetchCharacters(startPage)
    }

    private fun goToCharacterDetails() {
        adapter.whenItenClicked = { character ->
            val fragment = CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("name", character.name)
                    putString("status", character.status)
                    putString("imageUrl", character.image)
                    putString("origin", character.origin?.name)
                    putString("location", character.location?.name)
                    putString("gender", character.gender)
                    putString("species", character.species)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setUpObserver() {
        viewModel.totalPages.observe(viewLifecycleOwner) { pages ->
            val pages = (startPage..pages).toList()
            val navAdapter = NavigationAdapter(pages) { page ->
                Toast.makeText(requireContext(), "Pagina $page clicada", Toast.LENGTH_SHORT).show()
                viewModel.fetchCharacters(page)
            }
            setUpNavBarAdapter(navAdapter)
        }

        viewModel.characters.observe(viewLifecycleOwner) { list ->
            adapter.refresh(list)
        }
    }

    private fun setUpNavBarAdapter(navAdapter: NavigationAdapter) {
        binding.recyclerNavigation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerNavigation.adapter = navAdapter
    }

    private fun setUpCharacterAdapter() {
        adapter = CharacterAdapter(requireContext(), emptyList())
        binding.characterListRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.characterListRV.adapter = adapter
    }
}