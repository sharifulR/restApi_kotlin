package com.wbsoft.tstpdmo.networks

import com.wbsoft.tstpdmo.models.AllClassM
import com.wbsoft.tstpdmo.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface AllClassAPI {
    @GET(Constants.AllClass_ENDPOINT)
    suspend fun getAllClass(): Response<List<AllClassM>>
}