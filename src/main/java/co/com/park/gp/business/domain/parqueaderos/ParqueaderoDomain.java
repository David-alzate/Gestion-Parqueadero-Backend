package co.com.park.gp.business.domain.parqueaderos;

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

    private void setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}