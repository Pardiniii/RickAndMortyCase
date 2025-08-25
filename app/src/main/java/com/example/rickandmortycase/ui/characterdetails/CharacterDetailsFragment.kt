package com.example.rickandmortycase.ui.characterdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import com.example.rickandmortycase.R
import com.example.rickandmortycase.databinding.CharacterDetailsFragmentBinding

class CharacterDetailsFragment : Fragment(R.layout.character_details_fragment) {
    private lateinit var _binding: CharacterDetailsFragmentBinding
    val binding get() = _binding

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CharacterDetailsFragmentBinding.bind(view)

        setUpView()
    }

    private fun setUpView() {
        val name = arguments?.getString("name")
        val status = arguments?.getString("status")
        val imageUrl = arguments?.getString("imageUrl")
        val origin = arguments?.getString("origin")
        val location = arguments?.getString("location")
        val gender = arguments?.getString("gender")
        val species = arguments?.getString("species")

        with(binding) {
            characterName.text = name
            characterStatus.text = status
            originOfcTV.text = origin
            locationTV.text = location
            genderOfcTV.text = gender
            speciesOfcTV.text = species
            characterImage.load(imageUrl)

            when (gender?.lowercase()) {
                "male" -> genderIMG.setImageResource(R.drawable.maleicon)
                "female" -> genderIMG.setImageResource(R.drawable.femaleicon)
                else -> genderIMG.setImageResource(R.drawable.geneticsicon)
            }

            val bgDrawable = binding.statusBackground.background.mutate()
            when (binding.characterStatus.text.toString()) {
                "Alive" -> bgDrawable.setTint(requireContext().getColor(R.color.green))
                "Dead" -> bgDrawable.setTint(requireContext().getColor(R.color.red))
                else -> bgDrawable.setTint(requireContext().getColor(R.color.white))
            }
            binding.statusBackground.background = bgDrawable
        }
    }
}
