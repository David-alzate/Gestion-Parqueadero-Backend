package co.com.park.gp.business.domain.empleados;

import java.util.UUID;

import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class EmpleadoDomain {

	private UUID id;
	private TipoIdentificacionDomain tipoIdentificacion;
	private Long numeroIdentificacion;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private TipoEmpleadoDomain tipoEmpleado;
	private SedeDomain sede;
	private String password;

	public EmpleadoDomain(final UUID id, final TipoIdentificacionDomain tipoIdentificacion,
			final Long numeroIdentificacion, final String nombre, final String apellido, final String correoElectronico,
			final TipoEmpleadoDomain tipoEmpleado, final SedeDomain sede, final String password) {
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

	public static EmpleadoDomain build(final UUID id, final TipoIdentificacionDomain tipoIdentificacion,
			final Long numeroIdentificacion, final String nombre, final String apellido, final String correoElectronico,
			final TipoEmpleadoDomain tipoEmpleado, final SedeDomain sede, final String password) {
		return new EmpleadoDomain(id, tipoIdentificacion, numeroIdentificacion, nombre, apellido, correoElectronico,
				tipoEmpleado, sede, password);
	}

	public static EmpleadoDomain build(final UUID id) {
		return new EmpleadoDomain(id, TipoIdentificacionDomain.build(), 0L, TextHelper.EMPTY, TextHelper.EMPTY,
				TextHelper.EMPTY, TipoEmpleadoDomain.build(), SedeDomain.build(), TextHelper.EMPTY);
	}

	public static EmpleadoDomain build() {
		return new EmpleadoDomain(UUIDHelper.getDefault(), TipoIdentificacionDomain.build(), 0L, TextHelper.EMPTY,
				TextHelper.EMPTY, TextHelper.EMPTY, TipoEmpleadoDomain.build(), SedeDomain.build(), TextHelper.EMPTY);
	}

	public final void setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}

	public final void setTipoIdentificacion(TipoIdentificacionDomain tipoIdentificacion) {
		this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion,
				TipoIdentificacionDomain.build());
	}

	public final void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public final void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}

	public final void setApellido(String apellido) {
		this.apellido = TextHelper.applyTrim(apellido);
	}

	public final void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = TextHelper.applyTrim(correoElectronico);
	}

	public final void setTipoEmpleado(TipoEmpleadoDomain tipoEmpleado) {
		this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoDomain.build());
	}

	public final void setSede(SedeDomain sede) {
		this.sede = ObjectHelper.getObjectHelper().getDefaultValue(sede, SedeDomain.build());
	}

	public final void setPassword(String password) {
		this.password = TextHelper.applyTrim(password);
	}

	public final UUID getId() {
		return id;
	}

	public final TipoIdentificacionDomain getTipoIdentificacion() {
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

	public final TipoEmpleadoDomain getTipoEmpleado() {
		return tipoEmpleado;
	}

	public final SedeDomain getSede() {
		return sede;
	}

	public final String getPassword() {
		return password;
	}

}
