package com.submission.fundamental.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment = ListDetailFollow()
        fragment.arguments = Bundle().apply {
            putInt(ListDetailFollow.ARG_POSITION, position + 1)
            putString(ListDetailFollow.ARG_USERNAME, username)
        }
        return fragment
    }
// Pada function ListDetailFollow sudah disebutkan
// Jika fragment = 1 maka followers jika tidak maka following
// sehingga tidak diperlukan lagi Deklarasi, seperti
//            0 -> fragment = FollowersFragment()
//            1 -> fragment = ProfileFragment()



    override fun getItemCount(): Int {
        return 2
    }
}