# Migration during tests demo

This is a simple test that shows that the migrations are not loaded during when using the MockNetwork, and MockNodes in Corda.

## Execution

Run `StoreLocalMessageFlowTest` from IntelliJ, ensuring that `-javaagent:lib/quasar.jar` is added to the args.

The test will fail, as the migration to create the table is not executed.