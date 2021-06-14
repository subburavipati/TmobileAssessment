package com.tmobile.subbu.model.datasource

import com.tmobile.subbu.model.data.Card

interface DataSource {
    suspend fun getCards(): List<Card>
}