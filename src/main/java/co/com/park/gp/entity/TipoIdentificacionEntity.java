package co.com.park.gp.entity;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoIdentificacionEntity {

    private UUID id;
    private String nombre;

    public TipoIdentificacionEntity() {
        super();
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public TipoIdentificacionEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoIdentificacionEntity build() {
        return new TipoIdentificacionEntity();
    }

    public final TipoIdentificacionEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final TipoIdentificacionEntity setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final UUID getId() {
        return id;
    }

    public final String getNombre() {
        return nombre;
    }

}
