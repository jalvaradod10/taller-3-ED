public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private boolean activo;

    public Cliente(int idCliente, String nombre, String apellido, String telefono, boolean activo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.activo = activo;
    }

    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return idCliente + "," + nombre + "," + apellido + "," + telefono + "," + (activo ? 1 : 0);
    }
}
