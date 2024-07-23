package com.github.kmpsandbox.featuresample.presentation.details.edit

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.github.kmpsandbox.featuresample.domain.usecase.EditItemUseCase
import com.github.kmpsandbox.featuresample.model.domain.Item

/**
 * Фабрика для создания реализации интерфейса стора.
 * StoreFactory из библиотеки mvikotlin генерит реализацию стора.
 */
class EditItemStoreFactory(
    private val storeFactory: StoreFactory,
    private val editItemUseCase: EditItemUseCase
) {

    fun create(contact: Item): EditItemStore = object : EditItemStore,
        Store<EditItemStore.Intent, EditItemStore.State, EditItemStore.Label> by storeFactory.create(
            name = "EditItemStore",
            autoInit = true,
            initialState = EditItemStore.State(
                id = contact.id,
                text = contact.text
            ),
            reducer = ReducerImp,
            bootstrapper = null,
            executorFactory = { ExecutorImpl() }
        ) {}

    private sealed interface Action // это аналог Intent, но от репозитория (data-слоя и бизнес логики).

    private sealed interface Msg { // Intent и Action преобразовывается в Msg, который меняет State экрана

        data class ChangeItemText(val username: String) : Msg

    }

    // Отвечает за логику преобразования Action и Intent в Msg
    private inner class ExecutorImpl :
        CoroutineExecutor<EditItemStore.Intent, Action, EditItemStore.State, Msg, EditItemStore.Label>() {

        override fun executeIntent(
            intent: EditItemStore.Intent,
            getState: () -> EditItemStore.State
        ) {
            when (intent) {

                is EditItemStore.Intent.ChangeText -> {
                    dispatch(Msg.ChangeItemText(username = intent.text))
                }

                EditItemStore.Intent.SaveItem -> {
                    val state = getState()
                    val item = Item(
                        id = state.id,
                        text = state.text,
                    )
                    editItemUseCase(item)
                    publish(EditItemStore.Label.OnItemSaved)
                }
            }
        }

        // Обработка Action (в данном случае не нужна).
//        override fun executeAction(action: Action, getState: () -> AddContactStore.State) {}
    }

    // Reducer - принимает Msg и изменяет State
    private object ReducerImp : Reducer<EditItemStore.State, Msg> {
        override fun EditItemStore.State.reduce(msg: Msg) = when (msg) {
            is Msg.ChangeItemText -> {
                copy(text = msg.username)
            }
        }
    }

}