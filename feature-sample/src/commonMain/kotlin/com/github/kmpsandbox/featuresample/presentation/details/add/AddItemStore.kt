package com.github.kmpsandbox.featuresample.presentation.details.add

import com.github.kmpsandbox.featuresample.presentation.details.add.AddItemStore.*

import com.arkivanov.mvikotlin.core.store.Store

/**
 * В паттерне MVI роль ViewModel из MVVM (Presenter из MVP) играет сущность Store.
 *
 * В MVI у Store есть 1 вход и 1 выход.
 *  MVI существует в UDF-парадигме, поэтому:
 *  - Единый стейт.
 *  - Одна точка входа от UI к логике - сущность Intent (а не несколько методов ViewModel).
 *
 * В Store прилетает Intent, и в зависимости от Intent изменяется State.
 */
interface AddItemStore : Store<Intent, State, Label> {

    data class State(
        val text: String
    )

    sealed interface Label { // (аналог эффектов в ELM)
        data object ItemSaved : Label
    }

    /**
     * Intent - единая сущность, которая содержит "все возможные методы, которые могут быть вызваны на экране".
     */
    sealed interface Intent {
        data class ChangeItemText(val text: String) : Intent
        data object SaveItem : Intent
    }

}