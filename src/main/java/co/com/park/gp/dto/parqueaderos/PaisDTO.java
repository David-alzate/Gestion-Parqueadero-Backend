package co.com.park.gp.dto.parqueaderos;

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

    public PaisDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public PaisDTO setNombre(final String nombre) {
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
