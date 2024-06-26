package co.com.park.gp.entity.comunes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoVehiculoEntity {

    private UUID id;
    private String tipoVehiculo;

    public TipoVehiculoEntity() {
        super();
        setId(UUIDHelper.getDefault());
        setTipoVehiculo(TextHelper.EMPTY);
    }

    public TipoVehiculoEntity(final String tipoVehiculo, final UUID id) {
        setId(id);
        setTipoVehiculo(tipoVehiculo);
    }

    public static TipoVehiculoEntity build() {
        return new TipoVehiculoEntity();
    }

    public TipoVehiculoEntity setTipoVehiculo(final String tipoVehiculo) {
        this.tipoVehiculo = TextHelper.applyTrim(tipoVehiculo);
        return this;
    }

    public TipoVehiculoEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public UUID getId() {
        return id;
    }
}
