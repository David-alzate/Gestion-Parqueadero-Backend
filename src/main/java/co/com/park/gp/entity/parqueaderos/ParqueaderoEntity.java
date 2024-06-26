package co.com.park.gp.entity.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class ParqueaderoEntity {

    private UUID id;
    private String nombre;

    public ParqueaderoEntity() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public ParqueaderoEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static ParqueaderoEntity build() {
        return new ParqueaderoEntity();
    }

    public final ParqueaderoEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final ParqueaderoEntity setNombre(final String nombre) {
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
