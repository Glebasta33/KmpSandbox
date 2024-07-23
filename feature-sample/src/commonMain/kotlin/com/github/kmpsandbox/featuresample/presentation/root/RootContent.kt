package com.github.kmpsandbox.featuresample.presentation.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.github.kmpsandbox.featuresample.presentation.details.EditItem
import com.github.kmpsandbox.featuresample.presentation.list.ListContent

@Composable
fun RootContent(
    component: RootComponent
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Children(stack = component.stack) { child ->
            when (val childInstance: RootComponent.Child = child.instance) {
                is RootComponent.Child.ContactList -> ListContent(component = childInstance.component)
                is RootComponent.Child.Details -> EditItem(component = childInstance.component)
//                is Child.Creation -> CreationContent(component = childInstance.component)
            }
        }
    }
}