package com.stephenhouston.corda.migration

import java.util.UUID

data class Message(
    val id : UUID,
    val contents: String
)