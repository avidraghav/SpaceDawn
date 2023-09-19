package com.raghav.spacedawnv2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchesDao {

    @Query("SELECT * FROM cached_launches")
    fun getLaunches(): Flow<List<LaunchDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheLaunches(launches: List<LaunchDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReminder(launch: Reminder)
}
