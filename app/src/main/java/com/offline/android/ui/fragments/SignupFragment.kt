package com.offline.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.offline.android.R
import kotlinx.android.synthetic.main.fragment_register.*
import com.offline.android.di.auth.state.RegistrationFields
import com.offline.android.di.auth.state.AuthStateEvent.*

import java.util.Observer

class SignupFragment : BaseAuthFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_button.setOnClickListener {
            register()
        }
        subscribeObservers()
    }

    private fun register() {
        viewModel.setStateEvent(
            RegisterAttemptEvent(
                input_email.text.toString(),
                input_username.text.toString(),
                input_password.text.toString(),
                input_password_confirm.text.toString(),
                "123",
                "1234"
            )
        )
    }


    fun subscribeObservers() {

        viewModel.viewState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.registrationFields?.let {
                it.registration_email?.let { input_email.setText(it) }
                it.registration_username?.let { input_username.setText(it) }
                it.registration_password?.let { input_password.setText(it) }
                it.registration_confirm_password?.let {
                    input_password_confirm.setText(it)
                }
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setRegistrationFields(
            RegistrationFields(
                input_email.text.toString(),
                input_username.text.toString(),
                input_password.text.toString(),
                input_password_confirm.text.toString()
            )
        )
    }
}


