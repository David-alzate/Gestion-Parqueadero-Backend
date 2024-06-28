package co.com.park.gp.business.assembler.entity.impl.tarifas;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoVehiculoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;
import co.com.park.gp.entity.tarifas.TipoTarifaEntity;

import java.util.ArrayList;
import java.util.List;

public class TarifaAssemblerEntity implements AssemblerEntity<TarifaDomain, TarifaEntity> {

    private static final AssemblerEntity<TipoTarifaDomain, TipoTarifaEntity> tipoTarifaAssembler = TipoTarifaAssemblerEntity.getInstance();
    private static final AssemblerEntity<EstadoDomain, EstadoEntity> estadoAssembler = EstadoAssemblerEntity.getInstance();
    private static final AssemblerEntity<TipoVehiculoDomain, TipoVehiculoEntity> tipoVehiculoAssembler = TipoVehiculoAssemblerEntity.getInstance();
    private static final AssemblerEntity<SedeDomain, SedeEntity> sedeAssembler = SedeAssemblerEntity.getInstance();

    private static final AssemblerEntity<TarifaDomain, TarifaEntity> instance = new TarifaAssemblerEntity();

    private TarifaAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<TarifaDomain, TarifaEntity> getInstance() {
        return instance;
    }

    @Override
    public TarifaEntity toEntity(TarifaDomain domain) {
        var tarifaDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TarifaDomain.build());
        var tipoTarifaEntity = tipoTarifaAssembler.toEntity(tarifaDomainTmp.getTipoTarifa());
        var estadoEntity = estadoAssembler.toEntity(tarifaDomainTmp.getEstado());
        var tipoVehiculoEntity = tipoVehiculoAssembler.toEntity(tarifaDomainTmp.getTipoVehiculo());
        var sedeEntity = sedeAssembler.toEntity(tarifaDomainTmp.getSede());
        return TarifaEntity.build().setId(tarifaDomainTmp.getId()).setSede(sedeEntity).setTipoVehiculo(tipoVehiculoEntity).
                setTipoTarifa(tipoTarifaEntity).setTarifa(tarifaDomainTmp.getTarifa()).setEstado(estadoEntity).
                setFechaInicioVigencia(tarifaDomainTmp.getFechaInicioVigencia()).setFechaFinVigencia(tarifaDomainTmp.getFechaFinVigencia());

    }

    @Override
    public List<TarifaEntity> toEntityCollection(List<TarifaDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<TarifaDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public TarifaDomain toDomain(TarifaEntity data) {
        var tarifaEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TarifaEntity.build());
        var tipoTarifaDomain = tipoTarifaAssembler.toDomain(tarifaEntityTmp.getTipoTarifa());
        var estadoDomain = estadoAssembler.toDomain(tarifaEntityTmp.getEstado());
        var tipoVehiculoDomain = tipoVehiculoAssembler.toDomain(tarifaEntityTmp.getTipoVehiculo());
        var sedeDomain = sedeAssembler.toDomain(tarifaEntityTmp.getSede());
        return TarifaDomain.build(tarifaEntityTmp.getId(), sedeDomain, tipoVehiculoDomain, tipoTarifaDomain, tarifaEntityTmp.getTarifa(),
                estadoDomain, tarifaEntityTmp.getFechaInicioVigencia(), tarifaEntityTmp.getFechaFinVigencia());

    }

    @Override
    public List<TarifaDomain> toDomainCollection(List<TarifaEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<TarifaEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
