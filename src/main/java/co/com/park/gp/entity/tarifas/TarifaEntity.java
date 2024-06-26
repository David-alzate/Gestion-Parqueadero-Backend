package co.com.park.gp.entity.tarifas;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

import java.time.LocalDate;
import java.util.UUID;

public class TarifaEntity {
    private UUID id;
    private SedeEntity sede;
    private TipoVehiculoEntity tipoVehiculo;
    private TipoTarifaEntity tipoTarifa;
    private Long tarifa;
    private EstadoEntity estado;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;

    public TarifaEntity() {
        setId(UUIDHelper.getDefault());
        setSede(SedeEntity.build());
        setTipoVehiculo(TipoVehiculoEntity.build());
        setTipoTarifa(TipoTarifaEntity.build());
        setTarifa(0L);
        setEstado(EstadoEntity.build());
        setFechaInicioVigencia(LocalDate.now());
        setFechaFinVigencia(LocalDate.now().plusYears(1));
    }

    public TarifaEntity(UUID id, SedeEntity sede, TipoVehiculoEntity tipoVehiculo, TipoTarifaEntity tipoTarifa, Long tarifa,
                        EstadoEntity estado, LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia) {
        setId(id);
        setSede(sede);
        setTipoVehiculo(tipoVehiculo);
        setTipoTarifa(tipoTarifa);
        setTarifa(tarifa);
        setEstado(estado);
        setFechaInicioVigencia(fechaInicioVigencia);
        setFechaFinVigencia(fechaFinVigencia);
    }

    public TarifaEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TarifaEntity setSede(SedeEntity sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeEntity.build());
        return this;
    }

    public TarifaEntity setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoEntity.build());
        return this;
    }

    public TarifaEntity setTipoTarifa(TipoTarifaEntity tipoTarifa) {
        this.tipoTarifa = ObjectHelper.getObjectHelper().getDefaultValue(tipoTarifa, TipoTarifaEntity.build());
        return this;
    }

    public TarifaEntity setTarifa(Long tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    public TarifaEntity setEstado(EstadoEntity estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoEntity.build());
        return this;
    }

    public TarifaEntity setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
        return this;
    }

    public TarifaEntity setFechaFinVigencia(LocalDate fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public TipoVehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    public TipoTarifaEntity getTipoTarifa() {
        return tipoTarifa;
    }

    public Long getTarifa() {
        return tarifa;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public LocalDate getFechaFinVigencia() {
        return fechaFinVigencia;
    }
}
