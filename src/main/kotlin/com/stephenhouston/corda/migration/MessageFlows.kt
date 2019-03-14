package com.stephenhouston.corda.migration

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC

@InitiatingFlow(version = 1)
@StartableByRPC
class StoreLocalMessageFlow(
    val message: Message
) : FlowLogic<Unit>() {

    @Suspendable
    override fun call() {
        // Store the transitory state, and return the stored version
        val query = """
            INSERT
            INTO sjh_messages(id, contents)
            VALUES(?,?)
        """
        val jdbcSession = serviceHub.jdbcSession()
        val statement = jdbcSession.prepareStatement(query)
        statement.setString(1, message.id.toString())
        statement.setString(2, message.contents)
        statement.executeUpdate()
    }
}