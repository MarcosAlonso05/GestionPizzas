# GestionPizzas

---

## Enlace al repositorio

```
https://github.com/MarcosAlonso05/GestionPizzas
```

---

Este proyecto es una aplicación de consola escrita en Java para gestionar pedidos de pizza, incluyendo usuarios, pedidos y pagos. 
Utiliza una base de datos MySQL que puede ejecutarse fácilmente mediante Docker.

## Características

- Registro y visualización de usuarios
- Creación y consulta de pedidos
- Registro y visualización de pagos
- Eliminación de usuarios y pedidos
- Actualización del estado de un pedido
- Uso de JDBC para interactuar con MySQL
- Base de datos montable automáticamente con Docker

## Ejecución de la base de datos con Docker Compose

1. Asegúrate de tener Docker y Docker Compose instalados.
2Ejecuta el siguiente comando en la raíz del proyecto:

```bash
docker-compose up -d
```