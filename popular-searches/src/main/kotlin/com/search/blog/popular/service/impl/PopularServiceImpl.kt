package com.search.blog.popular.service.impl

import com.search.blog.popular.dto.PopularKeywordDto
import com.search.blog.popular.entity.PopularSearchEntity
import com.search.blog.popular.repository.PopularRepository
import com.search.blog.popular.service.PopularService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PopularServiceImpl(private val popularRepository: PopularRepository) : PopularService {

    @Transactional
    override fun increaseKeyword(keyword: String?) {
        if (keyword == null) return
        val popularSearchEntity = popularRepository.findByKeyword(keyword) ?: createPopularSearchEntity(keyword)
        popularSearchEntity.increaseCount()
    }

    override fun findPopularKeywords(): List<PopularKeywordDto> {
        return popularRepository.findTop10ByOrderByCount().map(PopularKeywordDto::from)
    }

    private fun createPopularSearchEntity(keyword: String): PopularSearchEntity {
        val popularSearch = PopularSearchEntity(keyword = keyword, count = 0)
        popularRepository.save(popularSearch)
        return popularSearch
    }
}