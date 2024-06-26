package co.com.park.gp.business.assembler.entity.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CiudadDomain;
import co.com.park.gp.business.domain.parqueaderos.DepartamentoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.parqueaderos.CiudadEntity;
import co.com.park.gp.entity.parqueaderos.DepartamentoEntity;

public final class CiudadAssemblerEntity implements AssemblerEntity<CiudadDomain, CiudadEntity> {

    private static final AssemblerEntity<CiudadDomain, CiudadEntity> instance = new CiudadAssemblerEntity();

    private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity
            .getInstance();

    private CiudadAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<CiudadDomain, CiudadEntity> getInstance() {
        return instance;
    }

    @Override
    public final CiudadDomain toDomain(final CiudadEntity data) {
        var ciudadEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, CiudadEntity.build());
        var departamentoDomain = departamentoAssembler.toDomain(ciudadEntityTmp.getDepartamento());
        return CiudadDomain.build(ciudadEntityTmp.getId(), ciudadEntityTmp.getNombre(), departamentoDomain);
    }

    @Override
    public final CiudadEntity toEntity(final CiudadDomain domain) {
        var ciudadDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, CiudadDomain.build());
        var departamentoEntity = departamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
        return CiudadEntity.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre())
                .setDepartamento(departamentoEntity);
    }

    @Override
    public List<CiudadDomain> toDomainCollection(List<CiudadEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<CiudadEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public List<CiudadEntity> toEntityCollection(List<CiudadDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<CiudadDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

}
