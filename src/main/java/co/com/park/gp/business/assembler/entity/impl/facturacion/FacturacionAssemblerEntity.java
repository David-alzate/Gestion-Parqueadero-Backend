package co.com.park.gp.business.assembler.entity.impl.facturacion;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.sesionparqueo.SesionParqueoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.TarifaAssemblerEntity;
import co.com.park.gp.business.domain.facturacion.FacturacionDomain;
import co.com.park.gp.business.domain.facturacion.MetodoPagoDomain;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.facturacion.FacturacionEntitiy;
import co.com.park.gp.entity.facturacion.MetodoPagoEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.util.ArrayList;
import java.util.List;

public class FacturacionAssemblerEntity implements AssemblerEntity<FacturacionDomain, FacturacionEntitiy> {

    private static final AssemblerEntity<FacturacionDomain, FacturacionEntitiy> INSTANCE = new FacturacionAssemblerEntity();

    private static final AssemblerEntity<SesionParqueoDomain, SesionParqueoEntity> sesionParqueoInstance = SesionParqueoAssemblerEntity.getInstance();

    private static final AssemblerEntity<MetodoPagoDomain, MetodoPagoEntity> metodoPagoInstance = MetodoPagoAssemblerEntity.getInstance();

    private static final AssemblerEntity<TarifaDomain, TarifaEntity> tarifaInstance = TarifaAssemblerEntity.getInstance();

    private FacturacionAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<FacturacionDomain, FacturacionEntitiy> getInstance() {
        return INSTANCE;
    }

    @Override
    public FacturacionEntitiy toEntity(FacturacionDomain domain) {
        var facturacionDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, FacturacionDomain.build());
        var sesionParqueoEntity = sesionParqueoInstance.toEntity(facturacionDomainTmp.getSesionParqueo());
        var tarifaEntity = tarifaInstance.toEntity(facturacionDomainTmp.getTarifa());
        var metodoPagoEntity = metodoPagoInstance.toEntity(facturacionDomainTmp.getMetodoPago());
        return FacturacionEntitiy.build().setId(facturacionDomainTmp.getId())
                .setSesionParqueo(sesionParqueoEntity)
                .setDuracion(facturacionDomainTmp.getDuracion())
                .setTarifa(tarifaEntity)
                .setValorPagar(facturacionDomainTmp.getValorPagar())
                .setMetodoPago(metodoPagoEntity);
    }

    @Override
    public List<FacturacionEntitiy> toEntityCollection(List<FacturacionDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<FacturacionDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public FacturacionDomain toDomain(FacturacionEntitiy data) {
        var facturacionEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, FacturacionEntitiy.build());
        var sesionParqueoDomain = sesionParqueoInstance.toDomain(facturacionEntityTmp.getSesionParqueo());
        var tarifaDomain = tarifaInstance.toDomain(facturacionEntityTmp.getTarifa());
        var metodoPagoDomain = metodoPagoInstance.toDomain(facturacionEntityTmp.getMetodoPago());
        return FacturacionDomain.build(facturacionEntityTmp.getId(), sesionParqueoDomain,
                facturacionEntityTmp.getDuracion(), tarifaDomain, facturacionEntityTmp.getValorPagar(), metodoPagoDomain);
    }

    @Override
    public List<FacturacionDomain> toDomainCollection(List<FacturacionEntitiy> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<FacturacionEntitiy>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
