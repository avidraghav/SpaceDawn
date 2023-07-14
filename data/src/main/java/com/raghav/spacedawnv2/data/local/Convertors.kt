package com.raghav.spacedawnv2.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.raghav.spacedawnv2.domain.model.LaunchServiceProvider
import com.raghav.spacedawnv2.domain.model.Mission
import com.raghav.spacedawnv2.domain.model.NetPrecision
import com.raghav.spacedawnv2.domain.model.Pad
import com.raghav.spacedawnv2.domain.model.Rocket
import com.raghav.spacedawnv2.domain.model.Status
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Convertors(val moshi: Moshi) {

    @TypeConverter
    fun launchServiceProviderToString(launchServiceProvider: LaunchServiceProvider): String {
        return moshi.adapter(LaunchServiceProvider::class.java).toJson(launchServiceProvider)
    }

    @TypeConverter
    fun stringToLaunchServiceProvider(value: String): LaunchServiceProvider? {
        return moshi.adapter(LaunchServiceProvider::class.java).fromJson(value)
    }

    @TypeConverter
    fun missionToString(mission: Mission): String {
        return moshi.adapter(Mission::class.java).toJson(mission)
    }

    @TypeConverter
    fun stringToMission(value: String): Mission? {
        return moshi.adapter(Mission::class.java).fromJson(value)
    }

    @TypeConverter
    fun netPrecisionToString(netPrecision: NetPrecision): String {
        return moshi.adapter(NetPrecision::class.java).toJson(netPrecision)
    }

    @TypeConverter
    fun stringToNetPrecision(value: String): NetPrecision? {
        return moshi.adapter(NetPrecision::class.java).fromJson(value)
    }

    @TypeConverter
    fun rocketToString(rocket: Rocket): String {
        return moshi.adapter(Rocket::class.java).toJson(rocket)
    }

    @TypeConverter
    fun stringToRocket(value: String): Rocket? {
        return moshi.adapter(Rocket::class.java).fromJson(value)
    }

    @TypeConverter
    fun statusToString(status: Status): String {
        return moshi.adapter(Status::class.java).toJson(status)
    }

    @TypeConverter
    fun stringToStatus(value: String): Status? {
        return moshi.adapter(Status::class.java).fromJson(value)
    }

    @TypeConverter
    fun padToString(pad: Pad): String {
        return moshi.adapter(Pad::class.java).toJson(pad)
    }

    @TypeConverter
    fun stringToPad(value: String): Pad? {
        return moshi.adapter(Pad::class.java).fromJson(value)
    }
}
