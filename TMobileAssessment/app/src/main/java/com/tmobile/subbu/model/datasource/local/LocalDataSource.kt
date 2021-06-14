package com.tmobile.subbu.model.datasource.local

import com.tmobile.subbu.model.data.Card
import com.tmobile.subbu.model.datasource.DataSource
import com.tmobile.subbu.model.datasource.local.db.CardsDB

/**
 * DataSource which reads the data from the DB
 */
class LocalDataSource(private val cardsDB: CardsDB) : DataSource {
    override suspend fun getCards() = cardsDB.cardsDao().getCards()

    suspend fun insertCard(card: Card) {
        cardsDB.cardsDao().insertCard(card)
    }
}