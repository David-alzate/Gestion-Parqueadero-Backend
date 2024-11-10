package co.com.park.gp.business.domain.vehiculos;

import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class VehiculoDomain {

    private UUID id;
    private TipoVehiculoDomain tipoVehiculo;
    private String placa;

    public VehiculoDomain(final UUID id, final TipoVehiculoDomain tipoVehiculo, final String placa) {
        setId(id);
        setTipoVehiculo(tipoVehiculo);
        setPlaca(placa);
    }

    public static VehiculoDomain build(final UUID id, final TipoVehiculoDomain tipoVehiculo, final String placa){
        return new VehiculoDomain(id, tipoVehiculo, placa);
    }

    public static VehiculoDomain build(final UUID id){
        return new VehiculoDomain(id, TipoVehiculoDomain.build(), TextHelper.EMPTY);
    }

    public static VehiculoDomain build(){
        return new VehiculoDomain(UUIDHelper.getDefault(), TipoVehiculoDomain.build(), TextHelper.EMPTY);
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());;
    }

    public void setTipoVehiculo(TipoVehiculoDomain tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDomain.build());
    }

    public void setPlaca(String placa) {
        this.placa = TextHelper.applyTrim(placa);
    }

    public UUID getId() {
        return id;
    }

    public TipoVehiculoDomain getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }
}
