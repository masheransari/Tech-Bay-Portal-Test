package com.dotinfiny.banglesystem.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.dotinfiny.interviewtask.baseClasses.BaseFragment
import com.dotinfiny.banglesystem.app.BaseActivity
import com.dotinfiny.banglesystem.customInterface.OnCustomItemClickListener
import com.dotinfiny.banglesystem.databinding.PostDetailFragmentBinding
import com.dotinfiny.banglesystem.model.api.CommentModel
import com.dotinfiny.banglesystem.ui.detail.adapter.CommentAdapter
import com.dotinfiny.banglesystem.ui.main.MainActivity
import com.org.dotinfiny.gamesprime.helpers.RequestID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.post_detail_fragment.*

@AndroidEntryPoint
class PostDetailFragment : BaseFragment(), OnCustomItemClickListener {

    private lateinit var viewModel: PostDetailViewModel
    private lateinit var adapter: CommentAdapter
    private lateinit var binding: PostDetailFragmentBinding
    private lateinit var mainActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostDetailViewModel::class.java)
        binding = PostDetailFragmentBinding.inflate(inflater)
        binding.detail = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CommentAdapter(this)
        recycler_view.adapter = adapter
        if (com.dotinfiny.banglesystem.Utils.Utils.isConnected(requireContext())) {
            viewModel.setPost(mSharedViewModel.post)
        } else {
            mainActivity.showSnackBar(binding.root)
        }
        viewModel.appRepository.resultData.observe(viewLifecycleOwner, Observer {
            if (it.apiType == RequestID.REQ_COMMENT) {
                if (it.error == null) {
                    if (it.list != null && it.list!!.size > 0) {
                        adapter.submitList(it.list as ArrayList<CommentModel>)
                    } else {
                        mainActivity.showToast("No Comments Found")
                    }
                } else {
                    mainActivity.showToast("No Comments Found")
                }
            }
        })
    }

    override fun setOnClickListener(data: Any, position: Int, view: View) {

    }

}