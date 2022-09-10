package com.example.coolblueassignment.entities

sealed class OperationResult<T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Error<T>(val throwable: Throwable) : OperationResult<T>()
}
