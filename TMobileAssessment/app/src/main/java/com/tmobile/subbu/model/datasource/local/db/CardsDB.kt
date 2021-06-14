package com.tmobile.subbu.model.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tmobile.subbu.model.data.*
import com.tmobile.subbu.model.datasource.local.db.dao.CardsDao

@Database(
    entities = [Card::class],
    version = 1,
    exportSchema = false
)
abstract class CardsDB : RoomDatabase() {
    abstract fun cardsDao(): CardsDao
}