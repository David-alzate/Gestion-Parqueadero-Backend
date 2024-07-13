package co.com.park.gp.business.domain.planes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TIpoPlanDomain {

    private UUID id;
    private String nombre;

    public TIpoPlanDomain(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static TIpoPlanDomain build(UUID id, String nombre) {
        return new TIpoPlanDomain(id, nombre);
    }

    public static TIpoPlanDomain build(UUID id) {
        return new TIpoPlanDomain(id, TextHelper.EMPTY);
    }

    public static TIpoPlanDomain build() {
        return new TIpoPlanDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
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
