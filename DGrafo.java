import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DGrafo implements Graph<Nodo> {
    // Declaramos las variables, arreglos y tablas que vayamos a utilizar
    private HashMap<String, Nodo> vertices;
    private ArrayList<edges> connect;
    int[][] matriz = null;

    public DGrafo() {
        // Constructor de la clase.
        vertices = new HashMap<>();
        connect = new ArrayList<>();
    }

    // Método para agregar un vértice al grafo
    public boolean add(Nodo vertex) {
        if (!vertices.containsKey(vertex.getNombre())) {
            // Agregar el vértice al hashtable
            vertices.put(vertex.getNombre(), vertex);
            // Vértice agregado exitosamente
            return true;
        }
        // El vértice ya existe en el grafo
        return false;
    }

    // Método para verificar si un vértice existe en el grafo dado su identificador
    public boolean contains(Nodo vertex) {
        if (vertices.containsKey(vertex.getNombre())) {
            // El vértice existe en el grafo
            return true;
        }
        // El vértice no existe en el grafo
        return false;
    }

    // Método para verificar si existe un lado entre dos vértices
    public boolean containsconnect(Nodo from, Nodo to) {
        // Iterar sobre todos los lados
        for (edges a : connect) {
            // Verificar si los vértices extremos coinciden en cualquier dirección
            if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)) {
                // Existe un lado entre los vértices
                return true;
            }
        }
        // No existe un lado entre los vértices
        return false;
    }

    public boolean connect(Nodo from, Nodo to, String comun) {
        // Esta función establece la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (!containsconnect(from, to)) {
            edges arista = new edges("" + from.getNombre() + to.getNombre() + "", 0, from, to);
            connect.add(arista);
            return true;
        }
        return false;
    }

    public boolean disconnect(Nodo from, Nodo to) {
        // Esta función elimina la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (contains(from) && contains(to) && containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)) {
                    connect.remove(a);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Nodo> getInwardEdges(Nodo to) {
        // Esta función devuelve una lista de vértices predecesores que tienen una
        // conexión con el vértice dado.
        List<Nodo> inwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoFinal().getNombre().equals(to.getNombre())) {
                inwardEdges.add(a.getExtremoInicial());
            }
        }
        return inwardEdges;
    }

    public List<Nodo> getOutwardEdges(Nodo from) {
        // Esta función devuelve una lista de vértices sucesores que tienen una conexión
        // con el vértice dado.
        List<Nodo> outwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getNombre().equals(from.getNombre())) {
                outwardEdges.add(a.getExtremoFinal());
            }
        }
        return outwardEdges;
    }

    public List<Nodo> getVerticesConnectedTo(Nodo vertex) {
        // Esta función devuelve una lista de vértices que tienen una conexión con el
        // vértice dado.
        List<Nodo> verticesConnectedTo = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getNombre().equals(vertex.getNombre())) {
                verticesConnectedTo.add(a.getExtremoFinal());
            } else if (a.getExtremoFinal().equals(vertex)) {
                verticesConnectedTo.add(a.getExtremoInicial());
            }
        }
        return verticesConnectedTo;
    }

    public List<Nodo> getAllVertices() {
        // Esta función devuelve una lista de todos los vértices del grafo.
        List<Nodo> allVertices = new ArrayList<>();
        for (String key : vertices.keySet()) {
            allVertices.add(vertices.get(key));
        }
        return allVertices;
    }

    public boolean remove(Nodo vertex) {
        // Esta función elimina un vértice del grafo.
        if (contains(vertex)) {
            vertices.remove(vertex.getNombre());
            return true;
        }
        return false;
    }

    public int size() {
        // Esta función devuelve el número de vértices en el grafo.
        return vertices.size();
    }

    @Override
    public String toString() {
        // Este método devuelve una representación en forma de cadena del grafo.
        StringBuilder sb = new StringBuilder();
        // Iterar sobre todos los vertices en el grafo
        for (Nodo a : vertices.values()) {
            sb.append("Nodo: ").append(a.getNombre()).append(" Altura: " + a.getAltura()).append("\n");
            if (a.getSup() != null) {
                sb.append(" Sup: ").append(a.getSup().getNombre()).append("\n");
            }
            if (a.getInf() != null) {
                sb.append(" Inf: ").append(a.getInf().getNombre()).append("\n");
            }
            if (a.getIzq() != null) {
                sb.append(" Izq: ").append(a.getIzq().getNombre()).append("\n");
            }
            if (a.getDer() != null) {
                sb.append(" Der: ").append(a.getDer().getNombre()).append("\n");
            }
        }
        // Iterar sobre todas las aristas en el grafo
        for (edges l : connect) {
            sb.append(l).append("\n");
        }
        // Devolver la representación en forma de cadena del grafo
        return sb.toString();
    }

    public boolean colocaAguaSube(Nodo vert, Nodo vertini) {
        boolean var = true;
        if (vert.getSup().getPerimetro() && vert.getAltura() < vert.getSup().getAltura()) {
            if (vert.getSup().getAltura() > vertini.getAltura()) {
                var = true;
                return var;
            } else {
                var = false;
                return var;
            }
        } else if (vert.getSup().getPerimetro() && vert.getAltura() >= vert.getSup().getAltura()) {
            var = false;
            return var;
        } else if (!vert.getSup().getPerimetro() && vert.getSup().getAltura() <= vertini.getAltura()) {
            var = colocaAguaSube(vert.getSup(), vertini);

        }
        return var;
    }

    public boolean colocaAguaBaja(Nodo vert, Nodo vertini) {
        boolean var = true;
        if (vert.getInf().getPerimetro() && vert.getAltura() < vert.getInf().getAltura()) {
            if (vert.getInf().getAltura() > vertini.getAltura()) {
                var = true;
                return var;
            } else {
                var = false;
                return var;
            }
        } else if (vert.getInf().getPerimetro() && vert.getAltura() >= vert.getInf().getAltura()) {
            var = false;
            return var;
        } else if (!vert.getInf().getPerimetro() && vert.getInf().getAltura() <= vertini.getAltura()) {
            var = colocaAguaBaja(vert.getInf(), vertini);

        }
        return var;
    }

    public boolean colocaAguaDer(Nodo vert, Nodo vertini) {
        boolean var = true;
        if (vert.getDer().getPerimetro() && vert.getAltura() < vert.getDer().getAltura()) {
            if (vert.getDer().getAltura() > vertini.getAltura()) {
                var = true;
                return var;
            } else {
                var = false;
                return var;
            }
        } else if (vert.getDer().getPerimetro() &&  vert.getAltura()  >= vert.getDer().getAltura()) {
            var = false;
            return var;
        } else if (!vert.getDer().getPerimetro() && vert.getDer().getAltura() <= vertini.getAltura()) {
            var = colocaAguaDer(vert.getDer(), vertini);

        }
        return var;
    }

    public boolean colocaAguaIzq(Nodo vert, Nodo vertini) {
        boolean var = true;
        if (vert.getIzq().getPerimetro() && vert.getAltura() < vert.getIzq().getAltura()) {
            if (vert.getIzq().getAltura() > vertini.getAltura()) {
                var = true;
                return var;
            } else {
                var = false;
                return var;
            }
        } else if (vert.getIzq().getPerimetro() && vert.getAltura()>= vert.getIzq().getAltura()) {
            var = false;
            return var;
        } else if (!vert.getIzq().getPerimetro() && vert.getIzq().getAltura() <= vertini.getAltura()) {
            var = colocaAguaIzq(vert.getIzq(), vertini);

        }
        return var;
    }

    public boolean verificarVecino(Nodo vertex) {
        // esta funcion evaluara si un vecino del nodo actual es valido

        if (vertex.getDer() != null && vertex.getIzq() != null && vertex.getSup() != null && vertex.getInf() != null) {
            // si esta a este if es porque ninguno de sus vecinos es un borde de la matriz
            return true;
        }
        return false;
    }

    public int subesube(Nodo vertex, int menormayor){
        if (vertex.getSup().getPerimetro() || vertex.getSup().getAltura() < vertex.getAltura()){
            if (vertex.getSup().getAltura() > vertex.getAltura()){
                menormayor = vertex.getSup().getAltura();
                return menormayor;
            }
            return menormayor;
        }
        if (vertex.getSup().getAltura() >= vertex.getAltura()) {
            menormayor = vertex.getSup().getAltura();
            menormayor = subesube(vertex.getSup(), menormayor);
        }
        return menormayor;
    }

    public int bajabaja(Nodo vertex, int menormayor){
        if (vertex.getInf().getPerimetro() || vertex.getInf().getAltura() < vertex.getAltura()){
            if (vertex.getInf().getAltura() > vertex.getAltura()){
                menormayor = vertex.getInf().getAltura();
                return menormayor;
            }
            return menormayor;
        }
        if (vertex.getInf().getAltura() >= vertex.getAltura()) {
            menormayor = vertex.getInf().getAltura();
            menormayor = bajabaja(vertex.getInf(), menormayor);
        }
        return menormayor;
    }
    public int derder(Nodo vertex, int menormayor){
        if (vertex.getDer().getPerimetro() || vertex.getDer().getAltura() < vertex.getAltura()){
            if (vertex.getDer().getAltura() > vertex.getAltura()){
                menormayor = vertex.getDer().getAltura();
                return menormayor;
            }
            return menormayor;
        }
        if (vertex.getDer().getAltura() >= vertex.getAltura()) {
            menormayor = vertex.getDer().getAltura();
            menormayor = derder(vertex.getDer(), menormayor);
        }
        return menormayor;   
    }
    public int izqizq(Nodo vertex, int menormayor){
        if (vertex.getIzq().getPerimetro() || vertex.getIzq().getAltura() < vertex.getAltura()){
            if (vertex.getIzq().getAltura() > vertex.getAltura()){
                menormayor = vertex.getIzq().getAltura();
                return menormayor;
            }
            return menormayor;
        }
        if (vertex.getIzq().getAltura() >= vertex.getAltura()) {
            menormayor = vertex.getIzq().getAltura();
            menormayor = izqizq(vertex.getIzq(), menormayor);
        }
        return menormayor;   
    }

    public int verificarAlturaVecino(Nodo vertex) {
        // esta funcion retorna un valor entero que sera la altura menor de los vecino
        // que es más alta que el vertice actual, si existe

        // arreglo donde se almacenan las 4 alturas de los vertices adyacente al actual
        int[] menorAlturaMasGrande = new int[4];
        int menorAltura;
        // asignamos la altura de cada vertice a una posición en el arreglo

        if (verificarVecino(vertex) && colocaAguaSube(vertex, vertex) && colocaAguaBaja(vertex, vertex) && colocaAguaDer(vertex, vertex)
                && colocaAguaIzq(vertex, vertex)) {
            menorAlturaMasGrande[2] = subesube(vertex, vertex.getSup().getAltura());
            menorAlturaMasGrande[3] = bajabaja(vertex, vertex.getInf().getAltura());
            menorAlturaMasGrande[0] = derder(vertex, vertex.getDer().getAltura());
            menorAlturaMasGrande[1] = izqizq(vertex, vertex.getInf().getAltura());            
        } else {
            menorAltura = vertex.getAltura();
            return menorAltura;
        }
        // ordenamos el arreglo del mas pequeño al mas grande
        Arrays.sort(menorAlturaMasGrande);
        menorAltura = menorAlturaMasGrande[0];
        // recorremos el arreglo para buscar el numero mayor, ya que ese seria su altura
        for (int i = 0; i < 4; i++) {
            if (menorAltura <= vertex.getAltura()) {
                menorAltura = menorAlturaMasGrande[i];
            } else if (menorAltura > vertex.getAltura()) {
                // si ya encontramos una altura que sea más grande que el vertice dado, cerramos
                // el ciclo
                break;
            }
        }
        // verificamos que la menor altura más grande que el vertice dado exista, en
        // caso contrario la menor altura es el mismo vertice
        if (menorAltura < vertex.getAltura()) {
            menorAltura = vertex.getAltura();
        }
        return menorAltura;
    }

    public int calcularAguaMaxima() {
        int aguaMaxima = 0;
        int alturavecinos = 999;
        int alturanodo = 999;
        for (Nodo x : vertices.values()) {
            if (!x.getVisitado() && verificarVecino(x)) {
                x.setVisitado(true);
                alturanodo = x.getAltura();
                alturavecinos = verificarAlturaVecino(x);
                if (alturanodo < alturavecinos) {
                    aguaMaxima = aguaMaxima + alturavecinos - alturanodo;
                    if (!x.getSup().getPerimetro()) {
                        aguaMaxima = AguaMaximaCola(x.getSup(), x, alturanodo, alturavecinos, aguaMaxima);
                    }
                    if (!x.getInf().getPerimetro()) {
                        aguaMaxima = AguaMaximaCola(x.getInf(), x, alturanodo, alturavecinos, aguaMaxima);
                    }
                    if (!x.getDer().getPerimetro()) {
                        aguaMaxima = AguaMaximaCola(x.getIzq(), x, alturanodo, alturavecinos, aguaMaxima);
                    }
                    if (!x.getIzq().getPerimetro()) {
                        aguaMaxima = AguaMaximaCola(x.getDer(), x, alturanodo, alturavecinos, aguaMaxima);
                    }
                }
            }
        }

        return aguaMaxima;
    }

    public int AguaMaximaCola(Nodo x, Nodo y, int alturaNodo, int alturavecinos, int aguaMaxima) {
        if (x.getAltura() <= y.getAltura() && !x.getVisitado()) {
            int alturanodo2 = x.getAltura();
            int alturavecinos2 = verificarAlturaVecino(x);
            if (alturavecinos < alturavecinos2) {
                aguaMaxima = aguaMaxima + alturavecinos - alturanodo2;
                x.setVisitado(true);
                if (!x.getSup().getPerimetro()) {
                    AguaMaximaCola(x.getSup(), x, alturaNodo, alturavecinos, aguaMaxima);
                    return aguaMaxima;
                }
                if (!x.getInf().getPerimetro()) {
                    AguaMaximaCola(x.getInf(), x, alturaNodo, alturavecinos, aguaMaxima);
                    return aguaMaxima;
                }
                if (!x.getDer().getPerimetro()) {
                    AguaMaximaCola(x.getIzq(), x, alturaNodo, alturavecinos, aguaMaxima);
                    return aguaMaxima;
                }
                if (!x.getIzq().getPerimetro()) {
                    AguaMaximaCola(x.getDer(), x, alturaNodo, alturavecinos, aguaMaxima);
                    return aguaMaxima;
                }
            }
            if (alturavecinos2 < alturavecinos) {
                aguaMaxima = aguaMaxima + alturavecinos2 - alturanodo2;
                x.setVisitado(true);
                if (!x.getSup().getPerimetro()) {
                    AguaMaximaCola(x.getSup(), x, alturaNodo, alturavecinos2, aguaMaxima);
                    return aguaMaxima;
                }
                if (!x.getInf().getPerimetro()) {
                    AguaMaximaCola(x.getInf(), x, alturaNodo, alturavecinos2, aguaMaxima);
                    return aguaMaxima;
                }
                if (!x.getDer().getPerimetro()) {
                    AguaMaximaCola(x.getIzq(), x, alturaNodo, alturavecinos2, aguaMaxima);
                    return aguaMaxima;
                }
                if (!x.getIzq().getPerimetro()) {
                    AguaMaximaCola(x.getDer(), x, alturaNodo, alturavecinos2, aguaMaxima);
                    return aguaMaxima;
                }
            }
        }
        return aguaMaxima;
    }

    public boolean cargarGrafo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            List<String> lineas = br.lines().collect(Collectors.toList());
            matriz = new int[lineas.size()][];
            for (int i = 0; i < lineas.size(); i++) {
                String[] valores = lineas.get(i).split(" ");
                matriz[i] = new int[valores.length];
                for (int j = 0; j < valores.length; j++) {
                    matriz[i][j] = Integer.parseInt(valores[j]);
                    Nodo x = new Nodo("" + i + "," + j + "");
                    add(x);
                    x.setAltura(matriz[i][j]);
                    if (i == 0 || i == matriz.length - 1 || j == 0 || j == matriz[i].length - 1) {
                        x.setPerimetro(true);
                    }
                }
            }for(

    int i = 0;i<matriz.length;i++)
    {
        for (int j = 0; j < matriz[i].length; j++) {
            Nodo a = vertices.get("" + i + "," + j + "");
            if (i == 0) {
                if (j == 0) {
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                } else if (j == matriz[i].length - 1) {
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                } else {
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                }
            } else if (i == matriz.length - 1) {
                if (j == 0) {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                } else if (j == matriz[i].length - 1) {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                } else {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                }
            } else if (j == 0) {
                if (i == 0) {
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                } else if (i == matriz.length - 1) {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                } else {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
                }
            } else if (j == matriz[i].length - 1) {
                if (i == 0) {
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                } else if (i == matriz.length - 1) {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                } else {
                    a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                    a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                    a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                }
            } else {
                a.setSup(vertices.get("" + (i - 1) + "," + j + ""));
                a.setInf(vertices.get("" + (i + 1) + "," + j + ""));
                a.setIzq(vertices.get("" + i + "," + (j - 1) + ""));
                a.setDer(vertices.get("" + i + "," + (j + 1) + ""));
            }

            // aca
        }
    }return true;}catch(
    IOException e)
    {
            e.printStackTrace();
        }return false;
}}
