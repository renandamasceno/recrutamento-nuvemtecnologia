package com.renan.marvelnuvem.ui.home.view.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.renan.marvelnuvem.api.model.ComicsModel
import com.renan.marvelnuvem.R

class PersonagemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imagem = view.findViewById<ImageView>(R.id.imagePersonagem)
    private val idPersonagem = view.findViewById<TextView>(R.id.txtPesonagem)

    @SuppressLint("SetTextI18n")
    fun bind(personagemModel: ComicsModel) {
        idPersonagem.text = " # ${personagemModel.id}"
        val imagePath = personagemModel.thumbnail?.getImagePath()
        Glide.with(itemView).load(imagePath).into(imagem)
    }
}