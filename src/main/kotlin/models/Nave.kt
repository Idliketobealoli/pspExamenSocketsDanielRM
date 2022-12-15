package models

import java.time.LocalDate
import java.util.UUID

class Nave(): java.io.Serializable {
    lateinit var id: UUID
    var tipoNave: TipoNave = models.TipoNave.XWING
    var misiles = 10
    var hyperspace = false
    lateinit var creationDate: String

    constructor(
        id: UUID?,
        tipoNave: TipoNave,
        misiles: Int,
        hyperspace: Boolean,
        creationDate: LocalDate
    ) : this() {
        val m = if (misiles < 10 ) 10 else if (misiles > 20) 20 else misiles
        this.id = id ?: UUID.randomUUID()
        this.tipoNave = tipoNave
        this.misiles = m
        this.hyperspace = hyperspace
        this.creationDate = creationDate.toString()
    }
}

enum class TipoNave {
    XWING, TFIGHTER
}