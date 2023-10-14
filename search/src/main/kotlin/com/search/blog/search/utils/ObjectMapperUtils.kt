package com.search.blog.search.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import java.io.IOException


@Component
class ObjectMapperUtils(private val objectMapper: ObjectMapper) {
    fun <T> jsonToObject(json: String, clazz: Class<T>): T? {
        if (ObjectUtils.isEmpty(json)) {
            return null
        }
        try {
            return objectMapper.readValue(json, clazz)
        } catch (e: IOException) {
            val stackTraceElements = e.stackTrace
            logger.error("IOException : {}", stackTraceElements[0])
        }
        return null
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ObjectMapperUtils::class.java)
    }
}

