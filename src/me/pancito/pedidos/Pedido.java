package me.pancito.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;
    private int id;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.id = contador++; // Asignamos el actual valor del contador
                              // como ID del nuevo producto e incrementamos
                              // el contador para darle el siguiente ID al
                              // próximo producto creado.
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido #" + id + "\n");
        for (LineaPedido linea : lineas) {
            sb.append(" - ").append(linea).append("\n");
        }
        sb.append("Total: $").append(calcularTotal());
        return sb.toString();
    }
}
