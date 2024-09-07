package co.com.park.gp.dto.planes;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.clientes.ClienteDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.time.LocalDate;
import java.util.UUID;

public final class PlanDTO {

    private UUID id;
    private SedeDTO sede;
    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;
    private TipoPlanDTO tipoPlan;
    private EstadoDTO estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public PlanDTO() {
        setId(UUIDHelper.getDefault());
        setSede(SedeDTO.build());
        setVehiculo(VehiculoDTO.build());
        setCliente(ClienteDTO.build());
        setTipoPlan(TipoPlanDTO.build());
        setEstado(EstadoDTO.build());
        setFechaInicio(LocalDate.now());
        setFechaFin(LocalDate.of(0, 1, 1));
    }

    public PlanDTO(UUID id, SedeDTO sede, VehiculoDTO vehiculo, ClienteDTO cliente, TipoPlanDTO tipoPlan,EstadoDTO estado, LocalDate fechaInicio, LocalDate fechaFin) {
        setId(id);
        setSede(sede);
        setVehiculo(vehiculo);
        setCliente(cliente);
        setTipoPlan(tipoPlan);
        setEstado(estado);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    public static PlanDTO build() {
        return new PlanDTO();
    }

    public PlanDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public PlanDTO setSede(SedeDTO sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDTO.build());
        return this;
    }

    public PlanDTO setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = ObjectHelper.getObjectHelper().getDefaultValue(vehiculo, VehiculoDTO.build());
        return this;
    }

    public PlanDTO setCliente(ClienteDTO cliente) {
        this.cliente = ObjectHelper.getObjectHelper().getDefaultValue(cliente, ClienteDTO.build());
        return this;
    }

    public PlanDTO setTipoPlan(TipoPlanDTO tipoPlan) {
        this.tipoPlan = ObjectHelper.getObjectHelper().getDefaultValue(tipoPlan, TipoPlanDTO.build());
        return this;
    }

    public PlanDTO setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public PlanDTO setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public PlanDTO setEstado(EstadoDTO estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDTO.build());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public SedeDTO getSede() {
        return sede;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public TipoPlanDTO getTipoPlan() {
        return tipoPlan;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
