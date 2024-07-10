package co.com.park.gp.entity.vehiculos;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;

import java.util.UUID;

public final class VehiculoEntity {

    private UUID id;
    private TipoVehiculoEntity tipoVehiculo;
    private String placa;

    public VehiculoEntity() {
        setId(UUIDHelper.getDefault());
        setTipoVehiculo(TipoVehiculoEntity.build());
        setPlaca(TextHelper.EMPTY);
    }

    public VehiculoEntity(final UUID id, final TipoVehiculoEntity tipoVehiculo, final String placa) {
        setId(id);
        setTipoVehiculo(tipoVehiculo);
        setPlaca(placa);
    }

    public static VehiculoEntity build() {
        return new VehiculoEntity();
    }

    public VehiculoEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public VehiculoEntity setTipoVehiculo(final TipoVehiculoEntity tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoEntity.build());
        return this;
    }

    public VehiculoEntity setPlaca(final String placa) {
        this.placa = TextHelper.applyTrim(placa);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public TipoVehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }
}
