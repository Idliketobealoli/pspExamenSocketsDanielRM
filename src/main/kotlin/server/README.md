El server es un serverSocket al que le pasamos el puerto, 
en este caso 6969. Una vez inicializado escuchará 
eternamente a los clientes, aceptando sus conexiones y delegandolas 
a GestorClientes en un hilo a parte.

Este gestor de clientes primero verificara el tipo de 
cliente que ha recibido. Si recibe un 0 significa que 
ha recibido un piloto, por lo que le manda un 0 para que el piloto 
sepa que le esta atendiendo correctamente, le pide la nave y 
una vez la tiene, actualiza la cache de naves, pasandole por ultimo la cadena "datos insertados" 
al piloto. 

Si recibe un 1 sabrá que se trata de luke, por lo que 
primero le mandara otro 1 para que luke sepa que esta siendo atendido, 
luego creara una copia serializable de la cache (ver readme de cache) 
y le mandara dicha copia. 

Si recibe un 2 sabrá que es bb8, por lo que le mandara otro 2 para que 
bb8 sepa que esta siendo atendido y acto seguido cogera 
una copia de la cache y procedera a contar el numero de naves 
registradas mediante cacheSerial.cache.size, 
y acto seguido contara el numero de misiles recorriendo la lista nave por 
nave y agregando el numero de misiles de cada nave al total de misiles. 
Una vez tenga las dos cosas calculadas, le enviara a bb8 el numero de naves y 
por ultimo el numero de misiles.

Si recibe cualquier otro numero significa que no se trata de ninguno de estos 
tipos de usuarios, por lo que para evitar que sea un posible imperial el que se conecte, cierra la 
gestion de este cliente. 

Si una excepcion se produjera, intenta finalmente cerrar el buffer de entrada de haber estado abierto.