package com.exxuslee.data.di

import androidx.room.Room
import com.exxuslee.data.utils.Constants.CARD_INFO_DB
import com.exxuslee.data.local.PriceDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Exxus Lee on 23/07/2020.
 */

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), PriceDatabase::class.java, CARD_INFO_DB)
            .fallbackToDestructiveMigration().build()
    }

    factory { get<PriceDatabase>().PriceDao }
}