package com.example.marketbitcoinappleovaz.data.remote

import com.example.marketbitcoinappleovaz.BuildConfig
import com.example.marketbitcoinappleovaz.utils.Constants
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test

class ApiKeyInterceptorTest {

    private lateinit var mockChain: Interceptor.Chain
    private lateinit var interceptor: ApiKeyInterceptor

    @Before
    fun setUp() {
        mockChain = mockk(relaxed = true) {
            every { request() } returns Request.Builder()
                .url("http://somehost/somepath")
                .addHeader("foo", "bar")
                .build()

            every { proceed(any()) } answers { invocation ->
                val request = invocation.invocation.args[0] as Request
                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("Test")
                    .body("abc".toResponseBody())
                    .build()
            }
        }
        interceptor = ApiKeyInterceptor()
    }

    @Test
    fun `should add API key to request headers`() {
        // When
        val response = interceptor.intercept(mockChain)

        // Then
        val request = response.request
        request.header(Constants.COIN_API) shouldBe BuildConfig.COIN_API_KEY
    }

}