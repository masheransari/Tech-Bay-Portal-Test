package com.dotinfiny.banglesystem.ui.main.main_fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dotinfiny.banglesystem.ui.main.post.PostFragment
import com.dotinfiny.banglesystem.ui.main.saved.FavouriteFragment


class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return PostFragment()
        } else {
            return FavouriteFragment()
        }
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }
}