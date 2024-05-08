package com.home.translator.login.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val validation: LoggedInUserView? = null,
    val error: Int? = null
)