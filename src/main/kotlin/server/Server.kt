package server

import java.net.ServerSocket
import java.net.Socket

fun main () : Unit {
    val server: ServerSocket
    var client: Socket
    val puerto = 6969

    try {
        server = ServerSocket(puerto)
        println("Servidor ${server.inetAddress} conectado en puerto $puerto")
        while (true) {
            client = server.accept()
            println("Usuario ${client.inetAddress} conectado. Atendiendo...")
            val gc = GestorClientes(client)
            gc.start()
        }
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
}