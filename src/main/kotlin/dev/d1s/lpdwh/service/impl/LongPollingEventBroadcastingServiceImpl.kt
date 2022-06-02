package dev.d1s.lpdwh.service.impl

import club.minnced.discord.webhook.WebhookCluster
import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookEmbedBuilder
import club.minnced.discord.webhook.send.WebhookMessageBuilder
import dev.d1s.lp.commons.entity.LongPollingEvent
import dev.d1s.lpdwh.constant.LP_DWH_EVENT_RECIPIENT
import dev.d1s.lpdwh.properties.DiscordWebhookClusterConfigurationProperties
import dev.d1s.lpdwh.service.LongPollingEventBroadcastingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LongPollingEventBroadcastingServiceImpl : LongPollingEventBroadcastingService {

    @set:Autowired
    lateinit var webhookCluster: WebhookCluster

    @set:Autowired
    lateinit var properties: DiscordWebhookClusterConfigurationProperties

    override fun broadcast(event: LongPollingEvent<Map<Any, Any>>) {
        webhookCluster.broadcast(
            WebhookMessageBuilder().apply {
                setUsername(properties.username ?: LP_DWH_EVENT_RECIPIENT)

                properties.avatarUrl?.let {
                    setAvatarUrl(it)
                }

                addEmbeds(
                    WebhookEmbedBuilder().apply {
                        setDescription(
                            EMBED_DESCRIPTION
                        )

                        field(
                            EMBED_GROUP_FIELD,
                            event.group
                        )
                        field(
                            EMBED_PRINCIPAL_FIELD,
                            event.principal ?: "not set"
                        )
                        field(
                            EMBED_DATA_FIELD,
                            event.data.toString()
                        )
                        field(
                            EMBED_TIMESTAMP_FIELD,
                            event.timestamp.toString()
                        )

                        // blue
                        setColor(0x00aed5)

                        setFooter(
                            WebhookEmbed.EmbedFooter(
                                EMBED_FOOTER,
                                null
                            )
                        )
                    }.build()
                )
            }.build()
        )
    }

    private fun WebhookEmbedBuilder.field(title: String, value: String) {
        addField(
            WebhookEmbed.EmbedField(
                false,
                title,
                value.code()
            )
        )
    }

    private fun String.code() = "```$this```"

    private companion object {
        private const val EMBED_DESCRIPTION = "Just received an event."
        private const val EMBED_GROUP_FIELD = "Group"
        private const val EMBED_PRINCIPAL_FIELD = "Principal"
        private const val EMBED_DATA_FIELD = "Data"
        private const val EMBED_TIMESTAMP_FIELD = "Timestamp"
        private const val EMBED_FOOTER = "lp-dwh. Long-polling events bridge for Discord."
    }
}