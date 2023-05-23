package com.phystech.edu.mipt.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
) {

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val nearest = restaurantDao.getAllNearest()
            val popular = restaurantDao.getAllPopular()
            if (nearest.isEmpty() && popular.isEmpty()) {
                try {
                    val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                        method = HttpMethod.Get
                    }.body<CatalogResponse>()
                    restaurantDao.insertAllNearest(*response.nearest.map {
                        it.mapToNearestRestaurantEntity()
                    }.toTypedArray())
                    restaurantDao.insertAllPopular(*response.popular.map {
                        it.mapToPopularRestaurantEntity()
                    }.toTypedArray())
                    //emit(response)
                } catch (e: Exception) {
                    // Handle error
                }
            }
            emit(
                CatalogResponse(
                    nearest = nearest.map { it.mapToRemoteRestaurant() },
                    popular = popular.map { it.mapToRemoteRestaurant() },
                    commercial = null
                )
            )
        }
    }
}