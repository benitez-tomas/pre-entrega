package me.pancito.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Estos objetos nos permiten manipular productos y pedidos.
        ProductoService productoService = new ProductoService(scanner);
        PedidoService pedidoService = new PedidoService(productoService, scanner);
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\n==== MENÚ ====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar/Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Crear pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": productoService.agregarProducto(); break;
                case "2": productoService.listarProductos(); break;
                case "3": productoService.actualizarProducto(); break;
                case "4": productoService.eliminarProducto(); break;
                case "5": pedidoService.crearPedido(); break;
                case "6": pedidoService.listarPedidos(); break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida."); break;
            }
        }
        
        System.out.println("Gracias por usar el sistema.");
        scanner.close(); // Esto lo aprendí en un curso introductorio a java y
                         // me quedó de costumbre, aunque sé que no siempre
                         // es necesario.
    }
}
