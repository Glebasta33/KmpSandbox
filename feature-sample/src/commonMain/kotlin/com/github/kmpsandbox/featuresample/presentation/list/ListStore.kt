package com.github.kmpsandbox.featuresample.presentation.list

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.github.kmpsandbox.featuresample.data.ItemsRepository
import com.github.kmpsandbox.featuresample.model.domain.Item
import com.github.kmpsandbox.featuresample.presentation.list.ListStore.*
import kotlinx.coroutines.launch

interface ListStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class OnItemClick(val item: Item) : Intent
        data object OnAddItemClick : Intent
    }

    data class State(val listState: ListState) {
        sealed interface ListState {
            data object Loading : ListState
            data class Loaded(val items: List<Item>) : ListState
        }
    }

    sealed interface Label {
        data class OnItemClick(val item: Item) : Label
        data object OnAddItemClick : Label
    }
}

class ListStoreFactory(
    private val storeFactory: StoreFactory = DefaultStoreFactory(),
    private val repository: ItemsRepository = ItemsRepository
) {

    fun create(): ListStore =
        object : ListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ListStore",
            initialState = State(State.ListState.Loading),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class ItemsLoaded(val items: List<Item>) : Action
    }

    private sealed interface Msg {
        data class ItemsLoaded(val items: List<Item>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                repository.getItems().collect { items ->
                    dispatch(Action.ItemsLoaded(items))
                }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.OnAddItemClick -> publish(Label.OnAddItemClick)
                is Intent.OnItemClick -> publish(Label.OnItemClick(intent.item))
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.ItemsLoaded -> {
                    dispatch(Msg.ItemsLoaded(action.items))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.ItemsLoaded -> {
                copy(listState = State.ListState.Loaded(msg.items))
            }
        }
    }
}
