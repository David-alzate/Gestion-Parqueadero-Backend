package co.com.park.gp.business.assembler.entity.impl.planes;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.planes.TipoPlanDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.planes.TipoPlanEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoPlanAssemblerEntity implements AssemblerEntity<TipoPlanDomain, TipoPlanEntity> {

    private static final TipoPlanAssemblerEntity INSTANCE = new TipoPlanAssemblerEntity();

    private TipoPlanAssemblerEntity() {
        super();
    }

    public static TipoPlanAssemblerEntity getInstance() {
        return INSTANCE;
    }


    @Override
    public TipoPlanEntity toEntity(TipoPlanDomain domain) {
        var tipoPlanDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TipoPlanDomain.build());
        return TipoPlanEntity.build().setId(tipoPlanDomainTmp.getId()).setNombre(tipoPlanDomainTmp.getNombre());
    }

    @Override
    public List<TipoPlanEntity> toEntityCollection(List<TipoPlanDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<TipoPlanDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public TipoPlanDomain toDomain(TipoPlanEntity data) {
        var tipoPlanEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TipoPlanEntity.build());
        return TipoPlanDomain.build(tipoPlanEntityTmp.getId(), tipoPlanEntityTmp.getNombre());
    }

    @Override
    public List<TipoPlanDomain> toDomainCollection(List<TipoPlanEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<TipoPlanEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
