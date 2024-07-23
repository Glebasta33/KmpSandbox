package com.github.kmpsandbox.featuresample.data

import com.github.kmpsandbox.featuresample.model.domain.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object ItemsRepository {


    private val _items = mutableListOf(
        Item(0, "Item 1"),
        Item(1, "Item 2"),
        Item(2, "Item 3")
    )
    private val _itemsFlow = MutableStateFlow(_items)


    suspend fun getItems(): Flow<List<Item>> {
        delay(1000)
        return _itemsFlow
    }

    fun addItem(item: Item) {
        _itemsFlow.update { oldItems ->
            val id = if (item.id < 0) _items.size else item.id
            oldItems.apply { add(item.copy(id = id)) }
        }
    }

    fun updateItem(updatedItem: Item) {
        _itemsFlow.value.replaceAll { item ->
            if (item.id == updatedItem.id) {
                updatedItem
            } else {
                item
            }
        }
    }
}