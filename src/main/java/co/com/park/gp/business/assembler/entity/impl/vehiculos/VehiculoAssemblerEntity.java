package co.com.park.gp.business.assembler.entity.impl.vehiculos;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.util.ArrayList;
import java.util.List;


public class VehiculoAssemblerEntity implements AssemblerEntity<VehiculoDomain, VehiculoEntity> {

    private static final AssemblerEntity<VehiculoDomain, VehiculoEntity> instance = new VehiculoAssemblerEntity();

    private static final AssemblerEntity<TipoVehiculoDomain, TipoVehiculoEntity> tipoVehiculoAssemblerEntity = TipoVehiculoAssemblerEntity.getInstance();

    private VehiculoAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<VehiculoDomain, VehiculoEntity> getInstance() {
        return instance;
    }

    @Override
    public VehiculoEntity toEntity(VehiculoDomain domain) {
        var vehiculoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, VehiculoDomain.build());
        var tipoVehiculoEntity = tipoVehiculoAssemblerEntity.toEntity(vehiculoDomainTmp.getTipoVehiculo());
        return VehiculoEntity.build().setId(vehiculoDomainTmp.getId())
                .setTipoVehiculo(tipoVehiculoEntity)
                .setPlaca(vehiculoDomainTmp.getPlaca());
    }

    @Override
    public List<VehiculoEntity> toEntityCollection(List<VehiculoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<VehiculoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public VehiculoDomain toDomain(VehiculoEntity data) {
        var vehiculoEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, VehiculoEntity.build());
        var tipoVehiculoDomain = tipoVehiculoAssemblerEntity.toDomain(vehiculoEntityTmp.getTipoVehiculo());
        return VehiculoDomain.build(vehiculoEntityTmp.getId(), tipoVehiculoDomain, vehiculoEntityTmp.getPlaca());
    }

    @Override
    public List<VehiculoDomain> toDomainCollection(List<VehiculoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<VehiculoEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

}
