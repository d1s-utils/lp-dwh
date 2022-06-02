package dev.d1s.lpdwh.configuration

import dev.d1s.lp.client.factory.longPollingClient
import dev.d1s.lpdwh.constant.LP_DWH_EVENT_RECIPIENT
import dev.d1s.lpdwh.properties.ListenerConfigurationProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LongPollingClientConfiguration {

    @set:Autowired
    lateinit var properties: ListenerConfigurationProperties

    @Bean
    fun longPollingClient() = longPollingClient {
        recipient = LP_DWH_EVENT_RECIPIENT
        baseUrl = properties.baseUrl
        authorization = properties.authorization
    }
}