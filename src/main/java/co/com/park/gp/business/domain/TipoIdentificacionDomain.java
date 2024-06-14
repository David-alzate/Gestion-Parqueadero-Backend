package co.com.park.gp.business.domain;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoIdentificacionDomain {

	private UUID id;
	private String nombre;

	public TipoIdentificacionDomain(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static final TipoIdentificacionDomain build(final UUID id, final String nombre) {
		return new TipoIdentificacionDomain(id, nombre);
	}

	public static final TipoIdentificacionDomain build(final UUID id) {
		return new TipoIdentificacionDomain(id, TextHelper.EMPTY);
	}

	public static final TipoIdentificacionDomain build() {
		return new TipoIdentificacionDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
	}

	public final void setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}

	public final void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}

	public final UUID getId() {
		return id;
	}

	public final String getNombre() {
		return nombre;
	}

}
