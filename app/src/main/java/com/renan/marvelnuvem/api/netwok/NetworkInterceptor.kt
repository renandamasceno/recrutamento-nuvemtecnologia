package com.renan.marvelnuvem.api.netwok
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val apikey = PUBLIC_KEY

        var request = chain.request()
        val originalHttpUrl = request.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(TS, ts)
            .addQueryParameter(API_KEY, apikey)
            .addQueryParameter(HASH, getHash(ts))
            .build()

        val requestBuilder = request.newBuilder().url(url)
        request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getHash(ts: String) = "${ts}$PRIVATE_KEY$PUBLIC_KEY".md5

    private val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

    companion object {
        private const val TS = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"

        private const val PRIVATE_KEY = "5cc85bab55caf13b4b0b3394e62f88e277e295db"
        const val PUBLIC_KEY = "d0b64dd4d5ffca022bfb79451900c80c"

    }
}



