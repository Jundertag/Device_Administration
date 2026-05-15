package com.jayden.deviceadministration.data.room.security

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SecurityLogEntity::class], version = 1)
abstract class SecurityLogDatabase : RoomDatabase() {
    abstract fun securityLogDao(): SecurityLogDao
}