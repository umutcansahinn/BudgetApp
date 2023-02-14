package com.example.paranikontrolet.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentSignInBinding
import com.example.paranikontrolet.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.authResult.observe(viewLifecycleOwner) {
           when(it) {
               is SignInUiState.SignInIsFailure-> {
                    Toast.makeText(context,it.value,Toast.LENGTH_SHORT).show()
               }
               is SignInUiState.VerificationIsFailure-> {
                   Toast.makeText(context,it.value,Toast.LENGTH_SHORT).show()
               }
               is SignInUiState.VerificationIsSuccess-> {
                   Toast.makeText(context,"giris basarılı",Toast.LENGTH_SHORT).show()
                   findNavController().navigate(R.id.action_signInFragment_to_navigation_home)
               }
               is SignInUiState.Loading-> {

               }
           }
        }
    }

    private fun initViews() {
        with(binding) {
            buttonSignIn.setOnClickListener {
                viewModel.signInWithEmailAndPassword(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            }
            textViewSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            }

            textViewForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_sign_in_to_forgotPassword)
            }
        }
        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = false
    }
}