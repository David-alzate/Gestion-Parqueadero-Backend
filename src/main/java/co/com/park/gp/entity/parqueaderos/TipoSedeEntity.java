package co.com.park.gp.entity.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoSedeEntity {

	private UUID id;
	private String nombre;

	public TipoSedeEntity() {
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
	}

	public TipoSedeEntity(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static TipoSedeEntity build() {
		return new TipoSedeEntity();
	}

	public TipoSedeEntity setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public TipoSedeEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
