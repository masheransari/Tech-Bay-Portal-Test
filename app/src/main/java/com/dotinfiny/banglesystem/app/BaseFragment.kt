package app.dotinfiny.interviewtask.baseClasses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dotinfiny.banglesystem.helpers.SharedViewModel

abstract class BaseFragment : Fragment() {

    protected lateinit var mSharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
}
