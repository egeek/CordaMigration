package com.stephenhouston.corda.migration

import net.corda.core.identity.CordaX500Name
import net.corda.core.identity.Party
import net.corda.core.utilities.getOrThrow
import net.corda.testing.core.singleIdentity
import net.corda.testing.node.MockNetwork
import net.corda.testing.node.StartedMockNode
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.test.assertTrue

class StoreLocalMessageFlowTest {

    private lateinit var mockNet: MockNetwork
    private lateinit var notaryNode: StartedMockNode
    private lateinit var megaCorpNode: StartedMockNode
    private lateinit var megaCorp: Party
    private lateinit var notary: Party

    @Before
    fun setup() {
        mockNet = MockNetwork(
            cordappPackages = listOf("com.stephenhouston")
        )
        notaryNode = mockNet.defaultNotaryNode
        megaCorpNode = mockNet.createPartyNode(CordaX500Name("MegaCorp", "London", "GB"))
        notary = mockNet.defaultNotaryIdentity
        megaCorp = megaCorpNode.info.singleIdentity()
    }

    @After
    fun tearDown() {
        mockNet.stopNodes()
    }

    @Test
    fun shouldBeAbleToStoreMessage() {
        val message = Message(id = UUID.randomUUID(), contents = "My test message")
        val flow = StoreLocalMessageFlow(message = message)
        val futureResult = megaCorpNode.startFlow(flow)
        mockNet.runNetwork()
        futureResult.getOrThrow()
        assertTrue { true }
    }

}