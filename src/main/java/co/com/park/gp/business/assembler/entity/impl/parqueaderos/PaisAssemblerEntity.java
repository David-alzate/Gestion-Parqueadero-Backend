package co.com.park.gp.business.assembler.entity.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.PaisDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.parqueaderos.PaisEntity;

public final class PaisAssemblerEntity implements AssemblerEntity<PaisDomain, PaisEntity> {

	private static final AssemblerEntity<PaisDomain, PaisEntity> instance = new PaisAssemblerEntity();

	private PaisAssemblerEntity() {
		super();
	}

	public static AssemblerEntity<PaisDomain, PaisEntity> getInstance() {
		return instance;
	}

	@Override
	public final PaisDomain toDomain(final PaisEntity data) {
		var paisEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, PaisEntity.build());
		return PaisDomain.build(paisEntityTmp.getId(), paisEntityTmp.getNombre());
	}

	@Override
	public final PaisEntity toEntity(final PaisDomain domain) {
		var paisDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisEntity.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

	@Override
	public List<PaisDomain> toDomainCollection(List<PaisEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<PaisEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<PaisEntity> toEntityCollection(List<PaisDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<PaisDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
