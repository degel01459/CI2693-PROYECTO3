public class edges extends connect {
  // Variables de Arista.
  private Nodo extremoInicial;
  private Nodo extremoFinal;

  // Crear Arista.
  public edges(String id, int peso, Nodo extremoInicial, Nodo extremoFinal) {
    super(id, peso);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  // Obtener Extremo1.
  public Nodo getExtremoInicial() {
    return this.extremoInicial;
  }

  // Obtener Extremo2.
  public Nodo getExtremoFinal() {
    return this.extremoFinal;
  }

  // Mostrar la arista.
  @Override
  public String toString() {
    return "Aristas: (" + extremoInicial.getNombre() + " -> " + extremoFinal.getNombre() + ") Caracteristica: "
        + getComun();
  }
}
