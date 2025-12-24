package com.example.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.example.domain.domainErrors.NetworkError
import com.example.domain.domainErrors.Result
import com.example.domain.domainErrors.Result.Success
import com.example.domain.repository.NetworkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val context: Context,
) : NetworkRepository {

    override fun observeNetwork(): Flow<Result<Boolean, NetworkError>> =
        callbackFlow<Boolean> {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    trySend(true)
                }

                override fun onLost(network: Network) {
                    trySend(false)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }
            .distinctUntilChanged()
            .map<Boolean, Result<Boolean, NetworkError.Network>> { isConnected ->
                Success(isConnected)
            }
            .catch {
                emit(Result.Error(NetworkError.Network.UNKNOWN_ERROR))
            }


}

