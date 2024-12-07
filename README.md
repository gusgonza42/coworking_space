# Coworking Space

- - -

## 1. Descripción del proyecto

- - - 

## 2. Integrantes del Equipo

- - - 

## 3. Tecnologías Utilizadas

### Backend

### Frontend

- - - 

## 4. Herramientas de Colaboración y Organización

- - - 

## 5. Metodología de Trabajo

- - - 

## 6. Instrucciones para Configurar el Proyecto en Local

### Clonar el Repositorio

### Configuración del Backend (Spring Boot y Docker)

1. **Iniciar Docker**:
    - Asegúrate de tener Docker instalado y en ejecución en tu máquina, ya que Docker se utilizará para configurar y
      ejecutar los servicios necesarios para la base de datos.

2. **Ejecutar el Archivo Docker**:
    - En la raíz del repositorio, encontrarás el archivo `docker-compose.yml`.
    - Para construir y ejecutar los contenedores necesarios, abre una terminal en la raíz del proyecto y ejecuta:
      ```bash
      docker-compose up --build
      ```
      Alternativamente, si tienes el plugin de Docker instalado en IntelliJ, puedes hacer clic en **Run** desde el
      archivo `docker-compose.yml`.

3. **Ejecutar el Backend en IntelliJ**:
    - Ejecuta el archivo `CoreApplication` con la opción **Run** de IntelliJ para iniciar la aplicación. El archivo se
      encuentra en:
      ```plaintext
      `src/main/java/com/tienda/facil/core/CoreApplication.java`
      ```

4. **Verificar la Ejecución**:
    - Una vez iniciado Spring Boot, verifica que la aplicación esté funcionando correctamente accediendo a:
      ```plaintext
      http://localhost:8080
      ```
    - También puedes acceder a la documentación de la API si se configuró Swagger u OpenAPI en:
      ```plaintext
      http://localhost:8080/docs/swagger-ui.html
      ```
      ```plaintext
      http://localhost:8080/docs/swagger-ui/index.html#/
      ```

Con estos pasos, el backend se ejecutará y utilizará Docker para la base de datos MySQL, configurada según el archivo
`application.properties`.

### Configuración del Frontend (#)