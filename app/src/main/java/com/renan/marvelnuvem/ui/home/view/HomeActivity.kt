package com.renan.marvelnuvem.ui.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renan.marvelnuvem.ui.informations.InformationsActivity
import com.renan.marvelnuvem.R
import com.renan.marvelnuvem.ui.home.viewmodel.ComicsViewModel
import com.renan.marvelnuvem.api.model.ComicsModel
import com.renan.marvelnuvem.api.repository.MarvelRepository
import com.renan.marvelnuvem.ui.home.view.adapter.PersonagemAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var _viewModel: ComicsViewModel
    private lateinit var _listAdapter: PersonagemAdapter
    private var _comics = mutableListOf<ComicsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewGridManager = GridLayoutManager(this, 3)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        setupNavigation()

        setupRecyclerView(recyclerView, viewGridManager)

        viewModelProvider()
        getList()

        showLoading(true)
        setScrollView()
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView?,
        viewGridManager: GridLayoutManager
    ) {
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = viewGridManager
            adapter = _listAdapter
        }
    }

    private fun setupNavigation() {
        _listAdapter = PersonagemAdapter(_comics) {
            val intent = Intent(this@HomeActivity, InformationsActivity::class.java)
            with(intent) {
                putExtra("COMICS_ID", it.id)
                putExtra("COMICS_TITLE", it.titulo)
                putExtra("COMICS_EDITION", it.numeroEdicao)
                putExtra("COMICS_DESCRIPTION", it.descricao)
                putExtra("COMICS_PAGES", it.paginacao)
                putExtra("COMICS_DATE", it.datas.lastIndex)
                putExtra("COMICS_PRECO", it.precos.lastIndex)
                putExtra("COMICS_THUMBNAIL", it.thumbnail?.getImagePath())
                putExtra("COMICS_IMAGEM", it.imagem.size)

                startActivity(this)
            }
        }
    }

    private fun viewModelProvider() {
        _viewModel = ViewModelProvider(
            this,
            ComicsViewModel.ComicViewModelFactory(MarvelRepository())
        ).get(ComicsViewModel::class.java)
    }

    private fun getList() {
        _viewModel.getList().observe({ lifecycle }, {
            _comics.addAll(it)
            _listAdapter.notifyDataSetChanged()
            showLoading(false)
        })
    }

    private fun showLoading(isLoading: Boolean) {
        val viewLoading = findViewById<View>(R.id.loading)
        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

    private fun setScrollView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 6 >= totalItemCount

                    if (totalItemCount > 0 && lastItem) {
                        showLoading(true)
                        _viewModel.nextPage().observe({ lifecycle }, {
                            _comics.addAll(it)
                            _listAdapter.notifyDataSetChanged()
                            showLoading(false)
                        })
                    }
                }
            })
        }
    }

}
