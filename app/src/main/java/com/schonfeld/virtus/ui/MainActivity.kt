package com.schonfeld.virtus.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schonfeld.virtus.databinding.ActivityMainBinding
import com.schonfeld.virtus.ui.adapters.HitsAdapter
import com.schonfeld.virtus.ui.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var view: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        bindViews()

        bindViewModel()

        viewModel.getHits()
    }

    private fun bindViewModel() {
        viewModel.state.observe(this, {
            when {
                it.isLoading -> showLoading()
                it.isSuccess -> hideLoading()
                else -> hideLoading()
            }
        })

        viewModel.hits.observe(this, { list ->
            view.rvHits.adapter = HitsAdapter(
                list,
                callbackClick = {
                    startActivity(
                        Intent(this@MainActivity, WebViewActivity::class.java)
                            .putExtra(WebViewActivity.EXTRA_URL, it.url)
                    )
                },
                callbackDelete = {
                    viewModel.deleteHit(it).run {
                        viewModel.getHits()
                    }
                }
            )
        })
    }

    private fun bindViews() {
        view.run {
            rvHits.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getHits()
            }
        }
    }

    private fun hideLoading() {
        view.mainLayout.alpha = 1f
        view.swipeRefreshLayout.isRefreshing = false
    }

    private fun showLoading() {
        view.mainLayout.alpha = 0.2f
        view.swipeRefreshLayout.isRefreshing = true
    }
}