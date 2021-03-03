package com.renan.marvelnuvem.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renan.marvelnuvem.R
import com.renan.marvelnuvem.api.model.ComicsModel

class PersonagemAdapter(private val dataSet: List<ComicsModel>, private val listener: (ComicsModel) -> Unit) :
    RecyclerView.Adapter<PersonagemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_home, parent, false)
        return PersonagemViewHolder(view)
    }

    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)

        holder.itemView.setOnClickListener { listener(item) }
    }
}