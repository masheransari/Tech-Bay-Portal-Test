package com.dotinfiny.banglesystem.ui.main.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dotinfiny.banglesystem.R
import com.dotinfiny.banglesystem.ui.main.main_fragment.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view_pager.setAdapter(createMainTabAdapter())
        TabLayoutMediator(tabs, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Post"
                    }
                    1 -> {
                        tab.text = "Favourite"
                    }
                }
            }).attach()
    }

    private fun createMainTabAdapter(): ViewPagerAdapter? {
        return ViewPagerAdapter(childFragmentManager, lifecycle)
    }
}