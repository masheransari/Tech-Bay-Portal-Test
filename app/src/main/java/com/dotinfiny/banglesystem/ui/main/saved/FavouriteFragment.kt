package com.dotinfiny.banglesystem.ui.main.saved

import android.content.Context
import android.os.Bundle
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
import com.dotinfiny.banglesystem.databinding.FavouriteFragmentBinding
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.model.local.DbPostModel
import com.dotinfiny.banglesystem.ui.main.MainActivity
import com.dotinfiny.banglesystem.ui.main.saved.adapter.FavouriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.favourite_fragment.*

@AndroidEntryPoint
class FavouriteFragment : BaseFragment(), OnCustomItemClickListener {

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var adapter: FavouriteAdapter
    private lateinit var mainActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        binding = FavouriteFragmentBinding.inflate(inflater)
        binding.favourite = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavouriteAdapter(this)
        recycler_view.adapter = adapter

        viewModel.dbPosts.observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                adapter.submitList(it)
            }
        })
    }

    override fun setOnClickListener(data: Any, position: Int, view: View) {
        if (Utils.isConnected(requireContext())) {
            if (data is DbPostModel) {
                mSharedViewModel.post = Utils.getPostModel(data)
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