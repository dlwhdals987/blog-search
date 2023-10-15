package com.search.blog.popular.service.impl

import com.search.blog.popular.dto.PopularKeywordDto
import com.search.blog.popular.entity.PopularSearchEntity
import com.search.blog.popular.repository.PopularRepository
import com.search.blog.popular.service.PopularService
import com.search.blog.search.service.SearchService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@ExtendWith(MockitoExtension::class)
@SpringBootTest
internal class PopularServiceImplTest {
    @Autowired
    private lateinit var popularService: PopularServiceImpl
    @Autowired
    private lateinit var popularRepository: PopularRepository
    @Test
    fun 인기검색어_도메인을_반환한다() {
        // given
        val query = "테스트"
        val count = 1
        val popularSearchEntity = PopularSearchEntity(query, count)
        popularRepository.save(popularSearchEntity)

        // when
        val popularSearch = popularService.findPopularKeywords()

        // then
        assertNotEquals(100, popularSearch.count())
        assertEquals(query, popularSearch[0].keyword)
        assertEquals(1, popularSearch[0].count)
        assertEquals(1, popularSearch.size)
    }

    @Test
    fun 인기검색어_목록을_반환한다() {
        // given
        val fixture: List<PopularSearchEntity> = fixture

        // when
        popularRepository.saveAll(fixture)


        // then
        val popularList = popularRepository.findAll()
        assertNotEquals(100, popularList.size)
        assertEquals(fixture.size, popularList.size)
    }

    private val fixture: List<PopularSearchEntity>
         get() = java.util.List.of<PopularSearchEntity>(
             PopularSearchEntity("java", 3),
             PopularSearchEntity("kotlin", 2),
             PopularSearchEntity("react", 4),
        )
}