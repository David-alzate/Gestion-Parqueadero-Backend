package co.com.park.gp.business.domain;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class ParqueaderoDomain {

    private UUID id;
    private String nombre;

    private ParqueaderoDomain(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static ParqueaderoDomain build(final UUID id, final String nombre) {
        return new ParqueaderoDomain(id, nombre);
    }

    public static ParqueaderoDomain build(final UUID id) {
        return new ParqueaderoDomain(id, TextHelper.EMPTY);
    }

    public static ParqueaderoDomain build() {
        return new ParqueaderoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    private final void setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final UUID getId() {
        return id;
    }

    public final String getNombre() {
        return nombre;
    }

}