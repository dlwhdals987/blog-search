package com.search.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication
@ConfigurationPropertiesScan("com.search.blog.search.config")
class BlogApplication

fun main(args: Array<String>) {
	runApplication<BlogApplication>(*args)
}
