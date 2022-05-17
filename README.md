Ejemplo de Uso de Pulsar con Kotlin
--


## Running

Hay que tener instalado y arrancado Pulsar

y dar de alta el topic en Pulsar

     const val topic = "persistent://tenant/ns1/sample-events"

Primero hay que crear los tenants

     ./pulsar-admin tenants create "tenant" 

Segundo hay que dar de alta el Namespace

    ./pulsar-admin namespaces  create "tenant/ns1"      


### Run Consumer

Ejecutar Clase **RefundCheckerService**, esta clase se queda en un bucle
escuchando mensajes en el Topic.

### Run Producer

Ejecutar Clase **Main**, esta clase inicia un Pulsar producer y genera eventos de Tipo RefundRequest



### TO-DO



### Dudas

- Para volver a leer un Topic desde el principio hay que crear un **Consumer** con un nombre distinto de 
Subscription e indicar que use **SubscriptionInitialPosition.Earliest**.
Sin embargo, para que funcione hay que cambiar en nombre de la subscripcion cada vez. Esto no tengo claro que sea lo 
correcto, ni que este bien.