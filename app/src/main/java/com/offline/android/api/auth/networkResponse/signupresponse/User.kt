package com.offline.android.api.auth.networkResponse.signupresponse

data class User(
	val userId: Int? = null,
	val otp: String? = null,
	val phoneNumber: String? = null,
	val userLogId: Int? = null,
	val email: String? = null,
	val status: Int? = null
)
