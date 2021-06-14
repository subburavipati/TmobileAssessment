package com.tmobile.subbu.model.datasource.remote

import com.tmobile.subbu.model.datasource.DataSource
import com.tmobile.subbu.model.datasource.remote.retrofit.CardsApi

/**
 * DataSource which deals with the retrofit API to get the data
 */
class RemoteDataSource(private val cardsApi: CardsApi) : DataSource {
    override suspend fun getCards() = cardsApi.getCards().page.cards
}