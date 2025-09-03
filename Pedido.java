public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int activo; // 1 = activo, 0 = inactivo

    public Producto(String id, String nombre, double precio, int activo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.activo = activo;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + precio + "," + activo;
    }

    public String getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }
}