package client

import cache.CacheSerializable
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

fun main(): Unit {
    val server = Socket("localhost", 6969)
    println("Luke - Conectado a servidor ${server.inetAddress}")

    val sendBuffer = ObjectOutputStream(server.getOutputStream())
    val receiveBuffer = ObjectInputStream(server.getInputStream())

    println("Luke - Enviando identificacion")
    sendBuffer.writeInt(1) // le decimos que somos Luke
    val response1 = receiveBuffer.readInt()
    if (response1 == 1) {
        val naves = receiveBuffer.readObject() as CacheSerializable
        println("Luke - Naves registradas: ${naves.cache}")
    }
    else {
        println("Luke - Hubo algun error con la identificacion")
        server.close()
    }
}