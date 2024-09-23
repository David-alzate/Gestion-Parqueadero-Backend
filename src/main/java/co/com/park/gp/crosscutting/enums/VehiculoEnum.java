package co.com.park.gp.crosscutting.enums;

public enum VehiculoEnum {
	CARRO("Carro"),
    MOTO("Moto"),
	CAMION("Camion");

    private final String nombre;

    VehiculoEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
