package com.raghav.spacedawnv2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raghav.spacedawnv2.domain.model.LaunchDetail

@Database(entities = [LaunchDetail::class], version = 1, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class LaunchesDatabase : RoomDatabase() {

    abstract fun getLaunchesDao(): LaunchesDao
}
