package co.com.park.gp.business.domain;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoSedeDomain {

	private UUID id;
	private String nombre;

	private TipoSedeDomain(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static TipoSedeDomain build(final UUID id, final String nombre) {
		return new TipoSedeDomain(id, nombre);
	}

	public static TipoSedeDomain build(final UUID id) {
		return new TipoSedeDomain(id, TextHelper.EMPTY);
	}

	public static TipoSedeDomain build() {
		return new TipoSedeDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
	}

	private final void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}

	private final void setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}

	public final UUID getId() {
		return id;
	}

	public final String getNombre() {
		return nombre;
	}

}