package com.search.blog.search.utils

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.nio.charset.StandardCharsets


object HttpHeaderUtils {
    fun createJsonMediaType(): HttpHeaders {
        val headers = HttpHeaders()
        headers.setAcceptCharset(listOf(StandardCharsets.UTF_8))
        headers.contentType = MediaType.APPLICATION_JSON
        return headers
    }
}
