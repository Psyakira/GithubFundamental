package com.submission.fundamental.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.fundamental.data.response.ItemsItem
import com.submission.fundamental.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels <MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
//        supportActionBar?.elevation = 0f
//    }

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvGithub.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvGithub.addItemDecoration(itemDecoration)

        mainViewModel.listUser.observe(this) { listUsers ->
            setUsersData(listUsers)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener{ _, _, _ ->
                    mainViewModel.searchUsers(searchView.text.toString())
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false

                }
        }
    }

    private fun setUsersData(username: List<ItemsItem?>?) {
        val adapter = ItemAdapter()
        adapter.submitList(username)
        binding.rvGithub.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}