package com.example.datacmns1707

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class Datacmns1707ApplicationTests(@Autowired private val bannerRepository: BannerRepository) {

    @Test
    fun `not suspend delete test`() {

        runBlocking {
            bannerRepository.deleteAll()

            val saveBanner = bannerRepository.save(Banner(name = "start.spring.io"))

            bannerRepository.deleteAll(flowOf(saveBanner))

            val banners = bannerRepository.findAll().toList()
            assertThat(banners).isEmpty()
        }
    }

    @Test
    fun `suspend delete test`() {

        runBlocking {
            bannerRepository.deleteAll()

            val saveBanner = bannerRepository.save(Banner(name = "start.spring.io"))

            val flowOf = flowOf(saveBanner.id!!)
            bannerRepository.deleteById(flowOf)

            val banners = bannerRepository.findAll().toList()
            assertThat(banners).isEmpty()

        }
    }
}
