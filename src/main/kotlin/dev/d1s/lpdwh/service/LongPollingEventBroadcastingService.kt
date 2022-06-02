package dev.d1s.lpdwh.service

import dev.d1s.lp.commons.entity.LongPollingEvent

interface LongPollingEventBroadcastingService {

    fun broadcast(event: LongPollingEvent<Map<Any, Any>>)
}