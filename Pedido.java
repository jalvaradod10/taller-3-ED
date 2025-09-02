public class Pedido {
    private int idPedido;
    private int idCliente;
    private String producto;
    private double precio;
    private int cantidad;
    private boolean activo;

    public Pedido(int idPedido, int idCliente, String producto, double precio, int cantidad, boolean activo) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.activo = activo;
    }

    public int getIdPedido() { return idPedido; }
    public int getIdCliente() { return idCliente; }
    public String getProducto() { return producto; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return idPedido + "," + idCliente + "," + producto + "," + precio + "," + cantidad + "," + (activo ? 1 : 0);
    }
}
