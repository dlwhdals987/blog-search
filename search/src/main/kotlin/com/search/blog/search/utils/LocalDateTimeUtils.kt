package com.search.blog.search.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LocalDateTimeUtils {
   companion object{
       fun stringToLocalDateTime(postDate: String?): LocalDateTime {
           val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
           return LocalDate.parse(postDate, formatter).atStartOfDay()
       }
   }
}

