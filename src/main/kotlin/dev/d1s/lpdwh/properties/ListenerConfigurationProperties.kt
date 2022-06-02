package dev.d1s.lpdwh.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.validation.constraints.NotBlank

@ConstructorBinding
@ConfigurationProperties("lp-dwh.listener")
data class ListenerConfigurationProperties(
    @NotBlank
    val baseUrl: String,

    @NotBlank
    val authorization: String,

    @NotBlank
    val groups: String
)