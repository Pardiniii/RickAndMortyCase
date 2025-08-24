import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycase.R
import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.databinding.FragmentRickAndMortyHomeBinding
import com.example.rickandmortycase.data.model.Character
import com.example.rickandmortycase.data.repository.Repository
import com.example.rickandmortycase.di.CharacterViewModelFactory
import com.example.rickandmortycase.ui.CharacterViewModel
import com.example.rickandmortycase.ui.adapter.CharacterAdapter

class CharacterListFragment : Fragment(R.layout.fragment_rick_and_morty_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    lateinit var binding: FragmentRickAndMortyHomeBinding
    private val repository = Repository(RetrofitInstance.api)
    private val viewModel by viewModels<CharacterViewModel> {
        CharacterViewModelFactory(repository)
    }


    // Exemplo de lista mockada (depois você pode trocar pelos dados da API)
//    private val characters = listOf(
//        Character(
//            id = 1,
//            name = "Rick Sanchez",
//            status = "Alive",
//            species = "Human",
//            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
//            gender = "Male",
//            episode = listOf("S01E01", "S01E02", "S01E03")
//        ),
//        Character(
//            id = 2,
//            name = "Morty Smith",
//            status = "Alive",
//            species = "Human",
//            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
//            gender = "Male",
//            episode = listOf("S01E01", "S01E02", "S01E03")
//        ),
//        Character(
//            id = 3,
//            name = "Summer Smith",
//            status = "Alive",
//            species = "Human",
//            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
//            gender = "Female",
//            episode = listOf("S01E01", "S01E04")
//        ),
//        Character(
//            id = 4,
//            name = "Beth Smith",
//            status = "Alive",
//            species = "Human",
//            image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
//            gender = "Female",
//            episode = listOf("S01E01", "S01E06")
//        )
//    )

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
        adapter = CharacterAdapter(requireContext(), emptyList())
        binding.characterListRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.characterListRV.adapter = adapter

        viewModel.characters.observe(viewLifecycleOwner){ list ->
            Log.d("DEBUG", "List size = ${list.size}")
            adapter.refresh(list)
        }
        viewModel.fetchCharacters(page = 20)

    }

}
