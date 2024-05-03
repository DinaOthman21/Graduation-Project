package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medisim.data.remote.dto.main.Post
import com.example.medisim.domain.SharedPreferences
import com.example.medisim.domain.repository.ApiServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ApiServicesRepository,
    private val pref: SharedPreferences
            ): ViewModel() {
    private val _postsList = MutableStateFlow(emptyList<Post>())
    val postsList: StateFlow<List<Post>> = _postsList

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        val token = pref.getSharedPreferences("token","")
        viewModelScope.launch(Dispatchers.IO) {
            repo.getPosts(token).collect{
                _postsList.value = it
            }

        }

    }

}