package co.com.park.gp.business.domain.comunes;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class TipoVehiculoDomain {

    private UUID id;
    private String tipoVehiculo;

    public TipoVehiculoDomain(UUID id, String tipoVehiculo) {
        setId(id);
        setTipoVehiculo(tipoVehiculo);
    }

    public static TipoVehiculoDomain build(UUID id, String tipoVehiculo){
        return new TipoVehiculoDomain(id, tipoVehiculo);
    }

    public static TipoVehiculoDomain build(UUID id){
        return new TipoVehiculoDomain(id, TextHelper.EMPTY);
    }

    public static TipoVehiculoDomain build(){
        return new TipoVehiculoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public UUID getId() {
        return id;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
}
