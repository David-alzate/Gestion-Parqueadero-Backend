package co.com.park.gp.dto.parqueaderos;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;

import java.util.UUID;

public final class CeldaDTO {

    private UUID id;
    private SedeDTO sede;
    private TipoVehiculoDTO tipoVehiculo;
    private int CantidadCeldas;

    public CeldaDTO() {
        setId(UUIDHelper.getDefault());
        setSede(SedeDTO.build());
        setTipoVehiculo(TipoVehiculoDTO.build());
        setCantidadCeldas(0);
    }

    public CeldaDTO(UUID id, SedeDTO sede, TipoVehiculoDTO tipoVehiculo, int cantidadCeldas) {
        setId(id);
        setSede(sede);
        setTipoVehiculo(tipoVehiculo);
        setCantidadCeldas(cantidadCeldas);
    }

    public static CeldaDTO build(){
        return new CeldaDTO();
    }

    public UUID getId() {
        return id;
    }

    public CeldaDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SedeDTO getSede() {
        return sede;
    }

    public CeldaDTO setSede(SedeDTO sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDTO.build());
        return this;
    }

    public TipoVehiculoDTO getTipoVehiculo() {
        return tipoVehiculo;
    }

    public CeldaDTO setTipoVehiculo(TipoVehiculoDTO tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDTO.build());
        return this;
    }

    public int getCantidadCeldas() {
        return CantidadCeldas;
    }

    public CeldaDTO setCantidadCeldas(int cantidadCeldas) {
        CantidadCeldas = cantidadCeldas;
        return this;
    }
}
