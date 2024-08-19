package co.com.park.gp.entity.parqueaderos;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;

import java.util.UUID;

public final class CeldaEntity {

    private UUID id;
    private SedeEntity sede;
    private TipoVehiculoEntity tipoVehiculo;
    private int CantidadCeldas;

    public CeldaEntity() {
        setId(UUIDHelper.getDefault());
        setSede(SedeEntity.build());
        setTipoVehiculo(TipoVehiculoEntity.build());
        setCantidadCeldas(0);
    }

    public CeldaEntity(UUID id, SedeEntity sede, TipoVehiculoEntity tipoVehiculo, int cantidadCeldas) {
        setId(id);
        setSede(sede);
        setTipoVehiculo(tipoVehiculo);
        setCantidadCeldas(cantidadCeldas);
    }

    public static CeldaEntity build(){
        return new CeldaEntity();
    }

    public UUID getId() {
        return id;
    }

    public CeldaEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public CeldaEntity setSede(SedeEntity sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeEntity.build());
        return this;
    }

    public TipoVehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    public CeldaEntity setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoEntity.build());
        return this;
    }

    public int getCantidadCeldas() {
        return CantidadCeldas;
    }

    public CeldaEntity setCantidadCeldas(int cantidadCeldas) {
        CantidadCeldas = cantidadCeldas;
        return this;
    }
}
