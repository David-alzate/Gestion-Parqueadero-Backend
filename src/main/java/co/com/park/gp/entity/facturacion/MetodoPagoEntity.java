package co.com.park.gp.entity.facturacion;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class MetodoPagoEntity {

    private UUID id;
    private String nombre;

    public MetodoPagoEntity() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public MetodoPagoEntity(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static MetodoPagoEntity build(){
        return new MetodoPagoEntity();
    }

    public UUID getId() {
        return id;
    }

    public MetodoPagoEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public MetodoPagoEntity setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }
}
