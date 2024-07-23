package com.github.kmpsandbox.featuresample.presentation.details.add

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.github.kmpsandbox.featuresample.data.ItemsRepository
import com.github.kmpsandbox.featuresample.domain.usecase.AddItemUseCase
import com.github.kmpsandbox.featuresample.model.domain.Item
import com.github.kmpsandbox.featuresample.presentation.details.add.AddItemStore.*

/**
 * Фабрика для создания реализации интерфейса стора.
 * StoreFactory из библиотеки mvikotlin генерит реализацию стора.
 */
class AddItemStoreFactory(
    private val storeFactory: DefaultStoreFactory = DefaultStoreFactory(),
    private val addItemUseCase: AddItemUseCase = AddItemUseCase(ItemsRepository)
) {

    fun create(): AddItemStore =
        object : AddItemStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AddItemStore",
            autoInit = true,
            initialState = State(""),
            reducer = ReducerImpl,
            bootstrapper = null,
            executorFactory = ::ExecutorImpl
        ) {}

    private sealed interface Action  // это аналог Intent, но от репозитория (data-слоя и бизнес логики).

    private sealed interface Msg { // Intent и Action преобразовывается в Msg, который меняет State экрана
        data class ChangeItemText(val text: String) : Msg
    }

    // Отвечает за логику преобразования Action и Intent в Msg, который меняет стейт экрана
    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when(intent) {
                is Intent.ChangeItemText -> {
                    dispatch(Msg.ChangeItemText(text = intent.text))
                }

                Intent.SaveItem -> {
                    val state = getState()
                    addItemUseCase(Item(text = state.text))
                    publish(Label.ItemSaved)
                }
            }
        }

        // Обработка Action (в данном случае не нужна).
//        override fun executeAction(action: Action, getState: () -> State) {}
    }

    // Reducer - принимает Msg и изменяет State
    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when(msg) {
            is Msg.ChangeItemText -> copy(text = msg.text)
        }

    }

}