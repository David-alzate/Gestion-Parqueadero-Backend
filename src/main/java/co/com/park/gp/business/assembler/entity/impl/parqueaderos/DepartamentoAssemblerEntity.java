package co.com.park.gp.business.assembler.entity.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.DepartamentoDomain;
import co.com.park.gp.business.domain.parqueaderos.PaisDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.parqueaderos.DepartamentoEntity;
import co.com.park.gp.entity.parqueaderos.PaisEntity;

public final class DepartamentoAssemblerEntity implements AssemblerEntity<DepartamentoDomain, DepartamentoEntity> {

    private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> instance = new DepartamentoAssemblerEntity();
    private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler = PaisAssemblerEntity.getInstance();

    private DepartamentoAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<DepartamentoDomain, DepartamentoEntity> getInstance() {
        return instance;
    }

    @Override
    public final DepartamentoDomain toDomain(final DepartamentoEntity data) {
        var departamentoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, DepartamentoEntity.build());
        var paisDomain = paisAssembler.toDomain(departamentoEntityTmp.getPais());
        return DepartamentoDomain.build(departamentoEntityTmp.getId(), departamentoEntityTmp.getNombre(), paisDomain);
    }

    @Override
    public final DepartamentoEntity toEntity(final DepartamentoDomain domain) {
        var departamentoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, DepartamentoDomain.build());
        var paisEntity = paisAssembler.toEntity(departamentoDomainTmp.getPais());
        return DepartamentoEntity.build().setId(departamentoDomainTmp.getId())
                .setNombre(departamentoDomainTmp.getNombre()).setPais(paisEntity);
    }

    @Override
    public List<DepartamentoDomain> toDomainCollection(List<DepartamentoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<DepartamentoEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public List<DepartamentoEntity> toEntityCollection(List<DepartamentoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<DepartamentoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

}