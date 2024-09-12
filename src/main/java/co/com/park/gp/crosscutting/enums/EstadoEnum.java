package co.com.park.gp.crosscutting.enums;

public enum EstadoEnum {
    ACTIVO("Activa"),
    INACTIVO("Inactiva");

    private final String nombre;

    EstadoEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
