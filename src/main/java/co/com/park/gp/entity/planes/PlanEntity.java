package co.com.park.gp.entity.planes;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.time.LocalDate;
import java.util.UUID;

public final class PlanEntity {

    private UUID id;
    private SedeEntity sede;
    private VehiculoEntity vehiculo;
    private ClienteEntity cliente;
    private TipoPlanEntity tipoPlan;
    private EstadoEntity estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public PlanEntity() {
        setId(UUIDHelper.getDefault());
        setSede(SedeEntity.build());
        setVehiculo(VehiculoEntity.build());
        setCliente(ClienteEntity.build());
        setTipoPlan(TipoPlanEntity.build());
        setEstado(EstadoEntity.build());
        setFechaInicio(LocalDate.now());
        setFechaFin(LocalDate.now());
    }

    public PlanEntity(UUID id, SedeEntity sede, VehiculoEntity vehiculo, ClienteEntity cliente, TipoPlanEntity tipoPlan,EstadoEntity estado, LocalDate fechaInicio, LocalDate fechaFin) {
        setId(id);
        setSede(sede);
        setVehiculo(vehiculo);
        setCliente(cliente);
        setTipoPlan(tipoPlan);
        setEstado(estado);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    public static PlanEntity build() {
        return new PlanEntity();
    }

    public PlanEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public PlanEntity setSede(SedeEntity sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeEntity.build());
        return this;
    }

    public PlanEntity setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = ObjectHelper.getObjectHelper().getDefaultValue(vehiculo, VehiculoEntity.build());
        return this;
    }

    public PlanEntity setCliente(ClienteEntity cliente) {
        this.cliente = ObjectHelper.getObjectHelper().getDefaultValue(cliente, ClienteEntity.build());
        return this;
    }

    public PlanEntity setTipoPlan(TipoPlanEntity tipoPlan) {
        this.tipoPlan = ObjectHelper.getObjectHelper().getDefaultValue(tipoPlan, TipoPlanEntity.build());
        return this;
    }

    public PlanEntity setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public PlanEntity setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public PlanEntity setEstado(EstadoEntity estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoEntity.build());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public VehiculoEntity getVehiculo() {
        return vehiculo;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public TipoPlanEntity getTipoPlan() {
        return tipoPlan;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
