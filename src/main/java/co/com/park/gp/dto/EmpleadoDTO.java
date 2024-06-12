package co.com.park.gp.dto;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public class EmpleadoDTO {

	private UUID id;
	private TipoIdentificacionDTO tipoIdentificacion;
	private int numeroIdentificacion;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private TipoEmpleadoDTO tipoEmpleado;
	private SedeDTO sede;

	public EmpleadoDTO() {
		setId(UUIDHelper.getDefault());
		setTipoIdentificacion(TipoIdentificacionDTO.build());
		setNumeroIdentificacion(0);
		setNombre(TextHelper.EMPTY);
		setApellido(TextHelper.EMPTY);
		setCorreoElectronico(TextHelper.EMPTY);
		setTipoEmpleado(TipoEmpleadoDTO.build());
		setSede(SedeDTO.build());
	}

	public EmpleadoDTO(final UUID id, final TipoIdentificacionDTO tipoIdentificacion, final int numeroIdentificacion,
			final String nombre, final String apellido, final String correoElectronico,
			final TipoEmpleadoDTO tipoEmpleado, final SedeDTO sede) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setNumeroIdentificacion(numeroIdentificacion);
		setNombre(nombre);
		setApellido(apellido);
		setCorreoElectronico(correoElectronico);
		setTipoEmpleado(tipoEmpleado);
		setSede(sede);
	}

	public final EmpleadoDTO setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final EmpleadoDTO setTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacion) {
		this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion, TipoIdentificacionDTO.build());
		return this;
	}

	public final EmpleadoDTO setNumeroIdentificacion(int numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
		return this;
	}

	public final EmpleadoDTO setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final EmpleadoDTO setApellido(String apellido) {
		this.apellido = TextHelper.applyTrim(apellido);
		return this;
	}

	public final EmpleadoDTO setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = TextHelper.applyTrim(correoElectronico);
		return this;
	}

	public final EmpleadoDTO setTipoEmpleado(TipoEmpleadoDTO tipoEmpleado) {
		this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoDTO.build());
		return this;
	}

	public final EmpleadoDTO setSede(SedeDTO sede) {
		this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDTO.build());
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final TipoIdentificacionDTO getTipoIdentificacion() {
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

	public final TipoEmpleadoDTO getTipoEmpleado() {
		return tipoEmpleado;
	}

	public final SedeDTO getSede() {
		return sede;
	}

}
