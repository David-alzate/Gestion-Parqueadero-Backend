package co.com.park.gp.dto.tarifas;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class EstadoDTO {

    private UUID id;
    private String estado;

    public EstadoDTO() {
        setId(UUIDHelper.getDefault());
        setEstado(TextHelper.EMPTY);
    }

    public EstadoDTO(final String estado, final UUID id) {
        setId(id);
        setEstado(estado);
    }

    public static EstadoDTO build() {
        return new EstadoDTO();
    }

    public EstadoDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public EstadoDTO setEstado(String estado) {
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
