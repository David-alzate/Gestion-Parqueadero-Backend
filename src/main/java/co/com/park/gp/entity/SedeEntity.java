package co.com.park.gp.entity;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class SedeEntity {

	private UUID id;
	private ParqueaderoEntity parqueadero;
	private String nombre;
	private CiudadEntity ciudad;
	private String direccion;
	private String correoElectronico;
	private int celdasCarro;
	private int celdasMoto;
	private int celdascamion;
	private TipoSedeEntity tipoSede;
	private PaisEntity pais;
	private DepartamentoEntity departamento;

	public SedeEntity() {
		setId(UUIDHelper.getDefault());
		setParqueadero(ParqueaderoEntity.build());
		setNombre(TextHelper.EMPTY);
		setCiudad(CiudadEntity.build());
		setDireccion(TextHelper.EMPTY);
		setCorreoElectronico(TextHelper.EMPTY);
		setCeldasCarro(0);
		setCeldasMoto(0);
		setCeldascamion(0);
		setTipoSede(TipoSedeEntity.build());
		setPais(PaisEntity.build());
		setDepartamento(DepartamentoEntity.build());
	}

	public SedeEntity(final UUID id, final ParqueaderoEntity parqueadero, final String nombre,
			final CiudadEntity ciudad, final String direccion, final String correoElectronico, final int celdasCarro,
			final int celdasMoto, final int celdascamion, final TipoSedeEntity tipoSede, final PaisEntity pais,
			final DepartamentoEntity departamento) {
		setId(id);
		setParqueadero(parqueadero);
		setNombre(nombre);
		setCiudad(ciudad);
		setDireccion(direccion);
		setCorreoElectronico(correoElectronico);
		setCeldasCarro(celdasCarro);
		setCeldasMoto(celdasMoto);
		setCeldascamion(celdascamion);
		setTipoSede(tipoSede);
		setPais(pais);
		setDepartamento(departamento);
	}

	public static final SedeEntity build() {
		return new SedeEntity();
	}

	public final SedeEntity setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}

	public final SedeEntity setParqueadero(final ParqueaderoEntity parqueadero) {
		this.parqueadero = ObjectHelper.getObjectHelper().getDefaultValue(parqueadero, new ParqueaderoEntity());
		return this;
	}

	public final SedeEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final SedeEntity setCiudad(final CiudadEntity ciudad) {
		this.ciudad = ObjectHelper.getObjectHelper().getDefaultValue(ciudad, new CiudadEntity());
		return this;
	}

	public final SedeEntity setDireccion(final String direccion) {
		this.direccion = TextHelper.applyTrim(direccion);
		return this;
	}

	public final SedeEntity setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = TextHelper.applyTrim(correoElectronico);
		return this;
	}

	public final SedeEntity setCeldasCarro(final int celdasCarro) {
		this.celdasCarro = celdasCarro;
		return this;
	}

	public final SedeEntity setCeldasMoto(final int celdasMoto) {
		this.celdasMoto = celdasMoto;
		return this;
	}

	public final SedeEntity setCeldascamion(final int celdascamion) {
		this.celdascamion = celdascamion;
		return this;
	}

	public final SedeEntity setTipoSede(final TipoSedeEntity tipoSede) {
		this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, new TipoSedeEntity());
		return this;
	}

	public final SedeEntity setPais(final PaisEntity pais) {
		this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, new PaisEntity());
		return this;
	}

	public final SedeEntity setDepartamento(final DepartamentoEntity departamento) {
		this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoEntity());
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final ParqueaderoEntity getParqueadero() {
		return parqueadero;
	}

	public final String getNombre() {
		return nombre;
	}

	public final CiudadEntity getCiudad() {
		return ciudad;
	}

	public final String getDireccion() {
		return direccion;
	}

	public final String getCorreoElectronico() {
		return correoElectronico;
	}

	public final int getCeldasCarro() {
		return celdasCarro;
	}

	public final int getCeldasMoto() {
		return celdasMoto;
	}

	public final int getCeldascamion() {
		return celdascamion;
	}

	public final TipoSedeEntity getTipoSede() {
		return tipoSede;
	}

	public final PaisEntity getPais() {
		return pais;
	}

	public final DepartamentoEntity getDepartamento() {
		return departamento;
	}

}
