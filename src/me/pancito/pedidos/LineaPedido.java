package me.pancito.pedidos;

import me.pancito.productos.Producto;

// Esta clase se usa para crear cada una de las líneas de
// un pedido, es decir, el qué y el cuanto.
public class LineaPedido {
    private Producto producto;
    private int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x " + cantidad + " = $" + getSubtotal();
    }
}
