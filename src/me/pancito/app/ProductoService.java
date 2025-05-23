package me.pancito.app;

import me.pancito.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();
    private Scanner scanner;

    public ProductoService(Scanner scanner) {
        this.scanner = scanner;
        // Usamos el mismo scanner que en Main para no crear objetos al pedo
    }

    public void agregarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        productos.add(new Producto(nombre, precio, stock));
        System.out.println("Producto agregado con éxito.");
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void actualizarProducto() {
        System.out.print("ID del producto a actualizar: ");
        int id = scanner.nextInt();
        Producto producto = buscarPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.print("Nuevo precio (actual: " + producto.getPrecio() + "): ");
        producto.setPrecio(scanner.nextDouble(););
        System.out.print("Nuevo stock (actual: " + producto.getStock() + "): ");
        producto.setStock(scanner.nextInt(););
        System.out.println("Producto actualizado.");
    }

    public void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        Producto producto = buscarPorId(id);
        if (producto != null) {
            productos.remove(producto);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
