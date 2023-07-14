package com.raghav.spacedawnv2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raghav.spacedawnv2.domain.model.LaunchDetail

@Dao
interface LaunchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLaunch(launch: LaunchDetail)

    @Query("SELECT * FROM saved_launches")
    fun getSavedLaunches(): List<LaunchDetail>
}
