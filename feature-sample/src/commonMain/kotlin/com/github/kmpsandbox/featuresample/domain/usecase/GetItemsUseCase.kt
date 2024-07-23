package com.github.kmpsandbox.featuresample.domain.usecase

import com.github.kmpsandbox.featuresample.data.ItemsRepository
import com.github.kmpsandbox.featuresample.model.domain.Item
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(
    private val repository: ItemsRepository
) {
    suspend operator fun invoke(): Flow<List<Item>> = repository.getItems()
}