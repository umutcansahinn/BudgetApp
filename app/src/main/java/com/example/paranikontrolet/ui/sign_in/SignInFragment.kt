package com.example.paranikontrolet.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paranikontrolet.R
import com.example.paranikontrolet.databinding.FragmentSignInBinding
import com.example.paranikontrolet.ui.base.BaseFragment
import com.example.paranikontrolet.utils.Resource
import com.example.paranikontrolet.utils.gone
import com.example.paranikontrolet.utils.showSnackbar
import com.example.paranikontrolet.utils.visible
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
                is Resource.Success -> {
                    binding.progressBar.gone()
                    findNavController().navigate(R.id.action_signInFragment_to_navigation_home)
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
        }
        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = false
    }
}