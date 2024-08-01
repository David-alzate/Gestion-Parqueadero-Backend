package co.com.park.gp.dto.sesionesparqueo;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.empleados.EmpleadoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public final class SesionParqueoDTO {

    private UUID id;
    private SedeDTO sede;
    private String placa;
    private EmpleadoDTO empleado;
    private EstadoDTO estado;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;

    public SesionParqueoDTO() {
        setId(UUIDHelper.getDefault());
        setSede(SedeDTO.build());
        setPlaca(TextHelper.EMPTY);
        setEmpleado(EmpleadoDTO.build());
        setEstado(EstadoDTO.build());
        setFechaHoraIngreso(LocalDateTime.now().withSecond(0).withNano(0));
        setFechaHoraSalida(LocalDateTime.of(0, 1, 1, 0, 0));
    }

    public SesionParqueoDTO(UUID id, SedeDTO sede, String placa, EmpleadoDTO empleado, EstadoDTO estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida) {
        setId(id);
        setSede(sede);
        setPlaca(placa);
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

    public String getPlaca() {
        return placa;
    }

    public SesionParqueoDTO setPlaca(String placa) {
        this.placa = TextHelper.applyTrim(placa);
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