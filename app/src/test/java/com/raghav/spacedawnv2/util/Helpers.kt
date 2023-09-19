package com.raghav.spacedawnv2.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class Helpers {

    companion object {
        val moshi: Moshi = Moshi.Builder().build()
        inline fun <reified T> getDtoFromJson(jsonString: String): T? {
            val adapter: JsonAdapter<T> =
                moshi.adapter(T::class.java)

            return adapter.fromJson(jsonString)
        }
    }
}
