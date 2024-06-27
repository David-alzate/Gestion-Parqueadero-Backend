package co.com.park.gp.business.domain.tarifas;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoTarifaDomain {

    private UUID id;
    private String tipoTarifa;

    public TipoTarifaDomain(final UUID id, final String tipoTarifa) {
        setId(id);
        setTipoTarifa(tipoTarifa);
    }

    public static TipoTarifaDomain build(final UUID id, final String tipoTarifa){
        return new TipoTarifaDomain(id, tipoTarifa);
    }

    public static TipoTarifaDomain build(final UUID id){
        return new TipoTarifaDomain(id, TextHelper.EMPTY);
    }

    public static TipoTarifaDomain build(){
        return new TipoTarifaDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    private void setTipoTarifa(final String tipoTarifa) {
        this.tipoTarifa = TextHelper.applyTrim(tipoTarifa);
    }

    private void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public UUID getId() {
        return id;
    }
}
