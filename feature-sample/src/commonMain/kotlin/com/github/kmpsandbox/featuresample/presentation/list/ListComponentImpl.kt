package com.github.kmpsandbox.featuresample.presentation.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.github.kmpsandbox.featuresample.model.domain.Item
import com.github.kmpsandbox.featuresample.utils.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListComponentImpl(
    onAddItemClick: () -> Unit,
    onItemClick: (Item) -> Unit,
    componentContext: ComponentContext
) : ListComponent, ComponentContext by componentContext {

    private val store: ListStore = instanceKeeper.getStore {
        val storeFactory = ListStoreFactory()
        storeFactory.create()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ListStore.State> = store.stateFlow

    init {
        componentScope.launch {
            store.labels.collect { label ->
                when (label) {
                    ListStore.Label.OnAddItemClick -> onAddItemClick()
                    is ListStore.Label.OnItemClick -> onItemClick(label.item)
                }
            }
        }
    }

    override fun onItemClick(item: Item) {
        store.accept(ListStore.Intent.OnItemClick(item))
    }

    override fun onAddItemClick() {
        store.accept(ListStore.Intent.OnAddItemClick)
    }
}