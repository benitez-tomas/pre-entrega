package me.pancito.app;

import me.pancito.excepciones.StockInsuficienteException;
import me.pancito.pedidos.LineaPedido;
import me.pancito.pedidos.Pedido;
import me.pancito.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class PedidoService {
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ProductoService productoService;
    private Scanner scanner;

    public PedidoService(ProductoService productoService, Scanner scanner) {
        this.productoService = productoService;
        this.scanner = scanner;
    }

    public void crearPedido() {
        Pedido pedido = new Pedido();
        while (true) {
            productoService.listarProductos();
            System.out.print("ID del producto a agregar (o 0 para finalizar): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (id == 0) break;
            Producto producto = productoService.buscarPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            try {
                if (cantidad > producto.getStock()) {
                    throw new StockInsuficienteException("No hay suficiente stock.");
                }
                producto.setStock(producto.getStock() - cantidad);
                pedido.agregarLinea(new LineaPedido(producto, cantidad));
            } catch (StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        if (!pedido.toString().contains("Total: $0.0")) {
            pedidos.add(pedido);
            System.out.println("Pedido creado:\n" + pedido);
        } else {
            System.out.println("No se creó ningún pedido.");
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println(p);
            System.out.println("--------------------");
        }
    }
}
