package com.example.rickandmortycase.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycase.R

class NavigationAdapter(
    private var pages: List<Int>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<NavigationAdapter.NavViewHolder>() {

    private var selectedPage: Int? = -1  //null

//    fun setSelectedPage(page: Int, recyclerView: RecyclerView? = null) {
//        val oldPage = selectedPage
//        selectedPage = page
//        if (oldPage != null) {
//            notifyItemChanged(oldPage)
//        }
//        notifyItemChanged(selectedPage!!)
//
//        recyclerView?.post {
//            val pos = pages.indexOf(page)
//            if (pos != -1) recyclerView.scrollToPosition(pos)
//        }
//    }

    fun setSelectedPage(page: Int){
        val oldIndex = pages.indexOf(selectedPage) // pega índice da página anterior
        selectedPage = page
        val newIndex = pages.indexOf(page)

        if (oldIndex != -1) notifyItemChanged(oldIndex)
        if (newIndex != -1) notifyItemChanged(newIndex)
    }

    fun updatePages(newPages: List<Int>) {
        pages = newPages
        notifyDataSetChanged()
    }

    inner class NavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPage: TextView = itemView.findViewById(R.id.tvPageNumber)

        fun bind(page: Int) {
            tvPage.text = page.toString()

            if (page == selectedPage) {
                tvPage.setTextColor(Color.WHITE)
                tvPage.setBackgroundResource(R.drawable.bg_selected_page)
            } else {
                tvPage.setTextColor(Color.WHITE)
                tvPage.setBackgroundResource(R.drawable.bg_page)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_navigation, parent, false)
        return NavViewHolder(view)
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder.bind(pages[position])
        val page = pages[position]
        holder.tvPage.text = page.toString()
        holder.itemView.setOnClickListener { onClick(page) }
    }

    override fun getItemCount() = pages.size
}
