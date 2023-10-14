package com.search.blog.search.config

import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class RestTemplateConfiguration {
    @Bean
    fun kakaoRestTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .requestFactory { httpRequestFactory() }
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(3))
            .interceptors()
            .build()
    }

    @Bean
    fun naverRestTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .requestFactory { httpRequestFactory() }
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(3))
            .build()
    }

    @Bean
    fun httpRequestFactory(): HttpComponentsClientHttpRequestFactory {
        val closeableHttpClient = HttpClientBuilder.create()
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(5)
            .setConnectionTimeToLive(30, TimeUnit.SECONDS)
            .evictIdleConnections(30, TimeUnit.SECONDS)
            .evictExpiredConnections()
            .build()
        val factory = HttpComponentsClientHttpRequestFactory()
        factory.setHttpClient(closeableHttpClient)
        return factory
    }

}