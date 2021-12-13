package com.sebrahimi.restapi

import com.sebrahimi.restapi.dto.ApiResponseDTO
import retrofit2.Response

sealed class ResponseWrapper<out T> {
    data class Success<T>(val data: T) : ResponseWrapper<T>()
    data class Failure(val error: Exception) : ResponseWrapper<Nothing>()

    companion object {
        fun <K : Any> processRetrofitResponsePaged(retrofitResponse: Response<ApiResponseDTO<K>>): ResponseWrapper<ApiResponseDTO<K>> {
            return if (retrofitResponse.isSuccessful)
                if (retrofitResponse.body() != null) processServerResponse(retrofitResponse.body()!!)
                else handleHttpError(retrofitResponse.code(), "Null Response Body")
            else handleHttpError(retrofitResponse.code(), retrofitResponse.message())
        }

        fun <K : Any> processRetrofitResponse(retrofitResponse: Response<K>): ResponseWrapper<K> {
            return if (retrofitResponse.isSuccessful)
                if (retrofitResponse.body() != null) Success(retrofitResponse.body()!!)
                else handleHttpError(retrofitResponse.code(), "Null Response Body")
            else handleHttpError(retrofitResponse.code(), retrofitResponse.message())
        }

        private fun <K : Any> processServerResponse(body: ApiResponseDTO<K>): ResponseWrapper<ApiResponseDTO<K>> {
            //TODO Check the response body
            return Success(body)
        }

        // TODO
        private fun handleHttpError(code: Int, message: String?): Failure {
            var errorMessage = if (code >= 500) "Server Side\nError Code: $code"
            else if (code >= 400) "Error Code: $code"
            else "Unknown Error\nError Code: $code"
            message?.let {
                errorMessage += "\nDetail: $message"
            }
            return Failure(Exception(errorMessage))
        }
    }
}
