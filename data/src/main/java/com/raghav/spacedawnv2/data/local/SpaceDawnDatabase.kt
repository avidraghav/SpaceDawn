package com.raghav.spacedawnv2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.Reminder

@Database(entities = [LaunchDetail::class, Reminder::class], version = 2, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class SpaceDawnDatabase : RoomDatabase() {

    abstract fun getLaunchesDao(): LaunchesDao
    abstract fun getRemindersDao(): RemindersDao
}
