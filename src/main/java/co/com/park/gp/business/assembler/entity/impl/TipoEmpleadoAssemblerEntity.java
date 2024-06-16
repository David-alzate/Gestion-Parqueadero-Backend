package co.com.park.gp.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.TipoEmpleadoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.TipoEmpleadoEntity;

public final class TipoEmpleadoAssemblerEntity implements AssemblerEntity<TipoEmpleadoDomain, TipoEmpleadoEntity> {

	private static final AssemblerEntity<TipoEmpleadoDomain, TipoEmpleadoEntity> instance = new TipoEmpleadoAssemblerEntity();

	private TipoEmpleadoAssemblerEntity() {
		super();
	}

	public static AssemblerEntity<TipoEmpleadoDomain, TipoEmpleadoEntity> getInstance() {
		return instance;
	}

	@Override
	public TipoEmpleadoDomain toDomain(final TipoEmpleadoEntity data) {
		var tipoEmpleadoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TipoEmpleadoEntity.build());
		return TipoEmpleadoDomain.build(tipoEmpleadoEntityTmp.getId(), tipoEmpleadoEntityTmp.getNombre());
	}

	@Override
	public List<TipoEmpleadoDomain> toDomainCollection(List<TipoEmpleadoEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<TipoEmpleadoEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public TipoEmpleadoEntity toEntity(final TipoEmpleadoDomain domain) {
		var tipoEmpleadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TipoEmpleadoDomain.build());
		return TipoEmpleadoEntity.build().setId(tipoEmpleadoDomainTmp.getId())
				.setNombre(tipoEmpleadoDomainTmp.getNombre());
	}

	@Override
	public List<TipoEmpleadoEntity> toEntityCollection(List<TipoEmpleadoDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<TipoEmpleadoDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
