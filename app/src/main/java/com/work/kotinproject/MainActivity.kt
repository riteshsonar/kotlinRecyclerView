package com.work.kotinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.work.kotinproject.ViewModel.ListViewModel
import com.work.kotinproject.adapter.UserListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val userAdapter = UserListAdapter(arrayListOf())
    private lateinit var swipeRefreshLayout :SwipeRefreshLayout
   private lateinit var  recyclerView: RecyclerView
    private lateinit var list_error: AppCompatTextView
    private lateinit var loading_view : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout=findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        list_error=findViewById<AppCompatTextView>(R.id.list_error)
        loading_view=findViewById<ProgressBar>(R.id.loading_view)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()
        recyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter= userAdapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing= false
            viewModel.refresh()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.users.observe(this, Observer {
            countries -> countries?.let {
            recyclerView.visibility= View.VISIBLE
                userAdapter.updateUsers(it)
             }
        })
        viewModel.userLoadError.observe(this, Observer {
            isError -> isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility= if (it) View.VISIBLE else View.GONE
                if (it){
                    list_error.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }
}