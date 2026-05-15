package com.jayden.deviceadministration.data.room.security

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SecurityLogDao {
    @Insert
    fun insertAll(logs: List<SecurityLogEntity>)
    @Delete
    fun deleteLog(log: SecurityLogEntity)
    @Query("SELECT * FROM SecurityLogEntity ORDER BY logTimestampNanos DESC")
    fun pagingSource(): PagingSource<Long, SecurityLogEntity>
}