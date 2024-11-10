package co.com.park.gp.business.domain.parqueaderos;

import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class CeldaDomain {

    private UUID id;
    private SedeDomain sede;
    private TipoVehiculoDomain tipoVehiculo;
    private int cantidadCeldas;

    public CeldaDomain(UUID id, SedeDomain sede, TipoVehiculoDomain tipoVehiculo, int cantidadCeldas) {
        setId(id);
        setSede(sede);
        setTipoVehiculo(tipoVehiculo);
        setCantidadCeldas(cantidadCeldas);
    }

    public static CeldaDomain build(UUID id, SedeDomain sede, TipoVehiculoDomain tipoVehiculo, int cantidadCeldas){
        return new CeldaDomain(id, sede, tipoVehiculo, cantidadCeldas);
    }

    public static CeldaDomain build(UUID id){
        return new CeldaDomain(id, SedeDomain.build(), TipoVehiculoDomain.build(), 0);
    }

    public static CeldaDomain build(){
        return new CeldaDomain(UUIDHelper.getDefault(), SedeDomain.build(), TipoVehiculoDomain.build(), 0);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public SedeDomain getSede() {
        return sede;
    }

    public void setSede(SedeDomain sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDomain.build());
    }

    public TipoVehiculoDomain getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculoDomain tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDomain.build());
    }

    public int getCantidadCeldas() {
        return cantidadCeldas;
    }

    public void setCantidadCeldas(int cantidadCeldas) {
    	cantidadCeldas = cantidadCeldas;
    }
}
