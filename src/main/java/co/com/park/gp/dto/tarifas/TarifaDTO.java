package co.com.park.gp.dto.tarifas;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

import java.util.UUID;
import java.time.LocalDate;

public class TarifaDTO {
    private UUID id;
    private SedeDTO sede;
    private TipoVehiculoDTO tipoVehiculo;
    private TipoTarifaDTO tipoTarifa;
    private Long tarifa;
    private EstadoDTO estado;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;

    public TarifaDTO() {
        setId(UUIDHelper.getDefault());
        setSede(SedeDTO.build());
        setTipoVehiculo(TipoVehiculoDTO.build());
        setTipoTarifa(TipoTarifaDTO.build());
        setTarifa(0L);
        setEstado(EstadoDTO.build());
        setFechaInicioVigencia(LocalDate.now());
        setFechaFinVigencia(LocalDate.now().plusYears(1));
    }

    public TarifaDTO(UUID id, SedeDTO sede, TipoVehiculoDTO tipoVehiculo, TipoTarifaDTO tipoTarifa, Long tarifa,
                     EstadoDTO estado, LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia) {
        setId(id);
        setSede(sede);
        setTipoVehiculo(tipoVehiculo);
        setTipoTarifa(tipoTarifa);
        setTarifa(tarifa);
        setEstado(estado);
        setFechaInicioVigencia(fechaInicioVigencia);
        setFechaFinVigencia(fechaFinVigencia);
    }

    public TarifaDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public TarifaDTO setSede(SedeDTO sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDTO.build());
        return this;
    }

    public TarifaDTO setTipoVehiculo(TipoVehiculoDTO tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDTO.build());
        return this;
    }

    public TarifaDTO setTipoTarifa(TipoTarifaDTO tipoTarifa) {
        this.tipoTarifa = ObjectHelper.getObjectHelper().getDefaultValue(tipoTarifa, TipoTarifaDTO.build());
        return this;
    }

    public TarifaDTO setTarifa(Long tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    public TarifaDTO setEstado(EstadoDTO estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDTO.build());
        return this;
    }

    public TarifaDTO setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
        return this;
    }

    public TarifaDTO setFechaFinVigencia(LocalDate fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public SedeDTO getSede() {
        return sede;
    }

    public TipoVehiculoDTO getTipoVehiculo() {
        return tipoVehiculo;
    }

    public TipoTarifaDTO getTipoTarifa() {
        return tipoTarifa;
    }

    public Long getTarifa() {
        return tarifa;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public LocalDate getFechaFinVigencia() {
        return fechaFinVigencia;
    }
}
