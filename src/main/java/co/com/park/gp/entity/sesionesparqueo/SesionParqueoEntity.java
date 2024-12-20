package co.com.park.gp.entity.sesionesparqueo;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public final class SesionParqueoEntity {

    private UUID id;
    private SedeEntity sede;
    private String placa;
    private TipoVehiculoEntity tipoVehiculo;
    private EmpleadoEntity empleado;
    private EstadoEntity estado;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;

    public SesionParqueoEntity() {
        setId(UUIDHelper.getDefault());
        setSede(SedeEntity.build());
        setPlaca(TextHelper.EMPTY);
        setTipoVehiculo(TipoVehiculoEntity.build());
        setEmpleado(EmpleadoEntity.build());
        setEstado(EstadoEntity.build());
        setFechaHoraIngreso(LocalDateTime.now().withSecond(0).withNano(0));
        setFechaHoraSalida(LocalDateTime.of(0, 1, 1, 0, 0));
    }

    public SesionParqueoEntity(UUID id, SedeEntity sede, String placa, TipoVehiculoEntity tipoVehiculo, EmpleadoEntity empleado, EstadoEntity estado, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida) {
        setId(id);
        setSede(sede);
        setPlaca(placa);
        setTipoVehiculo(tipoVehiculo);
        setEmpleado(empleado);
        setEstado(estado);
        setFechaHoraIngreso(fechaHoraIngreso);
        setFechaHoraSalida(fechaHoraSalida);
    }

    public static SesionParqueoEntity build(){
        return new SesionParqueoEntity();
    }

    public UUID getId() {
        return id;
    }

    public SesionParqueoEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public SesionParqueoEntity setSede(SedeEntity sede) {
        this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeEntity.build());
        return this;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    public SesionParqueoEntity setTipoVehiculo(TipoVehiculoEntity tipoVehiculoEntity) {
        this.tipoVehiculo = ObjectHelper.getObjectHelper().getDefaultValue(tipoVehiculoEntity, TipoVehiculoEntity.build());
        return this;
    }

    public SesionParqueoEntity setPlaca(String placa) {
        this.placa = TextHelper.applyTrim(placa);
        return this;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public SesionParqueoEntity setEmpleado(EmpleadoEntity empleado) {
        this.empleado = ObjectHelper.getObjectHelper().getDefaultValue(empleado, EmpleadoEntity.build());
        return this;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public SesionParqueoEntity setEstado(EstadoEntity estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoEntity.build());
        return this;
    }

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public SesionParqueoEntity setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
        return this;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public SesionParqueoEntity setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
        return this;
    }
}