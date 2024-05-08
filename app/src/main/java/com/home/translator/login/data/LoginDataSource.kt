package com.home.translator.login.data

import com.home.translator.login.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser: LoggedInUser?
            if (usernameValidation(username, password)) {
                fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Translator Application")
                return Result.Success(fakeUser)
            } else {
                fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Enter Valid User Credentials")
                return Result.Validation(fakeUser)
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    private fun usernameValidation(username: String, password: String): Boolean {
        return username == "user@gmail.com" && password == "user"
    }
}