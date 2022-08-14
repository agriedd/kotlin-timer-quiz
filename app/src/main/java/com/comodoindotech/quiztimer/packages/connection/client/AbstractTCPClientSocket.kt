package com.comodoindotech.quiztimer.packages.connection.client

interface TCPImageListener{
    fun onImageReceived()
}

interface ClientListener: TCPImageListener{
    fun onMessageReceived()
}

interface DataTransferListener{
    fun beforeSend(data: String)
    fun afterSend(data: String)
}

interface ConnectionListener: DataTransferListener {
    fun onConnected()
    fun onDisconnected()
    fun onResume()
}

abstract class AbstractConnectionListener{
    protected var connectionEvent: ConnectionListener? = null

    fun setConnectionListener(listener: ConnectionListener){
        this.connectionEvent = listener
    }
}

abstract class AbstractTCPClientSocket: ClientSocket, AbstractConnectionListener() {

    protected var port: Short? = null;
    protected var host: String = "0.0.0.0";

    fun makeConnection(host: String, port: Short): AbstractTCPClientSocket{
        this.port = port;
        this.host = host;
        return this;
    }

}