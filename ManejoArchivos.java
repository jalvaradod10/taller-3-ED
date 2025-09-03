public class Venta {
    private String id;
    private String idCliente;
    private String idProducto;
    private int cantidad;

    public Venta(String id, String idCliente, String idProducto, int cantidad) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return id + "," + idCliente + "," + idProducto + "," + cantidad;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }
}