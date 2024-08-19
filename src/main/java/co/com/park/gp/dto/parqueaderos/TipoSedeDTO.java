package co.com.park.gp.dto.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoSedeDTO {

    private UUID id;
    private String nombre;

    public TipoSedeDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public TipoSedeDTO(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoSedeDTO build() {
        return new TipoSedeDTO();
    }

    public TipoSedeDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TipoSedeDTO setNombre(final String nombre) {
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
