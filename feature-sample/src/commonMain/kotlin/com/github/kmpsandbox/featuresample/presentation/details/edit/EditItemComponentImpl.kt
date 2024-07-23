package com.github.kmpsandbox.featuresample.presentation.details.edit

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.github.kmpsandbox.featuresample.domain.Item
import com.github.kmpsandbox.featuresample.utils.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditItemComponentImpl(
    componentContext: ComponentContext,
    item: Item,
    onContactSaved: () -> Unit
) : EditItemComponent, ComponentContext by componentContext {

    private val store: EditItemStore = instanceKeeper.getStore {
        val storeFactory = EditItemStoreFactory()
        storeFactory.create(item)
    }

    init {
        componentScope.launch {
            store.labels.collect { label ->
                when(label) {
                    EditItemStore.Label.OnItemSaved -> {
                        onContactSaved()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<EditItemStore.State>
        get() = store.stateFlow

    override fun onItemTextChanged(text: String) {
        store.accept(EditItemStore.Intent.ChangeText(text))
    }

//    override fun onSaveContactClicked() {
//        store.accept(EditItemStore.Intent.SaveContact)
//    }

}