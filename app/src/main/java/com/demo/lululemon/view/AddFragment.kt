package com.demo.lululemon.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.demo.lululemon.R
import com.demo.lululemon.databinding.FragmentAddBinding
import com.demo.lululemon.viewmodel.MainViewModel

class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val viewModel by activityViewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        binding.saveBtn.setOnClickListener {
            viewModel.addToList(binding.inputEt.text.toString())
            parentFragmentManager.popBackStack()
        }
    }
}