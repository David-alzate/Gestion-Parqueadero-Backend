package co.com.park.gp.business.domain;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public class TipoEmpleadoDomain {

    private UUID id;
    private String nombre;

    public TipoEmpleadoDomain(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final TipoEmpleadoDomain build(final UUID id, final String nombre) {
        return new TipoEmpleadoDomain(id, nombre);
    }

    public static final TipoEmpleadoDomain build(final UUID id) {
        return new TipoEmpleadoDomain(id, TextHelper.EMPTY);
    }

    public static final TipoEmpleadoDomain build() {
        return new TipoEmpleadoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    public final void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final UUID getId() {
        return id;
    }

    public final String getNombre() {
        return nombre;
    }

}
