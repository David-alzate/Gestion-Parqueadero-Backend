package co.com.park.gp.entity.planes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class TipoPlanEntity {

    private UUID id;
    private String nombre;

    public TipoPlanEntity() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
    }

    public TipoPlanEntity(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoPlanEntity build() {
        return new TipoPlanEntity();
    }

    public TipoPlanEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TipoPlanEntity setNombre(String nombre) {
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
