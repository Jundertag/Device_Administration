package com.jayden.deviceadministration.feature.logs.tcp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jayden.deviceadministration.feature.logs.tcp.domain.TcpNetworkEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class TcpNetworkEventRepo(
    val dao: TcpNetworkEventDao
) {
    val logsFlow: Flow<PagingData<TcpNetworkEvent>> = Pager(
        config = PagingConfig(pageSize = 50),
        pagingSourceFactory = { dao.pagingSource() }
    ).flow.map { data ->
        data.map { entity -> TcpNetworkEvent(
            entity.logTimestamp,
            entity.logPackageName,
            entity.logIpAddress,
            entity.logPort
        ) }
    }

}