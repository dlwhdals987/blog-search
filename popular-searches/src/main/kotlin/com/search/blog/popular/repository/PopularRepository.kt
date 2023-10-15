package com.search.blog.popular.repository

import com.search.blog.popular.entity.PopularSearchEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PopularRepository: JpaRepository<PopularSearchEntity, Long> {
    fun findByKeyword(keyword:String):PopularSearchEntity?
    fun findTop10ByOrderByCount():List<PopularSearchEntity>
}