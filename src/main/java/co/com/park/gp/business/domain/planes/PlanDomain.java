package co.com.park.gp.business.domain.planes;

import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.time.LocalDate;
import java.util.UUID;

public final class PlanDomain {

    private UUID id;
    private SedeDomain sede;
    private VehiculoDomain vehiculo;
    private ClienteDomain cliente;
    private TipoPlanDomain tipoPlan;
    private EstadoDomain estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public PlanDomain(UUID id, SedeDomain sede, VehiculoDomain vehiculo, ClienteDomain cliente, TipoPlanDomain tipoPlan, EstadoDomain estado, LocalDate fechaInicio, LocalDate fechaFin) {
        setId(id);
        setSede(sede);
        setVehiculo(vehiculo);
        setCliente(cliente);
        setTipoPlan(tipoPlan);
        setEstado(estado);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    public static PlanDomain build(UUID id, SedeDomain sede, VehiculoDomain vehiculo, ClienteDomain cliente, TipoPlanDomain tipoPlan, EstadoDomain estado, LocalDate fechaInicio, LocalDate fechaFin) {
        return new PlanDomain(id, sede, vehiculo, cliente, tipoPlan,estado, fechaInicio, fechaFin);
    }

    public static PlanDomain build(UUID id) {
        return new PlanDomain(id, SedeDomain.build(), VehiculoDomain.build(), ClienteDomain.build(), TipoPlanDomain.build(), EstadoDomain.build(), LocalDate.now(), LocalDate.now());
    }

    public static PlanDomain build() {
        return new PlanDomain(UUIDHelper.getDefault(), SedeDomain.build(), VehiculoDomain.build(), ClienteDomain.build(), TipoPlanDomain.build(), EstadoDomain.build(), LocalDate.now(), LocalDate.now());
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public void setSede(SedeDomain sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDomain.build());
    }

    public void setVehiculo(VehiculoDomain vehiculo) {
        this.vehiculo = ObjectHelper.getObjectHelper().getDefaultValue(vehiculo, VehiculoDomain.build());
    }

    public void setCliente(ClienteDomain cliente) {
        this.cliente = ObjectHelper.getObjectHelper().getDefaultValue(cliente, ClienteDomain.build());
    }

    public void setTipoPlan(TipoPlanDomain tipoPlan) {
        this.tipoPlan = ObjectHelper.getObjectHelper().getDefaultValue(tipoPlan, TipoPlanDomain.build());
    }

    public EstadoDomain getEstado() {
        return estado;
    }

    public void setEstado(EstadoDomain estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDomain.build());
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public UUID getId() {
        return id;
    }

    public SedeDomain getSede() {
        return sede;
    }

    public VehiculoDomain getVehiculo() {
        return vehiculo;
    }

    public ClienteDomain getCliente() {
        return cliente;
    }

    public TipoPlanDomain getTipoPlan() {
        return tipoPlan;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
