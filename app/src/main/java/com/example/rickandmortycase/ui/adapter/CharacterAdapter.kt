package com.example.rickandmortycase.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortycase.R
import com.example.rickandmortycase.databinding.CharacterCardBinding
import com.example.rickandmortycase.data.model.Character


class CharacterAdapter(
    private val context: Context,
    characters: List<Character> = emptyList(),
    var whenItenClicked: (character: Character) -> Unit = {}
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var characters = characters.toMutableList()

    inner class ViewHolder(private val binding: CharacterCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var character: Character

        init {
            itemView.setOnClickListener {
                if (::character.isInitialized) {
                    whenItenClicked(character)
                }

            }
        }

        fun bind(character: Character) {
            this.character = character
            val name = binding.characterNameTV
            name.text = character.name
            val status = binding.characterStatusTV
            status.text = character.status
            when (character.status) {
                "Alive" -> status.setTextColor(ContextCompat.getColor(status.context, R.color.green))
                "Dead" -> status.setTextColor(ContextCompat.getColor(status.context, R.color.red))
                else -> status.setTextColor(ContextCompat.getColor(status.context, R.color.black))
            }
            val image = binding.characterIMG
            image.load(character.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CharacterCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    fun refresh(characters: List<Character>) {
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

}
