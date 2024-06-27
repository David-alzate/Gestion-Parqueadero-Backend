package co.com.park.gp.business.domain.tarifas;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class EstadoDomain {

    private UUID id;
    private String estado;

    public EstadoDomain(final UUID id, final String estado) {
        setId(id);
        setEstado(estado);
    }

    public static EstadoDomain build(final UUID id, final String estado){
        return new EstadoDomain(id, estado);
    }

    public static EstadoDomain build(final UUID id){
        return new EstadoDomain(id, TextHelper.EMPTY);
    }

    public static EstadoDomain build(){
        return new EstadoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    private void setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private void setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
    }

    public String getEstado() {
        return estado;
    }

    public UUID getId() {
        return id;
    }
}
