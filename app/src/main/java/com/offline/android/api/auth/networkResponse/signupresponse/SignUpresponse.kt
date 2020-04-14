package com.offline.android.api.auth.networkResponse.signupresponse

data class SignUpresponse(
	val success: Boolean? = null,
	val otp: String? = null,
	val message: String? = null,
	val user: User? = null
)
