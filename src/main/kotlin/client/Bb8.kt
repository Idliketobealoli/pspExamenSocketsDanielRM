package client

import cache.CacheSerializable
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

fun main(): Unit {
    val server = Socket("localhost", 6969)
    println("BB8 - Conectado a servidor ${server.inetAddress}")

    val sendBuffer = ObjectOutputStream(server.getOutputStream())
    val receiveBuffer = ObjectInputStream(server.getInputStream())

    println("BB8 - Enviando identificacion")
    sendBuffer.writeInt(2) // le decimos que somos bb8
    val response1 = receiveBuffer.readInt()
    if (response1 == 2) {
        val navesAmount = receiveBuffer.readInt()
        println("BB8 - Cantidad de naves registradas de momento: $navesAmount")
        val misiles = receiveBuffer.readInt()
        println("BB8 - Cantidad de misiles existentes de momento: $misiles")
    }
    else {
        println("BB8 - Hubo algun error con la identificacion")
        server.close()
    }
}