package com.dotinfiny.banglesystem.ui.main.post

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.dotinfiny.banglesystem.R
import com.dotinfiny.banglesystem.Utils.Utils
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.repository.DatabaseRepository
import com.dotinfiny.banglesystem.ui.view_model_factory.BaseViewModel
import com.org.dotinfiny.gamesprime.data.AppRepository
import com.org.dotinfiny.gamesprime.helpers.RequestID
import kotlinx.coroutines.launch

class PostViewModel @ViewModelInject constructor(
    val appRepository: AppRepository,
    val appDatabase: DatabaseRepository
) : BaseViewModel() {

    val posts = appDatabase.getPost()

    fun getData() {
        appRepository.createPostRequest(RequestID.REQ_POST, "")
    }

    fun updateList(list: ArrayList<PostModel>) {
        viewModelScope.launch {
            appDatabase.insertNewData(list)
        }
    }

    fun doAction(data: PostModel, view: View) {

        val popupMenu = PopupMenu(view.context, view)
        popupMenu.getMenu().add("Show Comments")
        popupMenu.getMenu().add("Favourite")
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.title.toString()) {
                "Show Comments" -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_homeFragment_to_postDetailFragment)
                }
                "Favourite" -> {
                    viewModelScope.launch {
                        appDatabase.markAsFavourite(Utils.getDbPostModel(data))
                    }
                    Toast.makeText(view.context, "Mark as Favourite", Toast.LENGTH_SHORT).show()
                }

            }
            true
        })
        popupMenu.show()


    }
}