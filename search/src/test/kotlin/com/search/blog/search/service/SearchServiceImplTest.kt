package com.search.blog.search.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
class SearchServiceImplTest() {

    @Autowired
    private lateinit var searchServiceImpl: SearchServiceImpl

    @Test
    fun searchBlog() {
        searchServiceImpl.searchBlog("11")
        println(111)
    }
}