package com.github.kmpsandbox.featuresample.model.domain

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int = -1,
    val text: String
)