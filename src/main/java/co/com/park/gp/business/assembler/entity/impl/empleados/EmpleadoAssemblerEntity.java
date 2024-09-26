package co.com.park.gp.business.assembler.entity.impl.empleados;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.empleados.TipoEmpleadoDomain;
import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

public final class EmpleadoAssemblerEntity implements AssemblerEntity<EmpleadoDomain, EmpleadoEntity> {

	private static final AssemblerEntity<TipoEmpleadoDomain, TipoEmpleadoEntity> tipoEmpleadoAssembler = TipoEmpleadoAssemblerEntity
			.getInstance();

	private static final AssemblerEntity<TipoIdentificacionDomain, TipoIdentificacionEntity> tipoIdentificacionAssembler = TipoIdentificacionAssemblerEntity
			.getInstance();

	private static final AssemblerEntity<SedeDomain, SedeEntity> sedeAssembler = SedeAssemblerEntity.getInstance();

	private static final AssemblerEntity<EstadoDomain, EstadoEntity> estadoAssembler = EstadoAssemblerEntity
			.getInstance();

	private static final AssemblerEntity<EmpleadoDomain, EmpleadoEntity> INSTANCE = new EmpleadoAssemblerEntity();

	private EmpleadoAssemblerEntity() {
		super();
	}

	public static AssemblerEntity<EmpleadoDomain, EmpleadoEntity> getInstance() {
		return INSTANCE;
	}

	@Override
	public EmpleadoDomain toDomain(final EmpleadoEntity data) {
		var empleadoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, EmpleadoEntity.build());
		var tipoEmpleadoDomain = tipoEmpleadoAssembler.toDomain(empleadoEntityTmp.getTipoEmpleado());
		var tipoIdentificacionDomain = tipoIdentificacionAssembler.toDomain(empleadoEntityTmp.getTipoIdentificacion());
		var sedeDomain = sedeAssembler.toDomain(empleadoEntityTmp.getSede());
		var estadoDomain = estadoAssembler.toDomain(empleadoEntityTmp.getEstado());
		return EmpleadoDomain.build(empleadoEntityTmp.getId(), tipoIdentificacionDomain,
				empleadoEntityTmp.getNumeroIdentificacion(), empleadoEntityTmp.getNombre(),
				empleadoEntityTmp.getApellido(), empleadoEntityTmp.getCorreoElectronico(), tipoEmpleadoDomain,
				sedeDomain, estadoDomain, empleadoEntityTmp.getPassword());
	}

	@Override
	public List<EmpleadoDomain> toDomainCollection(List<EmpleadoEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<EmpleadoEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public EmpleadoEntity toEntity(final EmpleadoDomain domain) {
		var empleadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, EmpleadoDomain.build());
		var tipoEmpleadoEntity = tipoEmpleadoAssembler.toEntity(empleadoDomainTmp.getTipoEmpleado());
		var tipoIdentificacionEntity = tipoIdentificacionAssembler.toEntity(empleadoDomainTmp.getTipoIdentificacion());
		var sedeEntity = sedeAssembler.toEntity(empleadoDomainTmp.getSede());
		var estadoEntity = estadoAssembler.toEntity(empleadoDomainTmp.getEstado());
		return EmpleadoEntity.build().setId(empleadoDomainTmp.getId()).setTipoIdentificacion(tipoIdentificacionEntity)
				.setNumeroIdentificacion(empleadoDomainTmp.getNumeroIdentificacion())
				.setNombre(empleadoDomainTmp.getNombre()).setApellido(empleadoDomainTmp.getApellido())
				.setCorreoElectronico(empleadoDomainTmp.getCorreoElectronico()).setTipoEmpleado(tipoEmpleadoEntity)
				.setSede(sedeEntity).setEstado(estadoEntity).setPassword(empleadoDomainTmp.getPassword());
	}

	@Override
	public List<EmpleadoEntity> toEntityCollection(List<EmpleadoDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<EmpleadoDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
