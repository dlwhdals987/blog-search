package com.search.blog.popular.entity

import com.search.blog.popular.utils.BaseEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
@Table(name = "popular_search", indexes = [Index(name = "idx_popular_search_keyword", columnList = "keyword")])

class PopularSearchEntity(
    @Column(unique = true)
    var keyword: String,
    var count: Int
) : BaseEntity() {

    fun increaseCount() {
        count++
    }

    @PrePersist
    fun prePersist() {
        count = 0
    }

}

