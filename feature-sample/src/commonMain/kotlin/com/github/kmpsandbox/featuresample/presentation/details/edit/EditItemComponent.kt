package com.github.kmpsandbox.featuresample.presentation.details.edit

import kotlinx.coroutines.flow.StateFlow

interface EditItemComponent {

    val model: StateFlow<EditItemStore.State>

    fun onItemTextChanged(text: String)

//    fun onSaveContactClicked()
}