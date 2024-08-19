package co.com.park.gp.entity.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class PaisEntity {

    private UUID id;
    private String nombre;

    public PaisEntity() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public PaisEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static PaisEntity build() {
        return new PaisEntity();

    }

    public PaisEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public PaisEntity setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
