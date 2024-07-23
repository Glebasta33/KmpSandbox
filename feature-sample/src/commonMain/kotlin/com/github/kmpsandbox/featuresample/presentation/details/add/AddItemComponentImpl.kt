package com.github.kmpsandbox.featuresample.presentation.details.add

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.github.kmpsandbox.featuresample.di.getKoinInstance
import com.github.kmpsandbox.featuresample.utils.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddItemComponentImpl(
    componentContext: ComponentContext,
    onItemSaved: () -> Unit
) : AddItemComponent, ComponentContext by componentContext {

    private val store: AddItemStore = instanceKeeper.getStore {
        val storeFactory = AddItemStoreFactory(getKoinInstance(), getKoinInstance())
        storeFactory.create()
    }

    init {
        componentScope.launch {
            store.labels.collect {
                when (it) {
                    AddItemStore.Label.ItemSaved -> onItemSaved()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AddItemStore.State>
        get() = store.stateFlow

    override fun onItemTextChanged(text: String) {
        store.accept(AddItemStore.Intent.ChangeItemText(text))
    }

    override fun onSaveButtonClick() {
        store.accept(AddItemStore.Intent.SaveItem)
    }
}