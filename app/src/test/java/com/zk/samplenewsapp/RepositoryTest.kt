package com.zk.samplenewsapp

import com.zk.samplenewsapp.repository.NewsApi
import com.zk.samplenewsapp.repository.Repository
import kotlinx.coroutines.runBlocking
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.zk.samplenewsapp.model.Article
import com.zk.samplenewsapp.model.Articles
import com.zk.samplenewsapp.model.Source
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException

@RunWith(JUnit4::class)
class RepositoryTest {

    private lateinit var newsApi: NewsApi
    private lateinit var repository: Repository

    private val articles = Articles(
        listOf(Article(
            Source("1","1"),
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1")),"ok", 1)

    @Before
    fun setUp() {
        newsApi = mock()
        val mockException: HttpException = mock()
        whenever(mockException.code()).thenReturn(401)
        runBlocking {
            whenever(newsApi.getNews()).thenReturn(articles)
        }
        repository = Repository(newsApi)
    }

    @Test
    fun `test getNews then articles is returned`() {
        runBlocking {
            assertEquals(articles, repository.getNews())
        }
    }
}