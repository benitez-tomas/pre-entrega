package me.pancito.app;

import me.pancito.excepciones.StockInsuficienteException;
import me.pancito.exceptiones.ErrorDeValidacionException;
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
        // Usamos el mismo scanner que en Main para no crear objetos al pedo
    }

    public void crearPedido() {
        Pedido pedido = new Pedido();
        while (true) {
            productoService.listarProductos();
            System.out.print("ID del producto a agregar (o 0 para finalizar): ");
            int id = scanner.nextInt();
            if (id == 0) break;
            Producto producto = productoService.buscarPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            try { // Chequeamos que la cantidad sea válida.
                if (cantidad > producto.getStock()) {
                    throw new StockInsuficienteException("No hay suficiente stock.");
                } else if (cantidad < 1) {
                    throw new ErrorDeValidacionException("Por favor indique una cantidad mayor o igual a 1 (uno).");
                }
                producto.setStock(producto.getStock() - cantidad);
                pedido.agregarLinea(new LineaPedido(producto, cantidad));
            } catch (StockInsuficienteException | ErrorDeValidacionException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Viendo que el pedido no tenga un total igual a 0,
        // nos aseguramos de que no haya un pedido vacío
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
