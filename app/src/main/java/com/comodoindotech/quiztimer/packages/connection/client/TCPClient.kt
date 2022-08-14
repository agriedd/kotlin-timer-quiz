package com.comodoindotech.quiztimer.packages.connection.client

import android.content.Context
import android.util.Log
import java.io.OutputStream
import java.io.PrintWriter
import java.net.Socket

class TCPClient(context: Context): AbstractTCPClientSocket() {

    private var handler: Thread? = null
    private var socket: Socket? = null
    private var sender: PrintWriter? = null
    private var outputStream: OutputStream? = null
    private var id: Short? = null

    override fun connect() {
        /**
         * make connection asynchronous
         *
         */
        handler = Thread{
            Log.d("connect", "${Thread.currentThread()} has run.")
            this.socket = Socket(this.host, this.port?.toInt() ?: 3000);
            Log.d("connect", "berhasil menyambungkan");
            outputStream = this.socket!!.getOutputStream()
            sender = PrintWriter(outputStream, true)
            connectionEvent?.onConnected()
        }
        handler!!.start();
    }

    override fun sendData(data: String) {
        connectionEvent!!.beforeSend(data)
        this.sender!!.println(data)
        connectionEvent!!.afterSend(data)
    }

    override fun disconnect() {
        connectionEvent?.onDisconnected()
    }

}