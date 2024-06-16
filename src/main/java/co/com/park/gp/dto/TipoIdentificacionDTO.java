package co.com.park.gp.dto;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoIdentificacionDTO {

    private UUID id;
    private String nombre;

    public TipoIdentificacionDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public TipoIdentificacionDTO(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoIdentificacionDTO build() {
        return new TipoIdentificacionDTO();
    }

    public final TipoIdentificacionDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final TipoIdentificacionDTO setNombre(final String nombre) {
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
