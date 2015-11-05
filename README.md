# Sistema de Control de Inventarios

## Requisitos previos:
 - Java JDK 8
 - MySQL Server

## Versión
2.0

![alt tag](screenshot.png)

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

 - Para conexiones remotas debe modificar el host del usuario de la BD, para ello ejecute el siguiente comando en el servidor MySQL del host:
```sql
update user set host='<remote_host>' where user='<user>' and host='<localhost>';
flush privileges;
```

 - Al ejecutar la aplicación es posible logearse con el usuario *admin* y contraseña *admin*
