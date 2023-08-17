package com.wbsoft.tstpdmo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wbsoft.tstpdmo.databinding.FragmentLoginBinding
import com.wbsoft.tstpdmo.models.LoginRequest
import com.wbsoft.tstpdmo.networks.NetworkResult
import com.wbsoft.tstpdmo.view.MainActivity
import com.wbsoft.tstpdmo.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get()=_binding!!

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLoginBinding.inflate(inflater,container, false)

        binding.btnSignIn.setOnClickListener {
            val validationResult= validateUserInput()
            if (validationResult.first){
                authViewModel.login(getLoginRequest())
            }
            else{
                Toast.makeText(context,validationResult.second,Toast.LENGTH_SHORT).show()
            }

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObseres()
    }
    private fun getLoginRequest():LoginRequest{
        val mobileNo=binding.mobilNo.text.toString()
        val password=binding.passId.text.toString()
        return LoginRequest(mobileNo,password)
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val loginRequest=getLoginRequest()
        return authViewModel.validateCredentials(loginRequest.mobile,loginRequest.password,true)

       /* binding.edtEmailPhone.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (!isValidMobileNo(s.toString())) {
                    binding.edtEmailPhone.isErrorEnabled = true
                    binding.edtEmailPhone.error = "মোবাইল নম্বর দিন "
                } else if (s.toString().length!=11) {
                    binding.edtEmailPhone.isErrorEnabled = true
                    binding.edtEmailPhone.error = "মোবাইল নম্বর কমপক্ষে ১১ ডিজিট হবে "

                }else {

                    binding.edtEmailPhone.isErrorEnabled = true
                    binding.edtEmailPhone.error = null
                }

            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.edtPassword.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length < 3) {
                    binding.btnSignIn.isEnabled = false
                    binding.edtPassword.isErrorEnabled = true
                    binding.edtPassword.error = "পাসওয়ার্ড কমপক্ষে ৩ ডিজিট লাগবে "
                } else {
                    binding.btnSignIn.isEnabled = true
                    binding.edtPassword.error = null
                    binding.edtPassword.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })*/
    }
    private fun bindObseres() {
        authViewModel.userRepositoryLiveData.observe(viewLifecycleOwner, Observer {

            //binding.pogss.isViibl=fals

            when(it){
                is NetworkResult.Success ->{
                    findNavController().navigate(R.id.action_loginFragment_to_subjectFragment)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    //binding.pogss.isViibl=tu
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}