package com.stephenhouston.corda.migration

import net.corda.core.schemas.MappedSchema
import net.corda.core.serialization.CordaSerializable
import java.util.UUID

object MessageSchema

@CordaSerializable
object MessageSchemaV1 : MappedSchema(
    schemaFamily = MessageSchema.javaClass,
    version = 1,
    mappedTypes = listOf(Message::class.java))

data class Message(
    val id : UUID,
    val contents : String
)