# 🎮 Game Leaderboard API (Spring Boot + Redis)

Este proyecto es un servicio de backend desarrollado con **Java Spring Boot** que gestiona tablas de clasificación (leaderboards) en tiempo real para videojuegos. Debido a la naturaleza altamente dinámica de las puntuaciones, el sistema utiliza **Redis** como base de datos en memoria para garantizar un rendimiento óptimo y una ordenación automática ultrarrápida.

---

## 🚀 Arquitectura y Tecnologías

El proyecto sigue una arquitectura limpia dividida en **3 capas principales**, comunicadas de la siguiente forma:

* **Model**: Contiene la clase `Jugador`, que actúa como el modelo de datos básico (atributos: `nombre` y `puntuación`).
* **Service**: Contiene la lógica de negocio. Se comunica directamente con Redis utilizando `StringRedisTemplate` para gestionar las estructuras de datos nativas.
* **Controller**: Expone una API REST estructurada que interactúa con el cliente mediante peticiones HTTP y respuestas estandarizadas con `ResponseEntity`.

### Estructura de Datos en Redis
Las tablas se almacenan utilizando la estructura de datos **Sorted Set (ZSET)** de Redis:
* **Key (Clave):** El identificador del juego (ej. `ranking:juego1`).
* **Value (Valor):** Un conjunto ordenado automáticamente donde el *miembro* es el nombre del jugador y el *score* es su puntuación (ej. `{"Pepe": 500.0}`).

---

## 📌 API Endpoints

Todas las respuestas de la API siguen el estándar de Spring Boot y devuelven un objeto `ResponseEntity` con el código de estado HTTP correspondiente.

### Consultas (GET)

| Método | Endpoint | Descripción | Respuesta |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/ranking/{juego}` | Obtiene todos los jugadores de un juego específico. | `List<Jugador>` |
| **GET** | `/api/ranking/juegos` | Obtiene el listado de todos los juegos registrados. | `Set<String>` |
| **GET** | `/api/ranking/{juego}/{nombre}` | Obtiene los datos de un jugador concreto en un juego. | `Jugador` |
| **GET** | `/api/ranking/jugador/{nombre}` | Obtiene todos los juegos y puntuaciones de un jugador. | `Map<String, Double>` |
| **GET** | `/api/ranking/todos` | Obtiene los nombres de todos los jugadores del sistema. | `Set<String>` |

### Operaciones (POST, PUT, DELETE)

* **POST** `/api/ranking/{juego}`  
    Inserta un nuevo jugador en el juego indicado. Requiere JSON en el Body.
* **PUT** `/api/ranking/{juego}`  
    Actualiza la puntuación de un jugador existente en el juego indicado. Requiere JSON en el Body.
* **DELETE** `/api/ranking/{juego}/{jugador}`  
    Elimina a un jugador específico del ranking de un juego.

### Estructura del JSON (Body)
Para las peticiones de inserción (**POST**) y actualización (**PUT**), el formato del cuerpo debe ser el siguiente:

```json
{
  "nombre": "Pepe",
  "puntuación": 200.0
}
```

# 📦 Despliegue con Docker

El proyecto está completamente contenerizado mediante Docker y se compone de dos servicios unificados en un archivo docker-compose.yml:

    Contenedor de Redis: Base de datos persistente en memoria.

    Contenedor de Spring Boot: Entorno de ejecución de la aplicación compilado mediante Maven.

## Estructura del Proyecto

Para que el despliegue funcione correctamente, los archivos deben respetar la siguiente estructura de directorios:
```
📁 proyecto/
├── 📄 docker-compose.yml 
└── 📁 Springboot/ 
    └── 📁 proyectoBAE/ 
        ├── 📄 Dockerfile 
        ├── 📄 pom.xml 
        └── 📁 src/
````

  ## Instrucciones de Inicio

  1. Abre una terminal y sitúate en la raíz del directorio principal (📁 proyecto/).

  2. Ejecuta el siguiente comando para compilar y levantar todo el entorno en segundo plano:

```Bash

docker compose up -d
```

Una vez que termine el proceso, la API estará lista y escuchando peticiones en el puerto configurado (por defecto 8080), totalmente conectada al servidor de Redis.
