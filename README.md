CONSIDERACIONES:

* El programa fue escrito en JAVA 11 y se ejecuta a través de sus test unitarios.
* Dado que se pidió que no se debía implementar ninguna interfaz ni salida por pantalla, decidí no implementar la ejecución. de mi preferencia, para ejecutarlo sería a través de un archivo que simule comandos, y si se me es requerido, lo puedo hacer con relativa facilidad.
* Dada la complejidad del ejercicio opte por no usar librerías externas como lombok que te ayudan a no tener que escribir getters y setters. La única librería externa que uso es Junit para hacer tos test unitarios.

SUPUESTOS:

* Dado ese punto de la consigna, es porque considero que los test son la prueba de su funcionamiento.
* Cuando un Usuario pide todas las Alertas que le llegaron, no significa que las haya leído, debe marcar cada alerta como tal para que sea considerada leída.
* Dos usuarios no pueden tener el mismo nombre, al mismo que dos tópicos no pueden tener el mismo nombre.
* Si una alerta no tiene fecha de expiración, estas no vencen.
* El sistema que se tiene para marcar si una alerta es dirigida a un usuario en un topic en específico o a un tópico en si es simplemente un booleano. Dado que el único comportamiento que posee es el siguiente `Se informa para cada alerta si es para todos los usuarios o para uno específico` y en el ejercicio se explica que no se debe imprimir por pantalla ni mostrar el contenido de ningún tipo.


Algunas decisiones de diseño:

* Las alertas son quienes deciden su orden dado una lista de distintas alertas, otra alternativa es que los usuarios y los tópicos lo hiciesen aprovechando la sobrecarga de constructores, pero si el día de mañana se agrega una nueva alerta o algún receptor nuevo, este código limita mucho la repetición de código.
* El administrador de alertas se encarga almacenar y de filtrar que alertas deben ser mostradas cuando se pidan todas las alertas validas, en caso de las alertas a tópicos, envía todas las alertas que no expiraron, y en caso de los usuarios, envía todas las alertas que no expiraron y no fueron marcadas como leídas.
* En los test de Topics, dado que en los Topics se crea una copia de las alertas para cada usuario registrado, para validar que a un usuario le llega las alertas correctas solo verifico el contenido. Consideré hacer una sobrecarga de los métodos Equals, pero dado que esa comparación seria solo utilizada en los test preferí solo validar que el contenido del cuerpo y el titulo sea el correcto. 

DIAGRAMAS:

A continuación, agrego un diagrama pseudo-UML para facilitar el entendimiento de las relaciones entre las clases.


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
