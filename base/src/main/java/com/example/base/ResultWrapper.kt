package com.example.base

import java.lang.Exception

/**
 * ResultWrapper is a wrapper class that wraps the result of a API Response before its sent to the ViewModel
 *      Based off how the UI needs to respond to certain Errors or Successes, those cases can be here and then will be observed in the ViewModel
 * */
sealed class ResultWrapper<out T: Any> {
    class Success<out T:Any>(val data: T): ResultWrapper<T>()
    object SuccessNoData: ResultWrapper<Nothing>()
    data class GenericError(val exception: Exception? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}