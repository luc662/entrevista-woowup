CONSIDERACIONES:

El programa fue escrito en JAVA 11 y se ejecuta a traves de sus test unitarios.
Dado que se pidio que no se debia implementar ninguna interfaz ni salida por pantalla, decidi no implementar la ejecucion. de mi preferencia, para ejecutarlo seria a traves de un archivo que simule comandos, y si se me es requerido, lo puedo hacer con relativa facilidad.
SUPUESTOS:

Dado ese punto de la consigna, es por que considero que los test son la prueba de su funcionamiento.
Cuando un Usuario pide todas las Aertas que le llegaron, no significa que las haya leido, debe marcar cada alerta como tal para que sea considerada leida.
Dos usuarios no pueden tener el mismo nombre, al mismo que dos topicos no pueden tener el mismo nombre.
si una alerta no tiene fecha de expiracon, estas no vencen.
EL sitema que se tiene para marcar si una alerta es dirigida a un usuario en un topic en especifico o a un topico en si es simplemente un booleano. Dado que el unico comportamiento que posee es el siguiente `Se informa para cada alerta si es para todos los usuarios o para uno específico` y en el ejercicio se explica que no se debe imprimir por pantalla ni mostrar el contenido de ningun tipo.

DIAGRAMAS:

A continuacion agrego un diagrama pseudo-UML para facilitar el entendimiento de las relaciones entre las clases.

![diagrama de clases pseudo-UML](https://raw.githubusercontent.com/luc662/entrevista-woowup/main/diagrama%20estrucural.png)

Ejercicio SQL
Escribir una consulta SQL que traiga todos los clientes que han comprado en total más de 100,000$ en los últimos 12 meses usando las siguientes tablas: 

Clientes: ID, Nombre, Apellido

Ventas: Fecha, Sucursal, Numero_factura, Importe, Id_cliente

```
SELECT c.ID, c.Nombre,c.Apellido, SUM(Importe) FROM Clientes as c 
inner join Ventas as v on c.ID = v.Id_cliente
WHERE n.fecha < CURRENT_DATE - INTERVAL '128 months'
group by c.ID, c.Nombre, c.Apellido
HAVING SUM(Importe) > 100000;
```
