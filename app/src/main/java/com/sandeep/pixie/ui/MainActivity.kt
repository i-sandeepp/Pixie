package com.sandeep.pixie.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandeep.pixie.R
import com.sandeep.pixie.adapter.MainAdapter
import com.sandeep.pixie.databinding.ActivityMainBinding
import com.sandeep.pixie.interfaces.RowSelectListener
import com.sandeep.pixie.model.PixieItem
import com.sandeep.pixie.model.PixieResponse
import com.sandeep.pixie.network.ApiResult
import com.sandeep.pixie.ui.dialog.MainDialog
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RowSelectListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val dialog: MainDialog by lazy {
        MainDialog.getInstance()
    }

    private lateinit var mainData: ArrayList<PixieItem>
    private lateinit var mainAdapter: MainAdapter

    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainData = ArrayList()
        mainAdapter = MainAdapter(this, mainData, this)

        binding.mainRecycler.layoutManager = LinearLayoutManager(this)
        binding.mainRecycler.adapter = mainAdapter

        setupListener()
        setupObservers()

        viewModel.getList(2, 3)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun createData(data: PixieResponse) {
        mainData = data
        mainAdapter.updateData(mainData)
        mainAdapter.notifyDataSetChanged()
    }

    private fun setupListener() {

        binding.mainSwipe.setOnRefreshListener {
            if (!isLoading) {
                viewModel.getList(2, 3)
            }
        }
    }

    private fun showDialog(url: String, desc: String) {
        dialog.setContext(url, desc)
        dialog.show(supportFragmentManager, "Dialog")
    }

    private fun setupObservers() {

        viewModel.pixieResponse.observe(this) {
            when (it) {

                is ApiResult.Loading -> {
                    isLoading = true
                    binding.mainRecycler.visibility = View.GONE
                    binding.mainSwipe.isRefreshing = true
                }

                is ApiResult.Failure -> {
                    isLoading = false
                    binding.mainRecycler.visibility = View.VISIBLE
                    binding.mainSwipe.isRefreshing = false
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                }

                is ApiResult.Success -> {
                    isLoading = false
                    binding.mainRecycler.visibility = View.VISIBLE
                    binding.mainSwipe.isRefreshing = false
                    createData(it.data)
                }
            }
        }
    }

    override fun onRowSelected(id: String, url: String, desc: String) {
        showDialog(url, desc)
    }
}