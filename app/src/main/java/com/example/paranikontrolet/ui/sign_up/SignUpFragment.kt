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
import com.example.paranikontrolet.utils.*
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

            binding.editTextEmail.text?.clear()
            binding.editTextName.text?.clear()
            binding.editTextPassword.text?.clear()
            binding.editTextVerifyPassword.text?.clear()

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
                is SignUpUiState.SendEmailIsSuccess -> {
                    requireView().showSnackbarWithButton(it.value) {
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    }
                }
                is SignUpUiState.SendEmailIsFailure-> {
                    requireView().showSnackbar(it.value)
                }
                is SignUpUiState.SignUpIsSuccess-> {
                    binding.progressBar.gone()
                    viewModel.saveUser(
                        getFirebaseUserUid = it.result.user?.uid!!,
                        email = binding.editTextEmail.text.toString(),
                        name = binding.editTextName.text.toString(),
                        password = binding.editTextPassword.text.toString()
                    )
                }
                is SignUpUiState.SignUpIsFailure -> {
                    binding.progressBar.gone()
                    requireView().showSnackbar(it.value)
                }
                is SignUpUiState.Loading-> {
                    if(it.visibility) {
                        binding.progressBar.visibility = View.VISIBLE
                    }else {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }
}