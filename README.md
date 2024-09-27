#  app-registro-usuario

msa-user-app



## Descripción

Microservicio Spring Boot que publica 3 endpoints para las funcionalidades de (obtener un Jwt token, creación de usuario y listado de usuarios creados).

## Requerimientos para ejecución

Para ejecutar el micro se requiere que en la maquina este instalado lo siguiente:
- Java 17 o superior
- Gradle 7.3 o superior

## Pasos para ejecución

Para correr el micro se requiere lo siguiente:
- Clonar el repositorio (git clone https://github.com/csantillan1984/nisum-test.git)
- Ubicarse en el directorio nisum-test
- Una vez dentro del directorio ubicarse en el directorio msa-user-app
- Ejecutar linux(./gradlew bootRun) windows (gradlew bootRun)


## Documentación de apis
SwaggerDoc: http://localhost:8080/doc/swagger-ui/index.html#/

### Api de generación de token

- **Métodos funcionando**: `GET`
- **Ruta**: `/auth/login?user=nisum`
- **Descripción**: devuelve un token activo para la gestiòn de usuarios

#### Respuesta

Una vez que hacemos una solicitud al endpoint nos responde el siguiente json:

```json
{
  "user": "nisum",
  "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJuaXN1bS1qd3QiLCJzdWIiOiJuaXN1bSIsImF1dGhvcml0aWVzIjpbIkFETV9VU0VSIl0sImlhdCI6MTcyNzQ1OTM3MSwiZXhwIjoxNzI3NDYyOTcxfQ.PmPzUZuBYQAHIsKf5r-L8uTb5StZrPJxv3gTwdbTOPyXIhgP2i_s05lVqCiPzrAGCtPo8pxqXM8LiEkbcrt4fA"
}

```

### Api de gestión de usuarios

- **Métodos funcionando**: `POST`,`GET`
- **Ruta**: `/api/v1/user`
- **Descripción**: El método post crea un usuario y el método get lista todos los usuarios creados.

#### Request para creación de usuario método post

El request debe tener el siguiente formato json:

```json
{
    "name": "Christian Santillan",
    "email": "c_santillan2008@hotmail.es",
    "password": "Msantillan",
    "phones": [
        {
            "number": "022471025",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
Hay que tener cuidado con el password dado que el patron es parametrizable ahora solo deja letras y espacion en blanco podemos cambiarlo en el application.yml


#### Request para listado de usuario método get
En este caso solo hay que mandar las cabeceras (Content-Type: application/json, Authorization: token genrado con el endpoint de generación de token). La formato de respuesta es:

```json
[
    {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.com",
        "phones": [
            {
                "number": "022471025",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
]
```
## Coleccion Postman
Se adjunta tambien la colección postman para que los endpoints sean probados a continuación se detalla la siguiente secuencia:
- Restaurar la colección en postman (https://github.com/csantillan1984/nisum-test/blob/master/nisum.postman_collection.json)
- Ejecutar los apis en el siguiente orden:
  - el api de autenticación una vez ejecutado debemos copair el token generado del campo token de la respuesta ej: **Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJuaXN1bS1qd3QiLCJzdWIiOiJuaXN1bSIsImF1dGhvcml0aWVzIjpbIkFETV9VU0VSIl0sImlhdCI6MTcyNzQ3NDE0OSwiZXhwIjoxNzI3NDc3NzQ5fQ.ljsA2BUL-Kh-Z-8kL60A_CeVl24ZiaxKryZIYYwVau14zPbl4vDVs2eJBdPZfK1pc4TmFDhqTKgeYUMAHlFoOQ**
  - Luego copiar el token y ponerlo en la cabecera **Authorization** de los endpoints create-user y findAll

## Arquitectura
link: (https://github.com/csantillan1984/nisum-test/blob/master/arquitectura.pdf)
