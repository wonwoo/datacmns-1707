package com.example.datacmns1707

import kotlinx.coroutines.flow.Flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@SpringBootApplication
class Datacmns1707Application

fun main(args: Array<String>) {
    runApplication<Datacmns1707Application>(*args)
}

interface BannerRepository : CoroutineCrudRepository<Banner, String> {

    suspend fun deleteById(entityStream: Flow<String>)

}

data class Banner(
    val id: String? = null,
    val name: String
)
