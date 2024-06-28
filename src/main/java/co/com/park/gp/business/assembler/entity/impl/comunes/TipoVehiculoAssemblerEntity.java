package co.com.park.gp.business.assembler.entity.impl.comunes;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoVehiculoAssemblerEntity implements AssemblerEntity<TipoVehiculoDomain, TipoVehiculoEntity> {

    public static final AssemblerEntity<TipoVehiculoDomain, TipoVehiculoEntity> instance = new TipoVehiculoAssemblerEntity();

    private TipoVehiculoAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<TipoVehiculoDomain, TipoVehiculoEntity> getInstance() {
        return instance;
    }

    @Override
    public TipoVehiculoEntity toEntity(TipoVehiculoDomain domain) {
        var tipoVehiculoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TipoVehiculoDomain.build());
        return TipoVehiculoEntity.build().setId(tipoVehiculoDomainTmp.getId())
                .setTipoVehiculo(tipoVehiculoDomainTmp.getTipoVehiculo());
    }

    @Override
    public List<TipoVehiculoEntity> toEntityCollection(List<TipoVehiculoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<TipoVehiculoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public TipoVehiculoDomain toDomain(TipoVehiculoEntity data) {
        var tipoVehiculoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TipoVehiculoEntity.build());
        return TipoVehiculoDomain.build(tipoVehiculoEntityTmp.getId(), tipoVehiculoEntityTmp.getTipoVehiculo());
    }

    @Override
    public List<TipoVehiculoDomain> toDomainCollection(List<TipoVehiculoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<TipoVehiculoEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
