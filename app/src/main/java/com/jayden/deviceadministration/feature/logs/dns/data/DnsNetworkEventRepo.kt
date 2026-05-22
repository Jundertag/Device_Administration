package com.jayden.deviceadministration.feature.logs.dns.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jayden.deviceadministration.feature.logs.dns.domain.DnsNetworkEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DnsNetworkEventRepo(
    val dao: DnsNetworkEventDao
) {
    val logsFlow: Flow<PagingData<DnsNetworkEvent>> = Pager(
        config = PagingConfig(pageSize = 50),
        pagingSourceFactory = { dao.pagingSource() }
    ).flow.map { data ->
        data.map { entity ->
            DnsNetworkEvent(
                entity.logIpAddress,
                entity.logPort,
                entity.logPackage,
                entity.logHostname,
                entity.logIpAddresses,
                entity.logAddressesResolved
            )
        }
    }
}