package co.com.park.gp.dto.empleados;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.comunes.TipoIdentificacionDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;

public final class EmpleadoDTO {

	private UUID id;
	private TipoIdentificacionDTO tipoIdentificacion;
	private Long numeroIdentificacion;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private TipoEmpleadoDTO tipoEmpleado;
	private SedeDTO sede;
	private EstadoDTO estado;
	private String password;

	public EmpleadoDTO() {
		setId(UUIDHelper.getDefault());
		setTipoIdentificacion(TipoIdentificacionDTO.build());
		setNumeroIdentificacion(0L);
		setNombre(TextHelper.EMPTY);
		setApellido(TextHelper.EMPTY);
		setCorreoElectronico(TextHelper.EMPTY);
		setTipoEmpleado(TipoEmpleadoDTO.build());
		setSede(SedeDTO.build());
		setEstado(EstadoDTO.build());
		setPassword(TextHelper.EMPTY);
	}

	public EmpleadoDTO(final UUID id, final TipoIdentificacionDTO tipoIdentificacion, final Long numeroIdentificacion,
			final String nombre, final String apellido, final String correoElectronico,
			final TipoEmpleadoDTO tipoEmpleado, final SedeDTO sede, final EstadoDTO estado, final String password) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setNumeroIdentificacion(numeroIdentificacion);
		setNombre(nombre);
		setApellido(apellido);
		setCorreoElectronico(correoElectronico);
		setTipoEmpleado(tipoEmpleado);
		setSede(sede);
		setEstado(estado);
		setPassword(password);
	}

	public static EmpleadoDTO build() {
		return new EmpleadoDTO();
	}

	public final EmpleadoDTO setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final EmpleadoDTO setTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacion) {
		this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion,
				TipoIdentificacionDTO.build());
		return this;
	}

	public final EmpleadoDTO setNumeroIdentificacion(Long numeroIdentificacion) {
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

	public final EmpleadoDTO setPassword(String password) {
		this.password = TextHelper.applyTrim(password);
		return this;
	}

	public final EstadoDTO getEstado() {
		return estado;
	}

	public final EmpleadoDTO setEstado(EstadoDTO estado) {
		this.estado = ObjectHelper.getObjectHelper().getDefaultValue(estado, EstadoDTO.build());
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final TipoIdentificacionDTO getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public final Long getNumeroIdentificacion() {
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

	public final String getPassword() {
		return password;
	}

}
