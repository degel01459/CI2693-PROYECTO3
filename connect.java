
public abstract class connect {
  // Clase abstracta

  // Propiedades de la clase
  private String id;
  private int comun;

  public connect(String id, int peso) {
    // Constructor de la clase abstracta
    this.id = id;
    this.comun = peso;
  }

  public String getId() {
    // obtener el id
    return id;
  }

  public int getComun() {
    // Obtener el comun
    return comun;
  }

  public int setComun(int x) {
    // Cambiar el comun
    this.comun = x;
    return comun;
  }

  public abstract String toString();
}