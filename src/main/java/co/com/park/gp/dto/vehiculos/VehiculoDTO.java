package co.com.park.gp.dto.vehiculos;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;

import java.util.UUID;

public final class VehiculoDTO {

    private UUID id;
    private TipoVehiculoDTO tipoVehiculo;
    private String placa;

    public VehiculoDTO() {
        setId(UUIDHelper.getDefault());
        setTipoVehiculo(TipoVehiculoDTO.build());
        setPlaca(TextHelper.EMPTY);
    }

    public VehiculoDTO(final UUID id, final TipoVehiculoDTO tipoVehiculo, final String placa) {
        setId(id);
        setTipoVehiculo(tipoVehiculo);
        setPlaca(placa);
    }

    public static VehiculoDTO build() {
        return new VehiculoDTO();
    }

    public VehiculoDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public VehiculoDTO setTipoVehiculo(final TipoVehiculoDTO tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDTO.build());
        return this;
    }

    public VehiculoDTO setPlaca(final String placa) {
        this.placa = TextHelper.applyTrim(placa);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public TipoVehiculoDTO getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }
}
