# Movie App
Movie App es una aplicacion de android en la cual podemos ver las peliculas y series que se encuentran almacenadas en el api de themoviedb, aqui tenemos la facilidad de poder cargar las peliculas y series populares, asi como tambien tener la capacidad de agregar a favoritos dichas peliculas para luego poder consultarlas en caso de requerirlo.
## Features

- Cargar el listado de peliculas y series.
- ver el detalle de cada pelicula y/o serie.
- Interfaz intuitiva y de facil uso.
- Guardar en base de datos local, las peliculas favoritas.
- Conexion directa al api de themoviedb, con el cual tenemos la capacidad de cargar las peliculas y series.
- Desarrollo realizado utilizando RXJava y RXAndroid, asi como manejo de estados con el fin de evitar errores futuros en la aplicacion.


## Tech

- [Android Studio] - IDE utilizado para el desarrollo en android
- [Kotlin] - Lenguaje de programacion que nos provee de muchas facilidades al momento de desarrollar un aplicacion en android.
- [Rxandroid-RxJava] - Libreria que nos ayuda con todo el manejo de injeccion de dependencias y poder realizar un desarrollo reactivo.
- [Androidx] - Ultima version de librerias de soporte de Android la cual nos provee de muchas funcionalidades nuevas y otras mejoradas con respecto a la version anterior.
- [RetroFit] - Libreria que nos facilita el desarrollo de los request y llamadas al API/
- [Courutines-Room] - Esta librería nos ayuda a administrar las llamadas a la base de datos SQLite, para evitar bloqueos en el proceso principal de la aplicación.
- [Glide] - es un marco de carga de imágenes, esto nos ayuda a poder cargar de forma eficiente los posters de las series y películas que se pueden ver en la app.
- [Timber] - Esta librería es utilizada para loguear los cambios y/o errores dentro de la app.

## Permissions
La apllicacion al momento de correr va a requerir de los siguientes permisos:
- Full Network Access.
- View Network Connections.
- Run at startup.
- Read and write access to external storage.

## Installation

Para la instalacion y/o poder correr el proyecto, realizar los siguientes pasos:
- Descargar el codigo fuente.
- Abrir codigo fuente con Android Studio.
- Una vez instaladas las dependencias correr la aplicacion desde el IDE.

## Api

En la aplicacion se utiliza el siguiente API 
| Api | Documentacion |
| ------ | ------ |
| themoviedb | [https://www.themoviedb.org/documentation/api][PlDb] |


## Development

Para el desarrollo de la aplicacion se utilizo una arquitectura MVVM, con la cual nos apoyamos del uso de el desarrollo reactivo utilizando RXandroid. la aplicacion consta de un manejo de estados para poder gestionar todo el ciclo de los request realizados al API, asi como tambien para manejar las solicitudes a la base de datos de la aplicacion.

