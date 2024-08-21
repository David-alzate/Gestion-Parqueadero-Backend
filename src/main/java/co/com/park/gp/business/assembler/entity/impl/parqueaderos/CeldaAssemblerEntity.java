package co.com.park.gp.business.assembler.entity.impl.parqueaderos;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.parqueaderos.CeldaDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

import java.util.ArrayList;
import java.util.List;

public class CeldaAssemblerEntity implements AssemblerEntity<CeldaDomain, CeldaEntity> {

    private static final AssemblerEntity<SedeDomain, SedeEntity> sedeAssembler = SedeAssemblerEntity.getInstance();

    private static final AssemblerEntity<TipoVehiculoDomain, TipoVehiculoEntity> tipoVehiculoAssembler = TipoVehiculoAssemblerEntity.getInstance();

    private static final AssemblerEntity<CeldaDomain, CeldaEntity> instance = new CeldaAssemblerEntity();

    private CeldaAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<CeldaDomain, CeldaEntity> getInstance() {
        return instance;
    }

    @Override
    public CeldaEntity toEntity(CeldaDomain domain) {
        var celdaDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, CeldaDomain.build());
        var sedeEntity = sedeAssembler.toEntity(celdaDomainTmp.getSede());
        var tipoVehiculoEntity = tipoVehiculoAssembler.toEntity(celdaDomainTmp.getTipoVehiculo());
        return CeldaEntity.build().setId(celdaDomainTmp.getId()).setSede(sedeEntity)
                .setTipoVehiculo(tipoVehiculoEntity).setCantidadCeldas(celdaDomainTmp.getCantidadCeldas());
    }

    @Override
    public List<CeldaEntity> toEntityCollection(List<CeldaDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<CeldaDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public CeldaDomain toDomain(CeldaEntity data) {
        var celdaEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, CeldaEntity.build());
        var sedeDomain = sedeAssembler.toDomain(celdaEntityTmp.getSede());
        var tipoVehiculoDomain = tipoVehiculoAssembler.toDomain(celdaEntityTmp.getTipoVehiculo());
        return CeldaDomain.build(celdaEntityTmp.getId(), sedeDomain, tipoVehiculoDomain, celdaEntityTmp.getCantidadCeldas());
    }

    @Override
    public List<CeldaDomain> toDomainCollection(List<CeldaEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<CeldaEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
