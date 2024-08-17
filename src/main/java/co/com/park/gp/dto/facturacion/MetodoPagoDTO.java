package co.com.park.gp.dto.facturacion;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class MetodoPagoDTO {

    private UUID id;
    private String nombre;

    public MetodoPagoDTO() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public MetodoPagoDTO(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static MetodoPagoDTO build(){
        return new MetodoPagoDTO();
    }

    public UUID getId() {
        return id;
    }

    public MetodoPagoDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public MetodoPagoDTO setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }
}
