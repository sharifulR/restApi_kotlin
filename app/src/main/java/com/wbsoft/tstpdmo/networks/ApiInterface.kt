package com.wbsoft.tstpdmo.networks

import com.wbsoft.tstpdmo.models.LoginRequest
import com.wbsoft.tstpdmo.models.LoginResponse
import com.wbsoft.tstpdmo.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


}