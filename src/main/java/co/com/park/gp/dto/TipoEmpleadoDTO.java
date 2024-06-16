package co.com.park.gp.dto;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoEmpleadoDTO {

    private UUID id;
    private String nombre;

    public TipoEmpleadoDTO() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public TipoEmpleadoDTO(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoEmpleadoDTO build() {
        return new TipoEmpleadoDTO();
    }

    public final TipoEmpleadoDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final TipoEmpleadoDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final UUID getId() {
        return id;
    }

}
