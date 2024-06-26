package co.com.park.gp.dto.tarifas;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoTarifaDTO {

    private UUID id;
    private String tipoTarifa;

    public TipoTarifaDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setTipoTarifa(TextHelper.EMPTY);
    }

    public TipoTarifaDTO(final UUID id, final String tipoTarifa) {
        setId(id);
        setTipoTarifa(tipoTarifa);
    }

    public static TipoTarifaDTO build() {
        return new TipoTarifaDTO();
    }

    public TipoTarifaDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TipoTarifaDTO setTipoTarifa(final String tipoTarifa) {
        this.tipoTarifa = TextHelper.applyTrim(tipoTarifa);
        return this;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public UUID getId() {
        return id;
    }
}
