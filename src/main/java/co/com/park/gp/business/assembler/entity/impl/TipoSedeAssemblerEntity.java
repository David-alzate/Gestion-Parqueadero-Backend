package co.com.park.gp.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.TipoSedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.TipoSedeEntity;

public class TipoSedeAssemblerEntity implements AssemblerEntity<TipoSedeDomain, TipoSedeEntity> {

	private static final AssemblerEntity<TipoSedeDomain, TipoSedeEntity> INSTANCE = new TipoSedeAssemblerEntity();

	private TipoSedeAssemblerEntity() {
		super();
	}

	public static AssemblerEntity<TipoSedeDomain, TipoSedeEntity> getInstance() {
		return INSTANCE;
	}

	@Override
	public TipoSedeDomain toDomain(TipoSedeEntity data) {
		var tipoSedeEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TipoSedeEntity.build());
		return TipoSedeDomain.build(tipoSedeEntityTmp.getId(), tipoSedeEntityTmp.getNombre());
	}

	@Override
	public TipoSedeEntity toEntity(TipoSedeDomain domain) {
		var tipoSedeDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TipoSedeDomain.build());
		return TipoSedeEntity.build().setId(tipoSedeDomainTmp.getId()).setNombre(tipoSedeDomainTmp.getNombre());
	}

	@Override
	public List<TipoSedeDomain> toDomainCollection(List<TipoSedeEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<TipoSedeEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<TipoSedeEntity> toEntityCollection(List<TipoSedeDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<TipoSedeDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
