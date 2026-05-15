package com.jayden.deviceadministration.data.room.network

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jayden.deviceadministration.data.room.security.SecurityLogEntity

@Dao
interface TcpNetworkEventDao {
    @Insert
    fun insertAll(logs: List<TcpNetworkEventEntity>)

    @Delete
    fun deleteLog(log: TcpNetworkEventEntity)

    @Query("SELECT * FROM TcpNetworkEventEntity ORDER BY logTimestamp DESC")
    fun pagingSource(): PagingSource<Long, TcpNetworkEventEntity>
}