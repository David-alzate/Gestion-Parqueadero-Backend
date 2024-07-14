package co.com.park.gp.business.domain.planes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoPlanDomain {

    private UUID id;
    private String nombre;

    public TipoPlanDomain(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TipoPlanDomain build(UUID id, String nombre) {
        return new TipoPlanDomain(id, nombre);
    }

    public static TipoPlanDomain build(UUID id) {
        return new TipoPlanDomain(id, TextHelper.EMPTY);
    }

    public static TipoPlanDomain build() {
        return new TipoPlanDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
