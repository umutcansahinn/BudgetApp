package com.example.paranikontrolet.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.example.paranikontrolet.MainActivity

abstract class BaseFragment: Fragment() {

    protected open var toolbarVisibility = true
    protected open var bottomNavigationViewVisibility = View.VISIBLE

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            val mainActivity =  activity as MainActivity
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toolbarVisibility)
        }
    }
}