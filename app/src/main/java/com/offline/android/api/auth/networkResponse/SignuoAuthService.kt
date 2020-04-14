package com.offline.android.api.auth.networkResponse

import androidx.lifecycle.LiveData
import com.offline.android.api.auth.networkResponse.signupresponse.SignUpresponse
import com.offline.android.ui.utils.GenericApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignuoAuthService {

    @POST("signup")
    @FormUrlEncoded
    fun  signUpRequest(
        @Field("email") email: String,
        @Field("phone_number") phonenumber: String,
        @Field("password") password: String,
        @Field("longitude") longitude: String,
        @Field("latitude") latitude: String
    ): LiveData<GenericApiResponse<SignUpresponse>>

}