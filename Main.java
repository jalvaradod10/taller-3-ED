import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Crear Producto");
            System.out.println("3. Crear Venta");
            System.out.println("4. Listar Ventas de Cliente");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> crearProducto();
                case 3 -> crearVenta();
                case 4 -> listarVentasCliente();
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    // -------------------- CLIENTES --------------------
    public static void crearCliente() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.csv", true))) {
            System.out.print("ID Cliente: ");
            String id = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Telefono: ");
            String telefono = sc.nextLine();
            bw.write(id + "," + nombre + "," + apellido + "," + telefono + ",1");
            bw.newLine();
            System.out.println("Cliente guardado.");
        } catch (IOException e) {
            System.out.println("Error al guardar cliente");
        }
    }

    // -------------------- PRODUCTOS --------------------
    public static void crearProducto() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.csv", true))) {
            System.out.print("ID Producto: ");
            String id = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Precio: ");
            double precio = sc.nextDouble();
            sc.nextLine();
            bw.write(id + "," + nombre + "," + precio + ",1");
            bw.newLine();
            System.out.println("Producto guardado.");
        } catch (IOException e) {
            System.out.println("Error al guardar producto");
        }
    }

    // -------------------- VENTAS --------------------
    public static void crearVenta() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ventas.csv", true))) {
            System.out.print("ID Venta: ");
            String id = sc.nextLine();
            System.out.print("ID Cliente: ");
            String idCliente = sc.nextLine();
            System.out.print("ID Producto: ");
            String idProducto = sc.nextLine();
            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();
            bw.write(id + "," + idCliente + "," + idProducto + "," + cantidad);
            bw.newLine();
            System.out.println("Venta guardada.");
        } catch (IOException e) {
            System.out.println("Error al guardar venta");
        }
    }

    // -------------------- LISTAR VENTAS --------------------
    public static void listarVentasCliente() {
        System.out.print("ID Cliente a consultar: ");
        String idCliente = sc.nextLine();

        try (BufferedReader brVentas = new BufferedReader(new FileReader("ventas.csv"));
             BufferedReader brProductos = new BufferedReader(new FileReader("productos.csv"))) {

            // Guardamos productos en un mapa para buscar precios rapido
            Map<String, Double> precios = new HashMap<>();
            String linea;
            while ((linea = brProductos.readLine()) != null) {
                String[] datos = linea.split(",");
                precios.put(datos[0], Double.parseDouble(datos[2]));
            }

            double totalGeneral = 0;
            System.out.println("\n--- Ventas del cliente " + idCliente + " ---");
            while ((linea = brVentas.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(idCliente)) {
                    String idProducto = datos[2];
                    int cantidad = Integer.parseInt(datos[3]);
                    double precio = precios.getOrDefault(idProducto, 0.0);
                    double total = precio * cantidad;
                    totalGeneral += total;
                    System.out.println("Producto: " + idProducto + " | Cant: " + cantidad + " | Total: $" + total);
                }
            }
            System.out.println("TOTAL GENERAL: $" + totalGeneral);

        } catch (IOException e) {
            System.out.println("Error al leer archivos");
        }
    }
}