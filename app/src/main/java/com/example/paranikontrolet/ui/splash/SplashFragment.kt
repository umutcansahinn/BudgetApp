package com.example.paranikontrolet.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentSplashBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = false

        observeEvent()
    }

    private fun observeEvent() {
        viewModel.isCurrentUserExist.observe(viewLifecycleOwner) {

            if (it != null && it.isEmailVerified) {
                findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }
    }
}