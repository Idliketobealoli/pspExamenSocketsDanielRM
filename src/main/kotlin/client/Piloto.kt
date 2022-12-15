package client

import models.Nave
import models.TipoNave
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.time.LocalDate

fun main(): Unit {
    val server = Socket("localhost", 6969)
    println("Piloto - Conectado a servidor ${server.inetAddress}")
    val nave =  Nave(null, TipoNave.XWING, (10..20).random(), true, LocalDate.now())

    val sendBuffer = ObjectOutputStream(server.getOutputStream())
    val receiveBuffer = ObjectInputStream(server.getInputStream())

    println("Piloto - Enviando identificacion")
    sendBuffer.writeInt(0) // le decimos que somos un piloto
    val response1 = receiveBuffer.readInt()
    if (response1 == 0) {
        sendBuffer.writeObject(nave)
        println("Piloto - ${receiveBuffer.readUTF()}")
    }
    else {
        println("Piloto - Hubo algun error con la identificacion")
        server.close()
    }
}