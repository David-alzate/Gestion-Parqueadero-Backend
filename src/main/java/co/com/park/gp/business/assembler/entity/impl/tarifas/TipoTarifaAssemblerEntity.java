package co.com.park.gp.business.assembler.entity.impl.tarifas;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.tarifas.TipoTarifaEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoTarifaAssemblerEntity implements AssemblerEntity<TipoTarifaDomain, TipoTarifaEntity> {

    public static final AssemblerEntity<TipoTarifaDomain, TipoTarifaEntity> instance = new TipoTarifaAssemblerEntity();

    private TipoTarifaAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<TipoTarifaDomain, TipoTarifaEntity> getInstance() {
        return instance;
    }

    @Override
    public TipoTarifaEntity toEntity(TipoTarifaDomain domain) {
        var tipoTarifaDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TipoTarifaDomain.build());
        return TipoTarifaEntity.build().setId(tipoTarifaDomainTmp.getId())
                .setTipoTarifa(tipoTarifaDomainTmp.getTipoTarifa());
    }

    @Override
    public List<TipoTarifaEntity> toEntityCollection(List<TipoTarifaDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<TipoTarifaDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public TipoTarifaDomain toDomain(TipoTarifaEntity data) {
        var tipoTarifaEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TipoTarifaEntity.build());
        return TipoTarifaDomain.build(tipoTarifaEntityTmp.getId(), tipoTarifaEntityTmp.getTipoTarifa());
    }

    @Override
    public List<TipoTarifaDomain> toDomainCollection(List<TipoTarifaEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<TipoTarifaEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
