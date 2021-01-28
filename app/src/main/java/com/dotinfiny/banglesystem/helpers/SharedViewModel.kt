package com.dotinfiny.banglesystem.helpers

import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.ui.view_model_factory.BaseViewModel

class SharedViewModel : BaseViewModel() {

    var postId: Int = -1
    var post: PostModel? = null
}