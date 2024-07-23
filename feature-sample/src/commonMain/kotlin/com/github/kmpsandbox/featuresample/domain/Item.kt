package com.github.kmpsandbox.featuresample.domain

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int = -1,
    val text: String
)