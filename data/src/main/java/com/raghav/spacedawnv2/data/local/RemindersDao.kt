package com.raghav.spacedawnv2.data.local

import androidx.room.Dao
import androidx.room.Query
import com.raghav.spacedawnv2.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface RemindersDao {
    @Query("SELECT * FROM reminders")
    fun getReminders(): Flow<List<Reminder>>

    @Query("DELETE FROM reminders WHERE id =:reminderId")
    suspend fun deleteReminder(reminderId: String)
}
