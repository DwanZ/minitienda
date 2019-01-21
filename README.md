# Minitienda 

## Proceso de desarrollo

Este proyecto fue realizado en Android nativo con el lenguaje Kotlin, utilizando programación reactiva y okhttp para realizar las peticiones, se implementó stetho para observar el almacenamiento local, se utilizó el patrón de diseño MVP clean architecture y la librería Room.

El proyecto tuvo un tiempo de desarrollo de 26 horas distribuidas en las actividades de:
- Configuración del proyecto
- Desarrollo del splash
- Desarrollo del módulo de datos con repositorios, fuente de datos local y remota, configuración de la persistencia local, y configuración de la petición de creación de solicitud de compra.
- Desarrollo del módulo de productos
- Desarrollo del módulo de ventas


## Funcionamiento

La aplicación funciona cargando los productos en la base de datos local al momento de abrir la actividad de productos, si ya hay productos presentes en la base de datos solo los recupera, en el menú lateral superior derecho podemos acceder a la lista de compras las compras se almacenan de manera local al crearse cuando se consume el Tpaga api, cuando una compra cambia de estado este se ve reflejado en la lista de compras si se actualiza,

### Mensajes para el usuario

- Intenta hacer una compra sin conexión => No tienes conexión a internet por favor revisa tu dispositivo
- No tiene acceso a la Tpaga api (401, 403) => No tienes acceso a la aplicación de Tpaga comunícate con soporte
- Error en el envío de los datos (422) => Se presentó un error en el envío de los datos del producto
- Error inesperado => Un error inesperado ha ocurrido

### Comunicación Con Tpaga

Al recibir una respuesta positiva del Tpaga api se procede a almacenar la compra localmente y luego se genera una intención con la url de pago, al abrirla con la billetera se muestra el widget de pago. En la aplicación minitienda se configuraró un action.VIEW para resolver el link de compras enviado al crear la solicitud de compras, se resuelve abriendo la vista del listado de compras.

## Notas y Observaciones 

Cabe mencionar que en durante el desarrollo de la prueba:

- Pude registrarme correctamente en la app de pruebas de Tpaga
- No pude configurar un medio de pago ya que siempre me generaba un error en el número de la tarjeta por lo tanto a la hora de probar no pude pagar una solicitud de compra, revisé el video que me llego por correo al registrarme pero no me fue de ayuda y no pude resolverlo.
- Me costó encontrar en la documentación los códigos de respuesta para saber porque se me generaba un 422 cuando lo consumía con x datos y cuando hacía un curl con los mismos datos me generaba correctamente la solicitud.
- Me confundió un poco que en el enunciado diga que se consume directamente la api-wallet pero cuando se lee la documentación dice que esa comunicación debe hacerse sólo entre backend y backend.
- En el enunciado en la sección de "insumos" en el primero párrafo dice sitema en vez de sistema.


Muchas gracias
