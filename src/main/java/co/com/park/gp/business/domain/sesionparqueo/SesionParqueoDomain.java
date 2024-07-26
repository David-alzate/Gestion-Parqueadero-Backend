package co.com.park.gp.business.domain.sesionparqueo;

import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public final class SesionParqueoDomain {

    private UUID id;
    private SedeDomain sede;
    private VehiculoDomain vehiculo;
    private EmpleadoDomain empleado;
    private EstadoDomain estado;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;

    public SesionParqueoDomain(UUID id, SedeDomain sede, VehiculoDomain vehiculo, EmpleadoDomain empleado, EstadoDomain estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida) {
        this.id = id;
        this.sede = sede;
        this.vehiculo = vehiculo;
        this.empleado = empleado;
        this.estado = estado;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public static SesionParqueoDomain build(UUID id, SedeDomain sede, VehiculoDomain vehiculo, EmpleadoDomain empleado, EstadoDomain estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida){
        return new SesionParqueoDomain(id, sede, vehiculo, empleado, estado, fechaHoraIngreso, fechaHoraSalida);
    }

    public static SesionParqueoDomain build(UUID id){
        return new SesionParqueoDomain(id, SedeDomain.build(), VehiculoDomain.build(), EmpleadoDomain.build(), EstadoDomain.build(), LocalDateTime.now(), LocalDateTime.of(0, 1, 1, 0, 0, 0));
    }

    public static SesionParqueoDomain build(){
        return new SesionParqueoDomain(UUIDHelper.getDefault(), SedeDomain.build(), VehiculoDomain.build(), EmpleadoDomain.build(), EstadoDomain.build(), LocalDateTime.now(), LocalDateTime.of(0, 1, 1, 0, 0, 0));
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

    public void setEmpleado(EmpleadoDomain empleado) {
        this.empleado = ObjectHelper.getObjectHelper().getDefaultValue(empleado, EmpleadoDomain.build());
    }

    public void setEstado(EstadoDomain estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDomain.build());
    }

    public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
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

    public EmpleadoDomain getEmpleado() {
        return empleado;
    }

    public EstadoDomain getEstado() {
        return estado;
    }

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }
}
