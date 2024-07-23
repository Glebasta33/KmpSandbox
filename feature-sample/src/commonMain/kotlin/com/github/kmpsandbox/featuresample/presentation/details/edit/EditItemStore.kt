package com.github.kmpsandbox.featuresample.presentation.details.edit

import com.arkivanov.mvikotlin.core.store.Store

interface EditItemStore : Store<EditItemStore.Intent, EditItemStore.State, EditItemStore.Label> {

    data class State(
        val id: Int,
        val text: String
    )

    sealed interface Intent {
        data class ChangeText(val text: String) : Intent
//        data object SaveContact : Intent
    }

    sealed interface Label {
        data object OnItemSaved : Label
    }

}