Esta clase es la que contiene la lista de naves. Es una lista privada 
para que no se pueda modificar desde fuera sin que dicha modificacion este controlada.

La controlo desde el metodo updarteCache mediante un reentrantLock para asegurarme de que 
solo un hilo pueda estar modificandola a la vez.

Como es un object, para poder enviar la lista luego, he hecho la clase 
cacheSerializable para que pueda implementar Serializable, y ademas 
para que lo que se le de a los clientes sea una copia de la lista, no la 
lista real.