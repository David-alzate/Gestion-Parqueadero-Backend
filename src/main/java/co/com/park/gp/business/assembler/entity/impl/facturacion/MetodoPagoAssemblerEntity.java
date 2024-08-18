package co.com.park.gp.business.assembler.entity.impl.facturacion;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.facturacion.MetodoPagoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.facturacion.MetodoPagoEntity;

import java.util.ArrayList;
import java.util.List;

public class MetodoPagoAssemblerEntity implements AssemblerEntity<MetodoPagoDomain, MetodoPagoEntity> {

    private static final AssemblerEntity<MetodoPagoDomain, MetodoPagoEntity> INSTANCE = new MetodoPagoAssemblerEntity();

    private MetodoPagoAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<MetodoPagoDomain, MetodoPagoEntity> getInstance() {
        return INSTANCE;
    }

    @Override
    public MetodoPagoEntity toEntity(MetodoPagoDomain domain) {
        var metodoPagoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, MetodoPagoDomain.build());
        return MetodoPagoEntity.build().setId(metodoPagoDomainTmp.getId())
                .setNombre(metodoPagoDomainTmp.getNombre());
    }

    @Override
    public List<MetodoPagoEntity> toEntityCollection(List<MetodoPagoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<MetodoPagoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public MetodoPagoDomain toDomain(MetodoPagoEntity data) {
        var metodoPagoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, MetodoPagoEntity.build());
        return MetodoPagoDomain.build(metodoPagoEntityTmp.getId(), metodoPagoEntityTmp.getNombre());
    }

    @Override
    public List<MetodoPagoDomain> toDomainCollection(List<MetodoPagoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<MetodoPagoEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
