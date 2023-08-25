package com.wbsoft.tstpdmo.viewmodels.repositorys

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wbsoft.tstpdmo.models.AllClassM
import com.wbsoft.tstpdmo.networks.AllClassAPI
import com.wbsoft.tstpdmo.networks.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class ClassRepository @Inject constructor(private val classApi:AllClassAPI) {

    private val _classLiveData=MutableLiveData<NetworkResult<AllClassM>>()
    val classLiveData:LiveData<NetworkResult<AllClassM>>
        get() = _classLiveData

    suspend fun getAllClass(){

        _classLiveData.postValue(NetworkResult.Loading())
        val response=classApi.getAllClass()

        if (response.isSuccessful && response.body() != null) {
            _classLiveData.postValue(NetworkResult.Success(response.body()!!))

            Log.d(Constraints.TAG, "login: ${response.body()}")
            Log.d("TAG", "getAllClass: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            //_loginResponseLiveData.postValue(NetworkResult.Error(response.message(), response.body()))
            _classLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "login: ${errorObj}")
        } else {
            _classLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }
}