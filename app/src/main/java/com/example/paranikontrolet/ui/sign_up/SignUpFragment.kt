package com.example.paranikontrolet.ui.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentSignUpBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.utils.Resource
import com.example.paranikontrolet.utils.gone
import com.example.paranikontrolet.utils.showSnackbar
import com.example.paranikontrolet.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeEvent()
    }

    private fun initViews() {
        binding.buttonSignUp.setOnClickListener {
            viewModel.signInWithEmailAndPassword(
                email = binding.editTextEmail.text.toString(),
                name = binding.editTextName.text.toString(),
                password = binding.editTextPassword.text.toString(),
                verifyPassword = binding.editTextVerifyPassword.text.toString()
            )

        }
        binding.textViewSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = false

    }

    private fun observeEvent() {
        viewModel.authResult.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    binding.progressBar.gone()
                    findNavController().navigate(R.id.action_signUpFragment_to_navigation_home)
                }
                is Resource.Error -> {
                    binding.progressBar.gone()
                    requireView().showSnackbar(it.message.toString())

                }
                is Resource.Loading -> {
                    binding.progressBar.visible()
                }
            }
        }

    }
}