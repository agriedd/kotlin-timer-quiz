package com.comodoindotech.quiztimer.packages.connection.client

abstract class AbstractTCPClientSocket: ClientSocket {

    protected var port: Short? = null;
    protected var host: String = "0.0.0.0";

    fun makeConnection(host: String, port: Short): AbstractTCPClientSocket{
        this.port = port;
        this.host = host;
        return this;
    }

}