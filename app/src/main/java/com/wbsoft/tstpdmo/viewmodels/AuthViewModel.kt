package com.wbsoft.tstpdmo.viewmodels

import android.text.TextUtils
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbsoft.tstpdmo.models.LoginRequest
import com.wbsoft.tstpdmo.models.LoginResponse
import com.wbsoft.tstpdmo.networks.ApiInterface
import com.wbsoft.tstpdmo.networks.NetworkResult
import com.wbsoft.tstpdmo.viewmodels.repositorys.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){

    val userRepositoryLiveData:LiveData<NetworkResult<LoginResponse>>
    get() = userRepository.loginResponseLiveData

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch{
            userRepository.login(loginRequest)

        }
    }

    fun validateCredentials(mobile:String,password:String, isLogin:Boolean): Pair<Boolean,String>{
        var result=Pair(true,"")
        if (!isLogin && TextUtils.isEmpty(mobile)|| TextUtils.isEmpty(password)){
            result=Pair(false,"Please provide the credential")
        }
        else if (password.length<=3){
            result=Pair(false,"Password length should be getter than 3")

        }
        return result

    }


}