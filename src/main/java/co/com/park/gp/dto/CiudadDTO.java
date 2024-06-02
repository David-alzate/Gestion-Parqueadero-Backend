package co.com.park.gp.dto;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class CiudadDTO {

	private UUID id;
	private String nombre;
	private DepartamentoDTO departamento;

	public CiudadDTO() {
		super();
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
		setDepartamento(DepartamentoDTO.build());
	}

	public CiudadDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}

	public static final CiudadDTO build() {
		return new CiudadDTO();
	}

	public final CiudadDTO setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final CiudadDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
		this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoDTO());
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final String getNombre() {
		return nombre;
	}

	public final DepartamentoDTO getDepartamento() {
		return departamento;
	}

}
