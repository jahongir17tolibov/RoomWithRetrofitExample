package com.example.roomwithretrofitexample.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomwithretrofitexample.R
import com.example.roomwithretrofitexample.databinding.FragmentMainBinding
import com.example.roomwithretrofitexample.ui.adapters.MainAdapter
import com.example.roomwithretrofitexample.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.roomwithretrofitexample.models.Result

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var fragmentMainBinding: FragmentMainBinding? = null
    private lateinit var binding: FragmentMainBinding

    private val cryAdapter by lazy { MainAdapter() }
    private val viewModel by viewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

        initLiveData()
        initLoadData()
        initRecyc()

        binding.swipeContainer.setOnRefreshListener {
            initLoadData()
        }


    }

    private fun initLoadData() {
        viewModel.fetchCryptos()
    }

    private fun initLiveData() {
        viewModel.cryptoList.observe(requireActivity()) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.data?.let {
                        cryAdapter.submitList(it)
                    }
                    binding.swipeContainer.isRefreshing = false
                }

                Result.Status.LOADING -> {
                    binding.swipeContainer.isRefreshing = true
                }

                Result.Status.ERROR -> {
                    result?.message?.let {
                        Toast.makeText(
                            requireContext(),
                            "Check your internet connection!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    binding.swipeContainer.isRefreshing = false
                }
            }
        }
    }

    private fun initRecyc() {
        binding.mainRecyc.run {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            adapter = cryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }

}