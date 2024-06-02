package co.com.park.gp.dto;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class ParqueaderoDTO {

	private UUID id;
	private String nombre;

	public ParqueaderoDTO() {
		super();
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
	}

	public ParqueaderoDTO(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}
	
	public static final ParqueaderoDTO build() {
		return new ParqueaderoDTO();
	}

	public final ParqueaderoDTO setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final ParqueaderoDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final String getNombre() {
		return nombre;
	}

}
