package co.com.park.gp.dto.comunes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoVehiculoDTO {

    private UUID id;
    private String tipoVehiculo;

    public TipoVehiculoDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setTipoVehiculo(TextHelper.EMPTY);
    }

    public TipoVehiculoDTO(final String tipoVehiculo, final UUID id) {
        setId(id);
        setTipoVehiculo(tipoVehiculo);
    }

    public static TipoVehiculoDTO build() {
        return new TipoVehiculoDTO();
    }

    public TipoVehiculoDTO setTipoVehiculo(final String tipoVehiculo) {
        this.tipoVehiculo = TextHelper.applyTrim(tipoVehiculo);
        return this;
    }

    public TipoVehiculoDTO setId(final UUID id) {
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
