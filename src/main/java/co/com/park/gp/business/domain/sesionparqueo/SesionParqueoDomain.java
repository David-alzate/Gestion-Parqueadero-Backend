package co.com.park.gp.business.domain.sesionparqueo;

import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public final class SesionParqueoDomain {

    private UUID id;
    private SedeDomain sede;
    private String placa;
    private TipoVehiculoDomain tipoVehiculo;
    private EmpleadoDomain empleado;
    private EstadoDomain estado;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;

    public SesionParqueoDomain(UUID id, SedeDomain sede, String placa,TipoVehiculoDomain tipoVehiculo, EmpleadoDomain empleado, EstadoDomain estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida) {
        setId(id);
        setSede(sede);
        setPlaca(placa);
        setTipoVehiculo(tipoVehiculo);
        setEmpleado(empleado);
        setEstado(estado);
        setFechaHoraIngreso(fechaHoraIngreso);
        setFechaHoraSalida(fechaHoraSalida);
    }

    public static SesionParqueoDomain build(UUID id, SedeDomain sede, String placa,TipoVehiculoDomain tipoVehiculo, EmpleadoDomain empleado, EstadoDomain estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida){
        return new SesionParqueoDomain(id, sede, placa,tipoVehiculo, empleado, estado, fechaHoraIngreso, fechaHoraSalida);
    }

    public static SesionParqueoDomain build(UUID id){
        return new SesionParqueoDomain(id, SedeDomain.build(), TextHelper.EMPTY, TipoVehiculoDomain.build(), EmpleadoDomain.build(), EstadoDomain.build(), LocalDateTime.now().withSecond(0).withNano(0), LocalDateTime.of(0, 1, 1, 0, 0));
    }

    public static SesionParqueoDomain build(){
        return new SesionParqueoDomain(UUIDHelper.getDefault(), SedeDomain.build(), TextHelper.EMPTY,TipoVehiculoDomain.build(), EmpleadoDomain.build(), EstadoDomain.build(), LocalDateTime.now().withSecond(0).withNano(0), LocalDateTime.of(0, 1, 1, 0, 0));
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public void setSede(SedeDomain sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDomain.build());
    }

    public void setPlaca(String placa) {
        this.placa = TextHelper.applyTrim(placa);
    }

    public TipoVehiculoDomain getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculoDomain tipoVehiculo) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculo, TipoVehiculoDomain.build());
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

    public String getPlaca() {
        return placa;
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
