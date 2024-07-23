package com.github.kmpsandbox.featuresample.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.github.kmpsandbox.featuresample.model.domain.Item
import com.github.kmpsandbox.featuresample.presentation.details.add.AddItemComponentImpl
import com.github.kmpsandbox.featuresample.presentation.details.edit.EditItemComponentImpl
import com.github.kmpsandbox.featuresample.presentation.list.ListComponentImpl
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.ItemList,
        handleBackButton = true,
        childFactory = ::handleNavConfig,
        serializer = Config.serializer()
    )

    private fun handleNavConfig(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            Config.ItemList -> RootComponent.Child.ContactList(
                ListComponentImpl(
                    onAddItemClick = {
                        navigation.push(Config.Creation)
                    },
                    onItemClick = { item ->
                        navigation.push(Config.Details(item))
                    },
                    componentContext = componentContext
                )
            )


            is Config.Details -> RootComponent.Child.Details(
                EditItemComponentImpl(
                    item = config.item,
                    onContactSaved = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
            )


            Config.Creation -> RootComponent.Child.Creation(
                AddItemComponentImpl(
                    onItemSaved = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
            )

        }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object ItemList : Config

        @Serializable
        data class Details(val item: Item) : Config

        @Serializable
        data object Creation : Config
    }
}