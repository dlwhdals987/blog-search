package com.search.blog.search.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties("blog.search")
data class ApiProperties(val kakao: Kakao, val naver: Naver) {
    data class Kakao(val apiUrl: String, val apiKey: String)
    data class Naver(val apiUrl: String, val clientId: String, val clientSecret: String)
}
