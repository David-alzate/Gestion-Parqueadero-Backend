package co.com.park.gp.business.assembler.entity.impl.tarifas;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.util.ArrayList;
import java.util.List;

public class EstadoAssemblerEntity implements AssemblerEntity<EstadoDomain, EstadoEntity> {

    public static final AssemblerEntity<EstadoDomain, EstadoEntity> instance = new EstadoAssemblerEntity();

    private EstadoAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<EstadoDomain, EstadoEntity> getInstance() {
        return instance;
    }

    @Override
    public EstadoEntity toEntity(EstadoDomain domain) {
        var estadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, EstadoDomain.build());
        return EstadoEntity.build().setId(estadoDomainTmp.getId())
                .setEstado(estadoDomainTmp.getEstado());
    }

    @Override
    public List<EstadoEntity> toEntityCollection(List<EstadoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<EstadoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public EstadoDomain toDomain(EstadoEntity data) {
        var estadoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, EstadoEntity.build());
        return EstadoDomain.build(estadoEntityTmp.getId(), estadoEntityTmp.getEstado());
    }

    @Override
    public List<EstadoDomain> toDomainCollection(List<EstadoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<EstadoEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
