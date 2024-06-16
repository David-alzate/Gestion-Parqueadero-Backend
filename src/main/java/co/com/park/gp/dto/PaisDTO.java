package co.com.park.gp.dto;

import java.util.UUID;


import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class PaisDTO {

    private UUID id;
    private String nombre;

    public PaisDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public PaisDTO(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static PaisDTO build() {
        return new PaisDTO();

    }

    public final PaisDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final PaisDTO setNombre(final String nombre) {
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
