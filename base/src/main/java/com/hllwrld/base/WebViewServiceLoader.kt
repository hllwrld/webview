package com.hllwrld.base

import java.util.*

object WebViewServiceLoader {

    fun <S> loadService(service : Class<S>) : S? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e:Exception) {
            null
        }
    }
}