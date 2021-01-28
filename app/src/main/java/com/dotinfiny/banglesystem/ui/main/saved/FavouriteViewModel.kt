package com.dotinfiny.banglesystem.ui.main.saved

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.dotinfiny.banglesystem.R
import com.dotinfiny.banglesystem.model.local.DbPostModel
import com.dotinfiny.banglesystem.repository.DatabaseRepository
import com.dotinfiny.banglesystem.ui.view_model_factory.BaseViewModel
import com.org.dotinfiny.gamesprime.data.AppRepository
import kotlinx.coroutines.launch

class FavouriteViewModel @ViewModelInject constructor(
    val appRepository: AppRepository,
    val appDatabase: DatabaseRepository
) : BaseViewModel() {

    val dbPosts = appDatabase.getDbPost()

    fun doAction(data: DbPostModel, view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.getMenu().add("Show Comments")
        popupMenu.getMenu().add("unFavourite")
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.title.toString()) {
                "Show Comments" -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_homeFragment_to_postDetailFragment)
                }
                "unFavourite" -> {
                    viewModelScope.launch {
                        appDatabase.removeFromTable(data)
                    }
                    Toast.makeText(view.context, "Remove Successfully", Toast.LENGTH_SHORT).show()
                }

            }
            true
        })
        popupMenu.show()
    }

}