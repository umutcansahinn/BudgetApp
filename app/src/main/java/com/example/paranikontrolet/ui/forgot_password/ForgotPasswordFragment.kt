package com.example.paranikontrolet.ui.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentForgotPasswordBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.forgotPassword.observe(viewLifecycleOwner) {
            when(it) {
                is ForgotPasswordState.PasswordSuccess-> {
                    requireView().showSnackbar(it.message)
                }
                is ForgotPasswordState.PasswordFailure -> {
                    requireView().showSnackbar(it.message)
                }
            }
        }
    }

    private fun initViews() {
        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = true

        binding.buttonForgotPassword.setOnClickListener {
            val email = binding.editTextForgotPassword.text.toString()
            viewModel.forgotPassword(email)

            findNavController().navigate(R.id.action_forgotPassword_to_navigation_sign_in)
        }
    }
}