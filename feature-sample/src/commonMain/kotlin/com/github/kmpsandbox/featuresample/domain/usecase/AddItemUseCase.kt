package com.github.kmpsandbox.featuresample.domain.usecase

import com.github.kmpsandbox.featuresample.data.ItemsRepository
import com.github.kmpsandbox.featuresample.model.domain.Item

class AddItemUseCase(
    private val repository: ItemsRepository
) {
    operator fun invoke(item: Item) {
        repository.addItem(item)
    }
}