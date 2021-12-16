### donis_guerra 

# Reglas
* Se reparte el mazo entre todos los jugadores
* En cada turno, todos los jugadores sacan la primer carta de su mazo y la muestran
* La carta más alta gana el turno, y el ganador se guarda todas las cartas de los demás jugadores en su mazo
  * Si más de un jugador saca la misma carta, siguen tirando hasta desempatar
* Cuando un jugador se queda sin mazo y no puede tirar su carta, pierde
* El último jugador, que se queda con todo el mazo, es el ganador

# Como jugar
1. Crear un nuevo juego indicando el nombre de la partida y de los jugadores.

```
POST /juegos

body:
{
    "identificacion": "nombre_juego",
    "jugadores": [
                    {
                        "nombre": "nombre_jugador1"
                    },
                    {
                       "nombre": "nombre_jugador2"
                    }
                 ]
}
```

2. Inicializar el juego. Esto reparte el mazo entre los jugadores.

```
POST /juegos/<id>/inicio
```

3. Pasar turnos. Se puede pasar de a un turno, o de a varios a la vez enviando 
la cantidad de turnos que se desean pasar.

```
POST /juegos/<id>/turno

o

POST /juegos/<id>/turno?cantidad=<int>
```

* Se puede ver el estado de un juego, que muestra los jugadores
y la cantidad de cartas que tiene cada uno actualmente.

```
GET /juegos/<id>/jugadores
```

* Se pueden ver los jugadores activos o inactivos (ya perdieron en 
un turno anterior) en un juego.

```
GET /juegos/<id>/jugadores?activo=<bool>
```

* Se pueden ver todos los turnos que pasaron en un juego, viendo
el ganador de cada uno y si el juego se termino.

```
GET /juegos/<id>/turnos
```

* Se pueden ver todos los juegos terminados o todavia activos.

```
GET /juegos/?terminado=<bool>
```