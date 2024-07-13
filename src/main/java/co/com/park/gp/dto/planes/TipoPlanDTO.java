package co.com.park.gp.dto.planes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class TipoPlanDTO {

    private UUID id;
    private String nombre;

    public TipoPlanDTO() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public TipoPlanDTO(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoPlanDTO build() {
        return new TipoPlanDTO();
    }

    public TipoPlanDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TipoPlanDTO setNombre(String nombre) {
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
