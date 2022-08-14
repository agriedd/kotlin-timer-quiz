package com.comodoindotech.quiztimer.packages.connection.client

interface ClientSocket{
    fun connect()
    fun sendData(data: String)
    fun disconnect()
}