package dev.d1s.lpdwh.listener

import dev.d1s.lp.client.api.LongPollingClient
import dev.d1s.lpdwh.properties.ListenerConfigurationProperties
import dev.d1s.lpdwh.service.LongPollingEventBroadcastingService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class LongPollingEventListener : InitializingBean {

    @set:Autowired
    lateinit var longPollingClient: LongPollingClient

    @set:Autowired
    lateinit var listenerConfigurationProperties: ListenerConfigurationProperties

    @set:Autowired
    lateinit var longPollingEventBroadcastingService: LongPollingEventBroadcastingService

    override fun afterPropertiesSet() {
        runBlocking {
            StringUtils.commaDelimitedListToSet(
                listenerConfigurationProperties.groups
            ).forEach {
                launch {
                    longPollingClient.onEvent<Map<Any, Any>>(it) {
                        longPollingEventBroadcastingService.broadcast(this)
                    }
                }
            }
        }
    }
}