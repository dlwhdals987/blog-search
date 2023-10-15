package com.search.blog.popular.repository

import com.search.blog.popular.entity.PopularSearchEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import javax.persistence.LockModeType

interface PopularRepository: JpaRepository<PopularSearchEntity, Long> {
    @Lock(value = LockModeType.OPTIMISTIC)
    fun findByKeyword(keyword:String):PopularSearchEntity?
    fun findTop10ByOrderByCount():List<PopularSearchEntity>
}