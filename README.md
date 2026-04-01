# messaging-app

## Resumen de la solución

La solución implementa una arquitectura desacoplada basada en microservicios para el procesamiento de mensajes.

Se compone de dos servicios principales:
-	Producer Service: recibe mensajes vía API REST, valida la información y publica el evento en RabbitMQ, este servicio llena la base de datos con 5 resgistros de origenes entre los cuales estan: 3001111111, 3002222222, 3003333333, 3004444444 y 3005555555, si intentas producir un mensaje con otro origen te dara un error, también cuenta con un método de auth basica donde debes poner un usuario y una contraseña los cuales son: admin y admin123 respectivamente.
-	Consumer Service: consume mensajes desde RabbitMQ, procesa la información y persiste el resultado en MongoDB incluso si se incumple la regla de negocio donde un destino solo puede recibir 3 mensajes cada 24 horas y tiene disponible un endpoint para obtener los mensajes que se enviaron a un destino concreto.

### Flujo general

Producer API → RabbitMQ → Consumer → MongoDB

Tecnologías utilizadas
-	Java + Spring Boot
-	MySQL
-	MongoDB
-	RabbitMQ
-	Docker Compose
-	Swagger/OpenAPI

## Ejecución del proyecto

1. Construcción de artefactos tanto en el ./producer como en el ./consumer

``` ./mvnw clean package ```
   
3. Levantar infraestructura y servicios

``` docker compose up --build ```

## Servicios disponibles
- Producer: http://localhost:8080
- Consumer: http://localhost:8081
- RabbitMQ: http://localhost:15672

## Swagger

- Producer: /swagger-ui.html
  <img width="2926" height="784" alt="image" src="https://github.com/user-attachments/assets/bcd92aaf-5a40-4dee-89af-ee73b7512603" />

- Consumer: /swagger-ui.html
  <img width="2926" height="784" alt="image" src="https://github.com/user-attachments/assets/fc34dbad-e93b-4803-bb68-0968592be473" />


## Respuestas cuestionario
1.
   Se tuvieron en cuenta los siguientes requerimientos no funcionales:
   - Desacoplamiento entre servicios
   - Procesamiento asíncrono
   - Mantenibilidad
   - Escalabilidad horizontal

   Sin embargo considero que se pueden agregar los siguientes requerimientos para construir una solución mucho mas robusta:
   - Pólitica de reintentos ante fallos en los mensajes enviados por rabbitmq
   - Reglas de observabilidad y monitoreo
     
2. Se aplicaron distintos patrones de diseño como:
   - Repository Pattern: Para mantener abstracciones entre la lógica de negocio y la capa de acceso a datos
   - DTO Pattern: Se crearon DTOs con sus validaciones para mantener una separación de capas, donde en las capas mas externas se realizan validaciones sin afectar el funcionamiento de los casos de uso
   - Dependency Injection: Gracias a Spring Boot y al concepto de inversión del control se facilita el uso de la inyección de dependencias sin tener que instancias clases de forma explicita

3. Considero imporatante que los servicios cuenten con health checks o alguna especie de circuit breaker en caso de que alguno falle y se le permita recuperarse. También sería bueno tener una estructura concreta para logs, donde se pueda moitorear de forma fácil errores en el sistema y por ultimo tener separación de ambientes tipo dev y prod para asegurar el correcto funcionamiento de algún feature antes de hacerlo público a los usuarios.
   
4. Para asegurar esto, se tuvieron en cuenta cosas como la separación de servicios en servicios pequeños y especificos, reduciendo así la cantidad de recursos usados en el servicio, dividiendo la carga, de modo que si un servicio es mas utilizado que otro, solo este va a tener que escalar, mientras que el otro puede funcionar con los recursos que tiene a su disposición. También gracias a los procesos asincronos es posible responder a un usuario de forma rapida y sin tener que esperar una respuesta del otro servicio, de modo que se entregan respuestas de forma rapida y liberando así recursos para nuevas peticiones.
   
5. Creo que en este tipo de sistemas es crucial asegurar el correcto manejo de los datos, por lo que para el servicio que lee los mensajes en la cola considero importante aplicar alguna politica de reintentos en caso de que el servicio este caido, para asegurar así la integridad de los datos con respecto a las acciones que realizan los usuarios, también puede ser importante agregr identificadores a cada uno de estos mensajes para no procesar el mismo mensaje mas de una vez. Por último también se podría agregar versionamiento a la API, en caso de que haya una evolución natural en la API sin afectar cosas que ya esten en funcionamiento.
   
6. Recomiendo un despligue basado en contenedores, donde cada servicio vive en un contenedor independiente y por lo tanto a sus propios recursos, puede ser bueno orquestar los contenedores por medio de Kubernetes donde gracias a metricas se defina en que momento se deben crer nuevas instancias de un servicio o como se va repartir la carga entre diferentes instancias.

7. Para este caso se uso una autenticación simple, teniendo en cuenta que no había usuarios y un admin con acceso a la app es quien envia estos mensajes, sin embargo si hubieran usuarios se podría aplicar una solución mas robusta como JWT o sesiones de usuario con algún tipo de refresh token para invalidar tokens previamente creados y si es necesario tener un api gateway expuesto a internet donde se centralicen los ednpoints disponibles en los servicios y cosas como la autorización y autenticación.
  
8. Se puede tener en cuenta la tasa de errores, el uso de CPU y memoria o incluso cosas como el tiempo de respuesta de la API, en el caso de AWS y servicios como CloudWatch se pueden configurar alertas que permitan actuar antes de que el uso de la CPU o la memoria lleguen a cierto tope por ejemplo y así actuar rapidamente y no afectar la experiencia de los usuarios.
   
9. Gracias a que el reto se desarrollo bajo una arquitectura de microservicios es posible escalar de forma horizontal agregando mas instancias de un servicio en especifico sin fetar al otro. Así mismo el uso de brokers de mensajeria como en este caso RabbitMQ permiten desacoplar la carga mientras que esta se procesa en backgroud sin que el usuario deba esperar.
   
10. En este caso solo se hicieron pruebas funcionales, sin embargo a futuro es importante agregar pruebas automatizadas como los son los tests unitarios para garantizar la funcionalidad de pequeñas unidades de código de manera controlada, así mismo hay que pensar en testear la comunicación entre los servicios para garantizar que su comunicación se da de forma efectiva y por último también considero importante agregar pruebas de carga, para ver como se comportan los servicios ante un alto trafico de usuarios.
    
11. Gracias al uso de arquitecturas como clean architecture las cuales permiten aislar el dominio de elementos externos y la separación de responsabilidades permitiendo a los desarrolladores agregar funcionalidades de forma constante sin afectar casos de uso existentes.     
