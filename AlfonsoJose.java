public class AlfonsoJose {
    public static void main(String[] args) {
        DGrafo grafo = new DGrafo();
        String archivo = "atlantis.txt";
        grafo.cargarGrafo(archivo);
        System.out.println(grafo.calcularAguaMaxima());

    }
}