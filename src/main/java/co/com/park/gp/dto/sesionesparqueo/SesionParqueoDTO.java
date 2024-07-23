package co.com.park.gp.dto.sesionesparqueo;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.empleados.EmpleadoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public final class SesionParqueoDTO {

    private UUID id;
    private SedeDTO sede;
    private VehiculoDTO vehiculo;
    private EmpleadoDTO empleado;
    private EstadoDTO estado;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;

    public SesionParqueoDTO() {
        setId(UUIDHelper.generate());
        setSede(SedeDTO.build());
        setVehiculo(VehiculoDTO.build());
        setEmpleado(EmpleadoDTO.build());
        setEstado(EstadoDTO.build());
        setFechaHoraIngreso(LocalDateTime.now());
        setFechaHoraSalida(LocalDateTime.now());
    }

    public SesionParqueoDTO(UUID id, SedeDTO sede, VehiculoDTO vehiculo, EmpleadoDTO empleado, EstadoDTO estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida) {
        setId(id);
        setSede(sede);
        setVehiculo(vehiculo);
        setEmpleado(empleado);
        setEstado(estado);
        setFechaHoraIngreso(fechaHoraIngreso);
        setFechaHoraSalida(fechaHoraSalida);
    }

    public static SesionParqueoDTO build(){
        return new SesionParqueoDTO();
    }

    public UUID getId() {
        return id;
    }

    public SesionParqueoDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SedeDTO getSede() {
        return sede;
    }

    public SesionParqueoDTO setSede(SedeDTO sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDTO.build());
        return this;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public SesionParqueoDTO setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = ObjectHelper.getObjectHelper().getDefaultValue(vehiculo, VehiculoDTO.build());
        return this;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public SesionParqueoDTO setEmpleado(EmpleadoDTO empleado) {
        this.empleado = ObjectHelper.getObjectHelper().getDefaultValue(empleado, EmpleadoDTO.build());
        return this;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public SesionParqueoDTO setEstado(EstadoDTO estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDTO.build());
        return this;
    }

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public SesionParqueoDTO setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
        return this;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public SesionParqueoDTO setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
        return this;
    }
}