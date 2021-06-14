package com.tmobile.subbu.model.repository

import androidx.annotation.VisibleForTesting
import com.tmobile.subbu.model.data.Card
import com.tmobile.subbu.model.datasource.local.LocalDataSource
import com.tmobile.subbu.model.datasource.remote.RemoteDataSource
import com.tmobile.subbu.model.datasource.remote.util.NetworkUtil
import java.lang.Exception

/**
 * Repository class which checks the network connection
 * if available then calls and reads the data from the network,
 * otherwise reads the data from the DB if available
 */
@VisibleForTesting
open class CardsRepository(
    private val networkUtil: NetworkUtil,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getCards(): List<Card> {
        return try {
            if (networkUtil.isNetworkAvailable()) {
                remoteDataSource.getCards().apply {
                    forEach {
                        localDataSource.insertCard(it)
                    }
                }
            } else {
                localDataSource.getCards()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}