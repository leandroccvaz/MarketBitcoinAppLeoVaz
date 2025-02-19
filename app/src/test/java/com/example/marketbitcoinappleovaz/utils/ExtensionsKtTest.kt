package com.example.marketbitcoinappleovaz.utils

import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.INTERNET_CONNECTION_ERROR
import com.example.marketbitcoinappleovaz.utils.Constants.ApiError.UNKNOWN_ERROR
import io.kotlintest.shouldBe
import org.junit.Test
import java.io.IOException

class ExtensionsKtTest {
    @Test
    fun `toResourceError should return correct message for IOException`() {
        val exception = IOException()
        val result = exception.toResourceError<String>()
        result.message shouldBe INTERNET_CONNECTION_ERROR
    }

    @Test
    fun `toResourceError should return correct message for generic Exception`() {
        val exception = Exception()
        val result = exception.toResourceError<String>()
        result.message shouldBe UNKNOWN_ERROR
    }
}