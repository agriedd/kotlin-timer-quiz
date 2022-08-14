package com.comodoindotech.quiztimer.packages.connection.models

enum class Commands(i: Int) {
    /**
     * Client
     * 0x10....
     *      Request     = 0x1010[00]
     *
     * Server
     * 0xFF....
     *
     */
    EMPTY(0x000),
    CLIENT_REQUEST_PARTICIPANT(0x101000),
}