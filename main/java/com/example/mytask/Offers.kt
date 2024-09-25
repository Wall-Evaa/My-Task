package com.example.mytask

data class Offers(
    val Message: String,
    val RecordCount: String,
    val Result: List<Result>,
    val Status: Int
)