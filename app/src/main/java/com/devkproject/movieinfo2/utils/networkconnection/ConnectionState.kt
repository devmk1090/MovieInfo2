package com.devkproject.movieinfo2.utils.networkconnection

sealed class ConnectionState {
    object Available: ConnectionState()
    object Unavailable: ConnectionState()
}