package server

import cache.Cache
import cache.CacheSerializable
import models.Nave
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class GestorClientes(client: Socket): Thread() {
    private val cliente = client

    override fun run() {
        println("Gestor de clientes iniciado para cliente ${cliente.inetAddress}")
        var entryBuffer: ObjectInputStream? = null
        try {
            entryBuffer = ObjectInputStream(cliente.getInputStream())
            val sendBuffer = ObjectOutputStream(cliente.getOutputStream())

            println("Leyendo identificacion de ${cliente.inetAddress}")
            var identificacion = entryBuffer.readInt()
            println("Identificacion : $identificacion")
            when (identificacion) {
                0 -> {
                    println("Atendiendo a piloto ${cliente.inetAddress}")
                    sendBuffer.writeInt(0)
                    val nave = entryBuffer.readObject() as Nave
                    Cache.updateCache(nave)
                    println("Cache actualizada.")
                    sendBuffer.writeUTF("Datos insertados")
                }
                1 -> {
                    println("Atendiendo a Luke Skywalker ${cliente.inetAddress}")
                    sendBuffer.writeInt(1)
                    val cacheSerial = CacheSerializable(Cache.getCache())
                    sendBuffer.writeObject(cacheSerial)
                    println("Cache enviada a Luke Skywalker")
                }
                2 -> {
                    println("Atendiendo a BB8 ${cliente.inetAddress}")
                    sendBuffer.writeInt(2)
                    val cacheSerial = CacheSerializable(Cache.getCache())
                    val navesAmount = cacheSerial.cache.size
                    println("Numero de naves obtenido")
                    var misiles = 0
                    cacheSerial.cache.forEach {
                        misiles += it.misiles
                    }
                    println("Numero de misiles calculado")
                    sendBuffer.writeInt(navesAmount)
                    println("Numero de naves enviado a BB8")
                    sendBuffer.writeInt(misiles)
                    println("Numero de misiles enviado a BB8")
                }
                else -> return
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                entryBuffer?.close()
                println("Buffer de entrada cerrado")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}