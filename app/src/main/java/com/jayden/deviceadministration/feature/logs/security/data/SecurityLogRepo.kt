package com.jayden.deviceadministration.feature.logs.security.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jayden.deviceadministration.feature.logs.security.domain.SecurityEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SecurityLogRepo(
    val dao: SecurityLogDao
) {
    val logsFlow: Flow<PagingData<SecurityEvent>> = Pager(
        config = PagingConfig(pageSize = 50),
        pagingSourceFactory = { dao.pagingSource() }
    ).flow.map { data ->
        data.map { entity -> SecurityEvent(
            entity.logTimestampNanos,
            entity.logId,
            entity.logLevel,
            entity.logTag,
            entity.logData
        ) }
    }
}