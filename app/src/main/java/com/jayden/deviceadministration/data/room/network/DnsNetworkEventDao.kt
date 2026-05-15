package com.jayden.deviceadministration.data.room.network

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DnsNetworkEventDao {
    @Insert
    suspend fun insertAll(logs: List<DnsNetworkEventEntity>)

    @Delete
    suspend fun deleteLog(log: DnsNetworkEventEntity)

    @Query("SELECT * FROM DnsNetworkEventEntity ORDER BY logTimestamp DESC")
    fun pagingSource(): PagingSource<Int, DnsNetworkEventEntity>
}