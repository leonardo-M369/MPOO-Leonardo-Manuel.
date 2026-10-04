package practica4;

public class PilaDinamica {
    private double[] elementos;
    private int cima;

    public PilaDinamica(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            capacidadInicial = 1;
        }
        this.elementos = new double[capacidadInicial];
        this.cima = 0;
    }

    public void push(double valor) {
        if (this.cima == this.elementos.length) {
            ampliarCapacidad();
        }
        this.elementos[this.cima] = valor;
        this.cima++;
    }

    public double pop() {
        if (isEmpty()) {
            return Double.NaN;
        }
        this.cima--;
        return this.elementos[this.cima];
    }

    public double peek() {
        if (isEmpty()) {
            return Double.NaN;
        }
        return this.elementos[this.cima - 1];
    }

    public boolean isEmpty() {
        return this.cima == 0;
    }

    public int size() {
        return this.cima;
    }

    public int capacity() {
        return this.elementos.length;
    }

    private void ampliarCapacidad() {
        int nuevaCapacidad = this.elementos.length * 2;
        double[] nuevoArreglo = new double[nuevaCapacidad];
        for (int i = 0; i < this.elementos.length; i++) {
            nuevoArreglo[i] = this.elementos[i];
        }
        this.elementos = nuevoArreglo;
    }
}