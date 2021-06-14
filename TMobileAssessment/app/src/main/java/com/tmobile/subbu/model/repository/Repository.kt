package com.tmobile.subbu.model.repository

import com.tmobile.subbu.model.data.Card

interface Repository {
    suspend fun getCards(): List<Card>
}