package dev.d1s.lpdwh.configuration

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.WebhookCluster
import dev.d1s.lpdwh.properties.DiscordWebhookClusterConfigurationProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils

@Configuration
class DiscordWebhookClusterConfiguration {

    @set:Autowired
    lateinit var properties: DiscordWebhookClusterConfigurationProperties

    @Bean
    fun webhookCluster() = WebhookCluster().apply {
        addWebhooks(
            StringUtils.commaDelimitedListToSet(
                properties.webhooks
            ).map {
                WebhookClient.withUrl(it)
            }
        )
    }
}