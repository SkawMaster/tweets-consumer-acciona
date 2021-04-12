# tweets-consumer

Para arrancar el microservicio, se lanza la ejecución de:
	mvn clean install spring-boot:run
	
	Se verá por consola el número de tweets que se han cargado en Base de datos.

Para probar los recursos del api utilizaremos el api de swagger que nos permite lanzar las llamadas de forma más cómoda:
	http://localhost:8080/tweets-consumer/swagger-ui.html
	
El proceso lógico de prueba sería primero atacar al recurso de /tweets sin seleccionar query params para obtener todos los registros
y ver que se han cargado.

El segundo sería atacar al de validacion /tweets/{id} para marcar como validado un tweet.

El tercer paso sería atacar al recurso de /tweets seleccionando el usuario del tweet que acabamos de validar para ver que se ha actualizado
el valor del campo validación.

El cuarto paso sería atacar al recruso /hashtags con un valor de 1 a 50 para obtener los hastags más usados en el mundo en ese instante.