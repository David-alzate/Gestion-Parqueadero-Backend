package co.com.park.gp.entity.tarifas;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoTarifaEntity {

    private UUID id;
    private String tipoTarifa;

    public TipoTarifaEntity() {
        super();
        setId(UUIDHelper.getDefault());
        setTipoTarifa(TextHelper.EMPTY);
    }

    public TipoTarifaEntity(final UUID id, final String tipoTarifa) {
        setId(id);
        setTipoTarifa(tipoTarifa);
    }

    public static TipoTarifaEntity build() {
        return new TipoTarifaEntity();
    }

    public TipoTarifaEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TipoTarifaEntity setTipoTarifa(final String tipoTarifa) {
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
