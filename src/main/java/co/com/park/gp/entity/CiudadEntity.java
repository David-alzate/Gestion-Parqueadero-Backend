package co.com.park.gp.entity;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class CiudadEntity {

	private UUID id;
	private String nombre;
	private DepartamentoEntity departamento;

	public CiudadEntity() {
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
		setDepartamento(DepartamentoEntity.build());
	}

	public CiudadEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}

	public static final CiudadEntity build() {
		return new CiudadEntity();
	}

	public final CiudadEntity setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final CiudadEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final CiudadEntity setDepartamento(final DepartamentoEntity departamento) {
		this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoEntity());
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final String getNombre() {
		return nombre;
	}

	public final DepartamentoEntity getDepartamento() {
		return departamento;
	}

}
