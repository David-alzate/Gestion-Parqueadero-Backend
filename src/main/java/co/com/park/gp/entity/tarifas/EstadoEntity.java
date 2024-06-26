package co.com.park.gp.entity.tarifas;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class EstadoEntity {

    private UUID id;
    private String estado;

    public EstadoEntity() {
        setId(UUIDHelper.getDefault());
        setEstado(TextHelper.EMPTY);
    }

    public EstadoEntity(final String estado, final UUID id) {
        setId(id);
        setEstado(estado);
    }

    public static EstadoEntity build() {
        return new EstadoEntity();
    }

    public EstadoEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public EstadoEntity setEstado(String estado) {
        this.estado = TextHelper.applyTrim(estado);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }
}
