package com.home.translator.login.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Validation<out T : Any>(val data: T) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Validation<*> -> "Validation[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}