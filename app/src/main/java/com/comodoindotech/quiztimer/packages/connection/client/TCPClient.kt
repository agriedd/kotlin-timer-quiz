package com.comodoindotech.quiztimer.packages.connection.client

import android.content.Context
import android.util.Log
import java.net.Socket

interface TCPImageListener{
    fun onImageReceived()
}

interface TCPClientListener: TCPImageListener{
    fun onMessageReceived()
}

class TCPClient(context: Context): AbstractTCPClientSocket() {

    private var handler: Thread? = null
    private var socket: Socket? = null

    override fun connect() {
        /**
         * make connection asynchronous
         *
         */
        handler = Thread{
            Log.d("connect", "${Thread.currentThread()} has run.")
            this.socket = Socket(this.host, this.port?.toInt() ?: 3000);
            Log.d("connect", "berhasil menyambungkan");
        }
        handler!!.start();
    }

    override fun sendData(data: String) {
        TODO("Not yet implemented")
    }

    override fun disconnect() {
        TODO("Not yet implemented")
    }

}