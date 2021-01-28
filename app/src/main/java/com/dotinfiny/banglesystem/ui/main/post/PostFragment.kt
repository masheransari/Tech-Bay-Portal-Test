package com.dotinfiny.banglesystem.ui.main.post

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.dotinfiny.interviewtask.baseClasses.BaseFragment
import com.dotinfiny.banglesystem.Utils.HawkUtil
import com.dotinfiny.banglesystem.Utils.Utils
import com.dotinfiny.banglesystem.app.BaseActivity
import com.dotinfiny.banglesystem.customInterface.OnCustomItemClickListener
import com.dotinfiny.banglesystem.databinding.PostFragmentBinding
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.ui.main.MainActivity
import com.dotinfiny.banglesystem.ui.main.post.adapter.PostAdapter
import com.org.dotinfiny.gamesprime.helpers.RequestID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.post_fragment.*


@AndroidEntryPoint
class PostFragment : BaseFragment() {

    private lateinit var viewModel: PostViewModel
    private lateinit var binding: PostFragmentBinding
    private lateinit var mainActivity: BaseActivity
    private lateinit var adapter: PostAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "onCreateView")
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding = PostFragmentBinding.inflate(inflater)
        binding.post = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        return binding.root
    }

    private fun getDataFromServer() {
        if (Utils.isConnected(requireContext())) {
            viewModel.getData()
        } else {
            mainActivity.showSnackBar(binding.root)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataFromServer()
        adapter = PostAdapter(clickListener)
        recyclerView.adapter = adapter

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            if (it != null && it.size > 0) {
                adapter.submitList(it)
            }
        })

        viewModel.appRepository.resultData.observe(viewLifecycleOwner, Observer {
            if (it.apiType == RequestID.REQ_POST && it.list != null) {
                mainActivity.dismissDialogBox()
                val list: ArrayList<PostModel> = it.list as ArrayList<PostModel>
                if (list.size > 0) {
                    viewModel.updateList(list)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Fragment", "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Fragment", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Fragment", "onStop")
    }

    private var clickListener = object : OnCustomItemClickListener {
        override fun setOnClickListener(data: Any, position: Int, view: View) {
            if (Utils.isConnected(requireContext())) {
                if (data is PostModel) {
                    mSharedViewModel.post = data
                    viewModel.doAction(data, view)
                }
            } else {
                mainActivity.showSnackBar(view)
                if (data is PostModel) {
                    HawkUtil.getInstance(requireContext()).updatePendingEnteries(data.id.toString())
                }
            }
        }
    }


}