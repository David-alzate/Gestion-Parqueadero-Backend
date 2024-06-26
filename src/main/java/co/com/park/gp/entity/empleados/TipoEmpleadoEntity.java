package co.com.park.gp.entity.empleados;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class TipoEmpleadoEntity {

	private UUID id;
	private String nombre;

	public TipoEmpleadoEntity() {
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
	}

	public TipoEmpleadoEntity(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static TipoEmpleadoEntity build() {
		return new TipoEmpleadoEntity();
	}

	public final TipoEmpleadoEntity setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final TipoEmpleadoEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final String getNombre() {
		return nombre;
	}

	public final UUID getId() {
		return id;
	}

}
