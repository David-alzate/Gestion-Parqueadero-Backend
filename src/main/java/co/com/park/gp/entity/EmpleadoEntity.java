package co.com.park.gp.entity;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public class EmpleadoEntity {

	private UUID id;
	private TipoIdentificacionEntity tipoIdentificacion;
	private int numeroIdentificacion;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private TipoEmpleadoEntity tipoEmpleado;
	private SedeEntity sede;
	private String password;

	public EmpleadoEntity() {
		setId(UUIDHelper.getDefault());
		setTipoIdentificacion(TipoIdentificacionEntity.build());
		setNumeroIdentificacion(0);
		setNombre(TextHelper.EMPTY);
		setApellido(TextHelper.EMPTY);
		setCorreoElectronico(TextHelper.EMPTY);
		setTipoEmpleado(TipoEmpleadoEntity.build());
		setSede(SedeEntity.build());
		setPassword(TextHelper.EMPTY);
	}

	public EmpleadoEntity(final UUID id, final TipoIdentificacionEntity tipoIdentificacion,
			final int numeroIdentificacion, final String nombre, final String apellido, final String correoElectronico,
			final TipoEmpleadoEntity tipoEmpleado, final SedeEntity sede, final String password) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setNumeroIdentificacion(numeroIdentificacion);
		setNombre(nombre);
		setApellido(apellido);
		setCorreoElectronico(correoElectronico);
		setTipoEmpleado(tipoEmpleado);
		setSede(sede);
		setPassword(password);
	}

	public static final EmpleadoEntity build() {
		return new EmpleadoEntity();
	}

	public final EmpleadoEntity setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final EmpleadoEntity setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
		this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion,
				TipoIdentificacionEntity.build());
		return this;
	}

	public final EmpleadoEntity setNumeroIdentificacion(int numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
		return this;
	}

	public final EmpleadoEntity setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final EmpleadoEntity setApellido(String apellido) {
		this.apellido = TextHelper.applyTrim(apellido);
		return this;
	}

	public final EmpleadoEntity setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = TextHelper.applyTrim(correoElectronico);
		return this;
	}

	public final EmpleadoEntity setTipoEmpleado(TipoEmpleadoEntity tipoEmpleado) {
		this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoEntity.build());
		return this;
	}

	public final EmpleadoEntity setSede(SedeEntity sede) {
		this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeEntity.build());
		return this;
	}

	public final EmpleadoEntity setPassword(String password) {
		this.password = TextHelper.applyTrim(password);
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final TipoIdentificacionEntity getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public final int getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public final String getNombre() {
		return nombre;
	}

	public final String getApellido() {
		return apellido;
	}

	public final String getCorreoElectronico() {
		return correoElectronico;
	}

	public final TipoEmpleadoEntity getTipoEmpleado() {
		return tipoEmpleado;
	}

	public final SedeEntity getSede() {
		return sede;
	}

	public final String getPassword() {
		return password;
	}

}
