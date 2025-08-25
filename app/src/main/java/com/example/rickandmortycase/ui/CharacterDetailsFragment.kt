package com.example.rickandmortycase.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import com.example.rickandmortycase.R
import com.example.rickandmortycase.databinding.CharacterDetailsFragmentBinding
import com.example.rickandmortycase.databinding.FragmentRickAndMortyHomeBinding

class CharacterDetailsFragment : Fragment(R.layout.character_details_fragment) {

    lateinit var _binding: CharacterDetailsFragmentBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CharacterDetailsFragmentBinding.bind(view)

        val name = arguments?.getString("name")
        val status = arguments?.getString("status")
        val imageUrl = arguments?.getString("imageUrl")
        val origin = arguments?.getString("origin")
        val location = arguments?.getString("location")
        val gender = arguments?.getString("gender")
        val species = arguments?.getString("species")

        // Atribuições usando binding
        binding.characterName.text = name
        binding.characterStatus.text = status
        binding.originOfcTV.text = origin
        binding.locationTV.text = location
        binding.genderOfcTV.text = gender
        binding.speciesOfcTV.text = species
        // Imagem com Coil
        binding.characterImage.load(imageUrl)
    }

}