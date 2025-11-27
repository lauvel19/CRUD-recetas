# 🍳 API de Recetas de Cocina

API REST completa para gestionar recetas de cocina, desarrollada con Spring Boot, MySQL y Docker.

## 🏗️ Arquitectura y Principios SOLID

### Principios Aplicados

1. **SRP (Single Responsibility Principle)**: Cada clase tiene una única responsabilidad
    - `Receta`: Solo representa el modelo de datos
    - `RecetaController`: Solo maneja peticiones HTTP
    - `RecetaUseCase`: Solo contiene lógica de negocio
    - `RecetaMapper`: Solo transforma objetos

2. **OCP (Open/Closed Principle)**: Abierto a extensión, cerrado a modificación
    - Fácil agregar nuevos casos de uso sin modificar existentes
    - Nuevos endpoints se agregan sin cambiar los existentes

3. **LSP (Liskov Substitution Principle)**: Las implementaciones pueden sustituirse
    - `JpaRepository` puede ser reemplazado por otra implementación

4. **ISP (Interface Segregation Principle)**: Interfaces específicas
    - `RecetaRepository` solo tiene métodos relacionados con Receta

5. **DIP (Dependency Inversion Principle)**: Dependencia de abstracciones
    - El caso de uso depende de la interfaz `RecetaRepository`, no de su implementación
    - El controlador depende del caso de uso, no de detalles de implementación

### Patrones de Diseño Aplicados

- **Repository Pattern**: Abstracción de la capa de datos
- **DTO Pattern**: Separación entre modelos de dominio y de transferencia
- **Mapper Pattern**: Transformación entre entidades y DTOs
- **Builder Pattern**: Construcción de objetos complejos (Lombok @Builder)
- **Exception Handling Pattern**: Manejo centralizado de excepciones

### Antipatrones Evitados

- ❌ God Object: Clases con responsabilidades bien definidas
- ❌ Magic Numbers/Strings: Uso de constantes y configuración
- ❌ Hard Coding: Sin datos quemados, todo configurable
- ❌ Anemic Domain Model: Lógica de negocio en casos de uso

## 🚀 Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL 8.0
- Docker & Docker Compose
- Maven
- Lombok
- phpMyAdmin

## 📋 Requisitos Previos

- JDK 17 o superior
- Maven 3.6+
- Docker y Docker Compose
- IntelliJ IDEA (recomendado)

## 🔧 Instalación y Ejecución

### Opción 1: Con Docker (Recomendado)

```bash
# 1. Clonar el repositorio
git clone <tu-repositorio>
cd recetas-api

# 2. Construir y ejecutar con Docker Compose
docker-compose up --build

# La aplicación estará disponible en:
# - API: http://localhost:8080/api/v1/recetas
# - phpMyAdmin: http://localhost:8081
```

### Opción 2: Ejecución Local

```bash
# 1. Asegúrate de tener MySQL corriendo localmente

# 2. Actualizar application.properties con tus credenciales de MySQL

# 3. Compilar y ejecutar
mvn clean install
mvn spring-boot:run
```

## 📡 Endpoints de la API

### Base URL: `http://localhost:8080/api/v1/recetas`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear una nueva receta |
| GET | `/` | Obtener todas las recetas |
| GET | `/{id}` | Obtener receta por ID |
| GET | `/buscar?keyword=` | Buscar recetas por nombre |
| GET | `/categoria/{categoria}` | Obtener recetas por categoría |
| GET | `/dificultad/{dificultad}` | Obtener recetas por dificultad |
| PUT | `/{id}` | Actualizar una receta |
| DELETE | `/{id}` | Eliminar una receta |

## 📝 Ejemplos de Uso

### Crear una Receta

```bash
curl -X POST http://localhost:8080/api/v1/recetas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pasta Carbonara",
    "ingredientes": "400g de pasta, 200g de panceta, 4 huevos, 100g de queso parmesano, pimienta negra",
    "instrucciones": "1. Cocinar la pasta. 2. Freír la panceta. 3. Mezclar huevos con queso. 4. Combinar todo y servir caliente.",
    "tiempoPreparacion": 30,
    "dificultad": "Media",
    "porciones": 4,
    "categoria": "Almuerzo"
  }'
```

### Obtener Todas las Recetas

```bash
curl http://localhost:8080/api/v1/recetas
```

### Buscar Recetas

```bash
curl "http://localhost:8080/api/v1/recetas/buscar?keyword=pasta"
```

### Actualizar una Receta

```bash
curl -X PUT http://localhost:8080/api/v1/recetas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pasta Carbonara Premium",
    "ingredientes": "400g de pasta, 200g de guanciale, 4 huevos, 100g de pecorino romano, pimienta negra",
    "instrucciones": "1. Cocinar la pasta al dente. 2. Freír el guanciale hasta dorar. 3. Mezclar huevos con queso. 4. Combinar todo rápidamente y servir.",
    "tiempoPreparacion": 35,
    "dificultad": "Media",
    "porciones": 4,
    "categoria": "Almuerzo"
  }'
```

### Eliminar una Receta

```bash
curl -X DELETE http://localhost:8080/api/v1/recetas/1
```

## 🗄️ Acceso a phpMyAdmin

1. Abrir navegador en: http://localhost:8081
2. Credenciales:
    - **Servidor**: mysql
    - **Usuario**: root
    - **Contraseña**: root
3. Seleccionar base de datos: `recetas_db`

## 🧪 Validaciones

La API incluye validaciones automáticas:

- **Nombre**: 3-200 caracteres, obligatorio
- **Ingredientes**: Mínimo 10 caracteres, obligatorio
- **Instrucciones**: Mínimo 20 caracteres, obligatorio
- **Tiempo de Preparación**: 1-1440 minutos
- **Dificultad**: Solo acepta "Fácil", "Media" o "Difícil"
- **Porciones**: 1-100 porciones
- **Categoría**: 3-100 caracteres, obligatorio

## 📊 Estructura de la Base de Datos

```sql
CREATE TABLE recetas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    ingredientes TEXT NOT NULL,
    instrucciones TEXT NOT NULL,
    tiempo_preparacion INT NOT NULL,
    dificultad VARCHAR(50) NOT NULL,
    porciones INT NOT NULL,
    categoria VARCHAR(100),
    fecha_creacion DATETIME NOT NULL,
    fecha_actualizacion DATETIME
);
```

## 🛑 Detener la Aplicación

```bash
# Si usas Docker Compose
docker-compose down

# Para eliminar también los volúmenes (datos)
docker-compose down -v
```

## 🔍 Logs y Debugging

```bash
# Ver logs de la aplicación
docker-compose logs -f recetas-api

# Ver logs de MySQL
docker-compose logs -f mysql
```

## ✅ Cumplimiento SonarQube

Este proyecto respeta las siguientes reglas de SonarQube:

- ✅ Sin código duplicado
- ✅ Nombres descriptivos de variables y métodos
- ✅ Métodos con responsabilidad única
- ✅ Manejo adecuado de excepciones
- ✅ Sin variables no utilizadas
- ✅ Sin imports no utilizados
- ✅ Logging apropiado
- ✅ Validación de entrada
- ✅ Sin código comentado
- ✅ Documentación JavaDoc en clases principales

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

## 👨‍💻 Autor

Desarrollado con ❤️ siguiendo las mejores prácticas de desarrollo de software.