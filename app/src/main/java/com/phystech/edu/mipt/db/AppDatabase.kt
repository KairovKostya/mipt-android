package com.phystech.edu.mipt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phystech.edu.mipt.data.NearestRestaurantEntity
import com.phystech.edu.mipt.data.PopularRestaurantEntity
import com.phystech.edu.mipt.data.RestaurantDao


@Database(entities = [NearestRestaurantEntity::class, PopularRestaurantEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao}