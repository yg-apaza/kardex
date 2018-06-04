# Sistema de Control de Inventarios

![alt tag](screenshot.png)

## Requisitos previos:
 - Java JDK 8
 - MySQL Server

## Instalación - Desarrollador

 - Clonar el proyecto en NetBeans
 
 - Modificar el archivo  kardex/Kardex/conexion.dat, con sus datos de usuario, password y host. Por defecto el host es 'locahost', el usuario es 'root' y el password '123456'. No dejar espacios después del símbolo '=', además los datos deben empezar desde la primera línea sin dejar saltos de líneas antes. El host también puede ser la dirección IP de una máquina remota en red local. Ejemplo:
```sql
host=localhost
usuario=root
password=123456

No editar este archivo.
```
 - Ejecutar los scripts SQL en el servidor MySQL del host, en el siguiente orden: *script.sql*, *triggers.sql*, *vistas.sql*, *datos.sql*

 - Al ejecutar la aplicación es posible logearse con el usuario *admin* y contraseña *admin*
