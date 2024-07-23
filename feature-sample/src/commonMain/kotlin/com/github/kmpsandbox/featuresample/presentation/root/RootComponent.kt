package com.github.kmpsandbox.featuresample.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.github.kmpsandbox.featuresample.presentation.details.edit.EditItemComponent
import com.github.kmpsandbox.featuresample.presentation.list.ListComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class ContactList(val component: ListComponent) : Child
        class Details(val component: EditItemComponent) : Child
//        class Creation(val component: CreationComponent) : Child
    }
}