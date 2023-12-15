public class Nodo {
      // variables de la clase.
      private String Nombre;
      private Nodo sup;
      private Nodo inf;
      private Nodo izq;
      private Nodo der;
      private int altura;
      private boolean visitado;
      private boolean perimetro; 

      // Constructor de la clase.
      public Nodo(String id) {
            this.Nombre = id;
            this.sup = null;
            this.inf = null;
            this.izq = null;
            this.der = null;
            this.altura = 0;
            this.visitado = false;
            this.perimetro = false;
      }

      // Devuelve el nombre del vértice.
      public String getNombre() {
            return Nombre;
      }
      public void setSup(Nodo sup) {
            this.sup = sup;
      } 
      public void setInf(Nodo inf) {
            this.inf = inf;
      }
      public void setIzq(Nodo izq) {
            this.izq = izq;
      }
      public void setDer(Nodo der) {
            this.der = der;
      }
      public Nodo getSup() {
            return sup;
      }
      public Nodo getInf() {
            return inf;
      }
      public Nodo getIzq() {
            return izq;
      }
      public Nodo getDer() {
            return der;
      }
      public int getAltura() {
            return altura;
      }
      public void setAltura(int altura) {
            this.altura = altura;
      }
      public boolean getVisitado() {
            return visitado;
      }
      public void setVisitado(boolean visitado) {
            this.visitado = visitado;
      }
      public boolean getPerimetro() {
            return perimetro;
      }
      public void setPerimetro(boolean perimetro) {
            this.perimetro = perimetro;
      }
      
      // Devuelve una representación en forma de cadena del vértice.
      public String toString() {
            return " " + getNombre() + " " + getAltura() + " " + getVisitado();
      }
}