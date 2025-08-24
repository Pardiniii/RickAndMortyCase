import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycase.R
import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.databinding.FragmentRickAndMortyHomeBinding
import com.example.rickandmortycase.data.repository.Repository
import com.example.rickandmortycase.di.CharacterViewModelFactory
import com.example.rickandmortycase.ui.CharacterDetailsFragment
import com.example.rickandmortycase.ui.CharacterViewModel
import com.example.rickandmortycase.ui.adapter.CharacterAdapter
import com.example.rickandmortycase.ui.adapter.NavigationAdapter

class CharacterListFragment : Fragment(R.layout.fragment_rick_and_morty_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    lateinit var binding: FragmentRickAndMortyHomeBinding
    private val repository = Repository(RetrofitInstance.api)
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

        //character list
        adapter = CharacterAdapter(requireContext(), emptyList())
        binding.characterListRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.characterListRV.adapter = adapter

        //nav bar
        viewModel.totalPages.observe(viewLifecycleOwner) { pages->
            val pages = (startPage..pages).toList()
            val navAdapter = NavigationAdapter(pages){ page ->
                Toast.makeText(requireContext(), "Pagina $page clicada", Toast.LENGTH_SHORT).show()
                viewModel.fetchCharacters(page)
            }
            binding.recyclerNavigation.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerNavigation.adapter = navAdapter
        }

        viewModel.characters.observe(viewLifecycleOwner){ list ->
            Log.d("DEBUG", "List size = ${list.size}")
            adapter.refresh(list)
        }

        adapter.whenItenClicked = {
            Toast.makeText(requireContext(), "personagem ${it.name} clicado", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CharacterDetailsFragment())
                .addToBackStack(null) // se quiser voltar
                .commit()
        }

        viewModel.fetchCharacters(startPage)

    }

}
