package com.jayden.deviceadministration.data.room.network

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DnsNetworkEventDao {
    @Insert
    fun insertAll(logs: List<DnsNetworkEventEntity>)

    @Delete
    fun deleteLog(log: DnsNetworkEventEntity)

    @Query("SELECT * FROM DnsNetworkEventEntity ORDER BY logTimestamp DESC")
    fun pagingSource(): PagingSource<Long, DnsNetworkEventEntity>
}