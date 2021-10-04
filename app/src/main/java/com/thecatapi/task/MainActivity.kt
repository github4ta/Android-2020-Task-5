package com.thecatapi.task

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.thecatapi.task.adapter.CatAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val itemAdapter = CatAdapter()
    private val catViewModel by viewModels<CatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        catViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })

        recyclerView.viewTreeObserver
            .addOnScrollChangedListener(OnScrollChangedListener {
                if (!recyclerView.canScrollVertically(1)) {
                    // bottom of scroll view
                    println("bottom of scroll view")
                    //catViewModel.upLoadItems()
                }
                if (!recyclerView.canScrollVertically(-1)) {
                    // top of scroll view
                    println("top of scroll view")
                }
            })
    }
}