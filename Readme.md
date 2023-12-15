Angel Rodriguez 15-11669
Kevin Briceño 15-11661

Proyecto III: Mundo Cubo

Descripción general: En el popular videojuego Mundo Cubo todo está hecho de cubos. La tierra, los arboles, el agua y hasta los animales
están compuestos por formas cúbicas con texturas pixeladas.

Objetivo: Actualmente Alfonso José se encuentra absorto en su proyecto más ambicioso hasta la fecha, construir la Ciudad Perdida de la Atlántida en Mundo Cubo. Se desea crear una implementación que permita calcular cuantos cubos de agua necesita para cubrir la cuidad de atlantis sin que se desborde



Implementación: Se creó la clase DGrafo.java, en ella utilizaremos una matriz de adyacencias que almacenará cada altura de los nodo del grafo; además, tendremos una clase que almacenará los objetos.

Contenido:

1. DGrafo.java: como se explica anteriormente, esta clase tendrá la tarea de tener todas la funciones necesaria para manejar la interfaz de un grafo. No solo contiene la matriz, sino que, además, contiene los métodos de añadir un nodo, eliminar un nodo, buscar si un nodo está contenido, conectar y desconectar dos nodos, colocaAgua y funciones de verificacion, nodos adyacentes a un nodo dado, tamaño del grafo, predecesores y sucesores, cargarGrafo, verificarVecino, verificarAlturaVecino, calcularAguaMaxima, AguaMaximaCola.

	1.1 CargarGrafo() es una funcion leera el archivo .txt tomará la matriz y almacenara cada posicion (i,j) como el id para cada nodo en Nodo.java y posteriormente en la hashTable. El valor 	numérico será la representación de la altura asiganada a cada posición (i,j)

	1.2 calcularAguaMaxima() es la función principal, en ella se estarán llamando las funciones recursivas que recorren la matriz y determinan la altura de los vecinos para así poder calcular cuanto cubos de agua puede tener el vertices actual; para finalmente, sumarlo todo y arrojar ese reasultado
	
	1.3 verificarAlturaVecino(): es una funcion que indica, recursivamente cual es la altura a tomar en cuenta para calcular la cantidad de cubos necesarion para ese nodo.

	1.4 verificarVecino: es una función que devuelve true o false si se posicionan en algun perimetro de la matriz

2. Graph.Java en la interfaz principal, a patir de alli se harán todas las llamadas correspondientes y se podrá hacer uso de los método mencionados en adjacencyListGraph

3. AlfosoJose.java: Es la clase ejecuta todo el programa, hace las llamadas correspondiente a las demás clases, contiene el metéto main.

4. Nodo.Java: esta clase contiene todos los parametros de un Nodo, como la altura, el estatus, si ha sido visitado o no.



