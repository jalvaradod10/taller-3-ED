import java.util.*;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> listarClientes();
                case 3 -> eliminarCliente();
                case 4 -> registrarPedido();
                case 5 -> listarPedidosCliente();
                case 6 -> guardarVenta();
                case 7 -> listarVentasCliente();
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 8);
        guardarDatos();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Registrar pedido");
        System.out.println("5. Listar pedidos de un cliente");
        System.out.println("6. Guardar una venta");
        System.out.println("7. Listar ventas realizadas por cliente");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // --- Métodos para cargar y guardar ---
    private static void cargarDatos() {
        List<String> lineasClientes = ManejoArchivos.leerArchivo("clientes.csv");
        clientes.clear();
        for (int i = 1; i < lineasClientes.size(); i++) {
            String[] p = lineasClientes.get(i).split(",");
            clientes.add(new Cliente(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4].equals("1")));
        }

        List<String> lineasPedidos = ManejoArchivos.leerArchivo("pedidos.csv");
        pedidos.clear();
        for (int i = 1; i < lineasPedidos.size(); i++) {
            String[] p = lineasPedidos.get(i).split(",");
            pedidos.add(new Pedido(Integer.parseInt(p[0]), Integer.parseInt(p[1]), p[2],
                    Double.parseDouble(p[3]), Integer.parseInt(p[4]), p[5].equals("1")));
        }
    }

    private static void guardarDatos() {
        List<String> lineasClientes = new ArrayList<>();
        lineasClientes.add("id_cliente,nombre,apellido,telefono,activo");
        for (Cliente c : clientes) {
            lineasClientes.add(c.toString());
        }
        ManejoArchivos.escribirArchivo("clientes.csv", lineasClientes);

        List<String> lineasPedidos = new ArrayList<>();
        lineasPedidos.add("id_pedido,id_cliente,producto,precio,cantidad,activo");
        for (Pedido p : pedidos) {
            lineasPedidos.add(p.toString());
        }
        ManejoArchivos.escribirArchivo("pedidos.csv", lineasPedidos);
    }

    // --- Opciones del menú ---
    private static void registrarCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        int id = clientes.size() + 1;
        clientes.add(new Cliente(id, nombre, apellido, telefono, true));
        System.out.println("Cliente registrado con ID " + id);
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : clientes) {
            if (c.isActivo()) {
                System.out.println(c.getIdCliente() + " - " + c.getNombre() + " " + c.getApellido() + " (" + c.getTelefono() + ")");
            }
        }
    }

    private static void eliminarCliente() {
        System.out.print("Ingrese ID del cliente a eliminar: ");
        int id = sc.nextInt();
        for (Cliente c : clientes) {
            if (c.getIdCliente() == id) {
                c.setActivo(false);
                System.out.println("Cliente eliminado lógicamente.");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    private static void registrarPedido() {
        System.out.print("Ingrese ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        System.out.print("Producto: ");
        String producto = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        int idPedido = pedidos.size() + 1;
        pedidos.add(new Pedido(idPedido, idCliente, producto, precio, cantidad, true));
        System.out.println("Pedido registrado con ID " + idPedido);
    }

    private static void listarPedidosCliente() {
        System.out.print("Ingrese ID del cliente: ");
        int id = sc.nextInt();
        System.out.println("\n--- PEDIDOS DEL CLIENTE ---");
        for (Pedido p : pedidos) {
            if (p.getIdCliente() == id && p.isActivo()) {
                System.out.println("Pedido " + p.getIdPedido() + ": " + p.getProducto() + " x" + p.getCantidad() + " $" + p.getPrecio());
            }
        }
    }

    private static void guardarVenta() {
        System.out.print("Ingrese ID del cliente: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Producto: ");
        String producto = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        System.out.print("Precio unitario: ");
        double precio = sc.nextDouble();
        int idPedido = pedidos.size() + 1;
        pedidos.add(new Pedido(idPedido, id, producto, precio, cantidad, true));
        System.out.println("Venta guardada con ID " + idPedido);
    }

    private static void listarVentasCliente() {
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = sc.nextLine();
        double total = 0;
        System.out.println("\n--- VENTAS DEL CLIENTE ---");
        for (Cliente c : clientes) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.isActivo()) {
                for (Pedido p : pedidos) {
                    if (p.getIdCliente() == c.getIdCliente() && p.isActivo()) {
                        double parcial = p.getPrecio() * p.getCantidad();
                        total += parcial;
                        System.out.println(p.getProducto() + " x" + p.getCantidad() + " = $" + parcial);
                    }
                }
            }
        }
        System.out.println("TOTAL: $" + total);
    }
}
