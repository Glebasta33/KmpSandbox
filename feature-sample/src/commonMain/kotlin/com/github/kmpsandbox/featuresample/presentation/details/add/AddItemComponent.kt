package com.github.kmpsandbox.featuresample.presentation.details.add

import kotlinx.coroutines.flow.StateFlow

/**
 * Компонент - это базовая сущность в Decompose, которая описывает экран: его состояние и поведение.
 */
interface AddItemComponent {

    // Состояние экрана
    val model: StateFlow<AddItemStore.State>

    // Действия на экране:
    fun onItemTextChanged(text: String)

    fun onSaveButtonClick()

}