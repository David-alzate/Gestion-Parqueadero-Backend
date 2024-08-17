package co.com.park.gp.business.domain.facturacion;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class MetodoPagoDomain {

    private UUID id;
    private String nombre;

    public MetodoPagoDomain(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static MetodoPagoDomain build(UUID id, String nombre) {
        return new MetodoPagoDomain(id, nombre);
    }

    public static MetodoPagoDomain build(UUID id) {
        return new MetodoPagoDomain(id, TextHelper.EMPTY);
    }

    public static MetodoPagoDomain build() {
        return new MetodoPagoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}
