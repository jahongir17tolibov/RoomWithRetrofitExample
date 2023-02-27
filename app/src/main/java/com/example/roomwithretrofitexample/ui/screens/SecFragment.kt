package com.example.roomwithretrofitexample.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomwithretrofitexample.R
import com.example.roomwithretrofitexample.databinding.FragmentSecBinding

class SecFragment : Fragment(R.layout.fragment_sec) {

    private var fragmentSecBinding: FragmentSecBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSecBinding.bind(view)
        fragmentSecBinding = binding

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSecBinding = null
    }

}