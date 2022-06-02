package dev.d1s.lpdwh.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.validation.constraints.NotBlank

@ConstructorBinding
@ConfigurationProperties("lp-dwh.discord")
data class DiscordWebhookClusterConfigurationProperties(
    @NotBlank
    val webhooks: String,

    val username: String?,

    val avatarUrl: String?
)