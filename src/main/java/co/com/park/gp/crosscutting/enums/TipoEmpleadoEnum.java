package co.com.park.gp.crosscutting.enums;

public enum TipoEmpleadoEnum {
    ADMINISTRADOR("Administrador"),
    EMPLEADO("Empleado");

    private final String rol;

    TipoEmpleadoEnum(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
