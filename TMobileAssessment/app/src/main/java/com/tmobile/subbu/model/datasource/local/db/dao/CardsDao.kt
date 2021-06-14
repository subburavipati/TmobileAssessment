package com.tmobile.subbu.model.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmobile.subbu.model.data.Card

@Dao
interface CardsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCard(card: Card)

    @Query("select * from card")
    suspend fun getCards(): List<Card>
}