package com.submission.fundamental.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.fundamental.data.response.ItemsItem
import com.submission.fundamental.databinding.FragmentListDetailFollowBinding

class ListDetailFollow : Fragment() {
    private val detailUserViewModel by viewModels<DetailViewModel>()
    private var _binding: FragmentListDetailFollowBinding? = null
    private val binding get() = _binding!!


    private var position: Int = 0
    private var username: String = ""


    companion object {
        const val ARG_POSITION = "key_position"
        const val ARG_USERNAME = "key_name"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListDetailFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        Log.d("usernameFragment", username)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollows.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollows.addItemDecoration(itemDecor)

        detailUserViewModel.isLoading2.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        detailUserViewModel.listFollow.observe(viewLifecycleOwner) {
            setData(it)
        }
// menyatakan position fragment pada tab layout tanpa membuat dua class berbeda
        if (position == 1) {
            detailUserViewModel.getFollowers(username)
        } else {
            detailUserViewModel.getFollowing(username)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFragment.visibility = View.VISIBLE
        } else {
            binding.progressBarFragment.visibility = View.INVISIBLE
        }
    }

    private fun setData(listFollow: List<ItemsItem>) {
        val adapter = ItemAdapter()
        adapter.submitList(listFollow)
        binding.rvFollows.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}