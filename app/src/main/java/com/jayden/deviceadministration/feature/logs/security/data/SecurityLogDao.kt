package com.jayden.deviceadministration.feature.logs.security.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SecurityLogDao {
    @Insert
    suspend fun insertAll(logs: List<SecurityLogEntity>)
    @Delete
    suspend fun deleteLog(log: SecurityLogEntity)
    @Query("SELECT * FROM SecurityLogEntity ORDER BY logTimestampNanos DESC")
    fun pagingSource(): PagingSource<Int, SecurityLogEntity>
}