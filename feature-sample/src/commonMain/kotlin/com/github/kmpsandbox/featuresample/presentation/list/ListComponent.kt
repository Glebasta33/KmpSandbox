package com.github.kmpsandbox.featuresample.presentation.list

import com.github.kmpsandbox.featuresample.model.domain.Item
import kotlinx.coroutines.flow.StateFlow

interface ListComponent {

    val model: StateFlow<ListStore.State>

    fun onItemClick(item: Item)

    fun onAddItemClick()
}