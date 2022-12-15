Luke, bb8 y los pilotos son los distintos tipos de cliente que tendra nuestro programa, 
y cada uno tiene un codigo numerico asignado entero. 

Los pilotos se conectan, le mandan un 0 al servidor y 
espera que el servidor le devuelva un 0. Si no lo hace es que algo ha ido mal por lo que 
cierra la conexion. Si lo recibe, le pasa al servidor su nave para que la inserte y espera la respuesta 
del servidor de que se ha insertado. 

BB8 le manda un 2 y espera recibir lo mismo, en caso contrario, pasa lo mismo que con los pilotos. 
Cuando la respesta del servidor es correcta, espera a que este le de la cantidad de naves y misiles, y posteriormente 
las printea por pantalla. 

Luke le manda un 1 y lo mismo que los otros dos. 
Cuando se conecta correctamente espera que el servidor le pase la lista 
de naves y una vez la tiene las printea por pantalla.