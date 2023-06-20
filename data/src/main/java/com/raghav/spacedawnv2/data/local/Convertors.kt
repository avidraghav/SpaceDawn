package com.raghav.spacedawnv2.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.raghav.spacedawnv2.domain.model.LaunchServiceProvider
import com.raghav.spacedawnv2.domain.model.Mission
import com.raghav.spacedawnv2.domain.model.NetPrecision
import com.raghav.spacedawnv2.domain.model.Pad
import com.raghav.spacedawnv2.domain.model.Rocket
import com.raghav.spacedawnv2.domain.model.Status

class Convertors {

    private val gson = Gson()

    @TypeConverter
    fun launchServiceProviderToString(launchServiceProvider: LaunchServiceProvider): String {
        return gson.toJson(launchServiceProvider)
    }

    @TypeConverter
    fun stringToLaunchServiceProvider(value: String): LaunchServiceProvider {
        return gson.fromJson(value, LaunchServiceProvider::class.java)
    }

    @TypeConverter
    fun missionToString(mission: Mission): String {
        return gson.toJson(mission)
    }

    @TypeConverter
    fun stringToMission(value: String): Mission {
        return gson.fromJson(value, Mission::class.java)
    }

    @TypeConverter
    fun netPrecisionToString(netPrecision: NetPrecision): String {
        return gson.toJson(netPrecision)
    }

    @TypeConverter
    fun stringToNetPrecision(value: String): NetPrecision {
        return gson.fromJson(value, NetPrecision::class.java)
    }

    @TypeConverter
    fun rocketToString(rocket: Rocket): String {
        return gson.toJson(rocket)
    }

    @TypeConverter
    fun stringToRocket(value: String): Rocket {
        return gson.fromJson(value, Rocket::class.java)
    }

    @TypeConverter
    fun statusToString(status: Status): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun stringToStatus(value: String): Status {
        return gson.fromJson(value, Status::class.java)
    }

    @TypeConverter
    fun padToString(pad: Pad): String {
        return gson.toJson(pad)
    }

    @TypeConverter
    fun stringToPad(value: String): Pad {
        return gson.fromJson(value, Pad::class.java)
    }
}
