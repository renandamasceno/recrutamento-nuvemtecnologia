package com.renan.marvelnuvem.ui.informations

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.renan.marvelnuvem.R
import com.renan.marvelnuvem.api.model.DatesModel
import com.renan.marvelnuvem.api.model.ThumbnailModel
import com.renan.marvelnuvem.api.repository.MarvelRepository
import com.renan.marvelnuvem.ui.home.viewmodel.ComicsViewModel
import java.util.*

class InformationsActivity : AppCompatActivity() {

    private lateinit var _viewModel: ComicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informations)

        val thumbnail = getDadosComics()
        viewModelProvider()
        goBack()
        showLoading(false)
    }


    @SuppressLint("SetTextI18n")
    private fun getDadosComics(): String? {
        val id = intent.getStringExtra("COMICS_ID")
        val titulo = intent.getStringExtra("COMICS_TITLE")
        val descricao = intent.getStringExtra("COMICS_DESCRIPTION")
        val paginas = intent.getStringExtra("COMICS_PAGES")
        val dataPublicacao = intent.getStringExtra("COMICS_EDITION")
        val preco = intent.getStringExtra("COMICS_PRECO")
        val thumbnail = intent.getStringExtra("COMICS_THUMBNAIL")
        val imagem = intent.getStringExtra("COMICS_IMAGEM")


        findViewById<TextView>(R.id.txtTitleDetalhes).text = titulo
        val txtDescription = findViewById<TextView>(R.id.txtDescription)
        val imageFullDetalhes = findViewById<ImageView>(R.id.imageFullDetalhes)

        if(descricao != null){
            txtDescription.text = descricao
        }
        if (dataPublicacao != null){
            for (date in dataPublicacao as List<DatesModel>){
                val calendar = Calendar.getInstance()
                calendar.time = date.data!!
            }
        }

        if (imagem != null) {
            Glide.with(this)
                .load(
                    (imagem as List<ThumbnailModel>)[(imagem as List<ThumbnailModel>).size - 1]
                        .getImagePath("imageFullDetalhes")
                )
                .into(imageFullDetalhes)
        }

        Glide.with(this).load(thumbnail).into(findViewById<ImageView>(R.id.imageMiniDetalhes))

        return thumbnail
    }

    private fun viewModelProvider() {
        _viewModel = ViewModelProvider(
            this,
            ComicsViewModel.ComicViewModelFactory(MarvelRepository())
        ).get(ComicsViewModel::class.java)
    }

    private fun goBack() {
        val goBackHome = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarDetalhes)
        goBackHome.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val viewLoading = findViewById<View>(R.id.loading)
        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

}