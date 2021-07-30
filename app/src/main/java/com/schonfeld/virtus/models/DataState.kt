package com.schonfeld.virtus.models

data class DataState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false
)