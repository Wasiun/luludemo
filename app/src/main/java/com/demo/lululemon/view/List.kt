package com.demo.lululemon.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.lululemon.R
import com.demo.lululemon.adapter.ItemListAdapter
import com.demo.lululemon.databinding.FragmentListBinding
import com.demo.lululemon.viewmodel.MainViewModel


class List : Fragment(R.layout.fragment_list) {
    private lateinit var binding: FragmentListBinding
    private val viewModel by activityViewModels<MainViewModel>()
    private val mAdapter by lazy {
        ItemListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        binding.addBtn.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<AddFragment>(R.id.container_view)
            }
        }

        binding.alphabetBtn.setOnClickListener {
            viewModel.getList(MainViewModel.Companion.Sort.ALPHABETS)
            binding.alphabetBtn.apply {
                background = ResourcesCompat.getDrawable(resources, R.drawable.filled, null)
                setTextColor(Color.WHITE)
            }
            binding.timeBtn.apply {
                background = ResourcesCompat.getDrawable(resources, R.drawable.border, null)
                setTextColor(resources.getColor(R.color.luluprimary))
            }
        }

        binding.timeBtn.setOnClickListener {
            viewModel.getList(MainViewModel.Companion.Sort.ENTRY)
            binding.timeBtn.apply {
                background = ResourcesCompat.getDrawable(resources, R.drawable.filled, null)
                setTextColor(Color.WHITE)
            }
            binding.alphabetBtn.apply {
                background = ResourcesCompat.getDrawable(resources, R.drawable.border, null)
                setTextColor(resources.getColor(R.color.luluprimary))
            }
        }

        setupRV()
        viewModel.getList(MainViewModel.Companion.Sort.ALPHABETS)
        viewModel.list.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }
    }

    private fun setupRV() {
        binding.rvList.apply {
            adapter = mAdapter
            val lm = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            layoutManager = lm
            val itemDecoration = DividerItemDecoration(
                this.getContext(),
                lm.getOrientation()
            )
            addItemDecoration(itemDecoration)
        }
    }
}
