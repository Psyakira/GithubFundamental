package com.submission.fundamental.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.submission.fundamental.data.response.GithubResponse
import com.submission.fundamental.data.retrofit.ApiConfig
import com.submission.fundamental.data.response.ItemsItem
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        searchUsers()
    }

    fun searchUsers(q: String = "Syakira") {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUsers(q)
        client.enqueue(object : retrofit2.Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items as List<ItemsItem>?
                } else {
                    Log.d(TAG, "OnFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "OnFailure : ${t.message}")
            }
        })
    }
}