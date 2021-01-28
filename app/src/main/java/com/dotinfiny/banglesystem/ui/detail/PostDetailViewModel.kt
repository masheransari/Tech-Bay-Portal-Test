package com.dotinfiny.banglesystem.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.repository.DatabaseRepository
import com.org.dotinfiny.gamesprime.data.AppRepository
import com.org.dotinfiny.gamesprime.helpers.RequestID

class PostDetailViewModel @ViewModelInject constructor(
    val appRepository: AppRepository,
    val appDatabase: DatabaseRepository
) : ViewModel() {
    private lateinit var post: PostModel

    val title: MutableLiveData<String> = MutableLiveData()
    val body: MutableLiveData<String> = MutableLiveData()

    fun setPost(post: PostModel?) {
        this.post = post!!
        title.value = post.title
        body.value = post.body
        getCommentsFromServer()
    }

    fun getCommentsFromServer() {
        appRepository.createPostRequest(RequestID.REQ_COMMENT, post.id.toString())
    }
}