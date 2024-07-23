package com.github.kmpsandbox.featuresample.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.github.kmpsandbox.featuresample.data.ItemsRepository
import com.github.kmpsandbox.featuresample.domain.usecase.AddItemUseCase
import com.github.kmpsandbox.featuresample.domain.usecase.EditItemUseCase
import com.github.kmpsandbox.featuresample.domain.usecase.GetItemsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

object ItemsModule {
    val items = module {
        single { ItemsRepository }
        single { AddItemUseCase(get()) }
        single { EditItemUseCase(get()) }
        single { GetItemsUseCase(get()) }
        factory { DefaultStoreFactory() } bind StoreFactory::class
    }
}

//для быстрого получения зависимости из графа по типу
inline fun <reified T> getKoinInstance(qualifier: Qualifier? = null): T {
    return object : KoinComponent {
        val value : T by inject()
    }.value
}