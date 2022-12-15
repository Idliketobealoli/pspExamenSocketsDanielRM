package cache

import models.Nave
import java.lang.Thread.sleep
import java.util.concurrent.locks.ReentrantLock

object Cache {
    private val cache = mutableListOf<Nave>()
    private val lock = ReentrantLock()

    fun getCache(): List<Nave> {
        return cache.toList()
    }

    fun updateCache(nave: Nave) {
        while (!lock.tryLock()) {
            sleep((100L..500L).random())
        }
        if (lock.isHeldByCurrentThread) {
            cache.add(nave)
        }
        lock.unlock()
    }
}

class CacheSerializable(): java.io.Serializable {
    lateinit var cache: List<Nave>

    constructor(naves: List<Nave>): this() {
        this.cache = naves
    }
}