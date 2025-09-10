package a;
public class Cliente {
    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private int activo; // 1 = activo, 0 = inactivo

    public Cliente(String id, String nombre, String apellido, String telefono, int activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.activo = activo;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + apellido + "," + telefono + "," + activo;
    }

    public String getId() {
        return id;
    }
}