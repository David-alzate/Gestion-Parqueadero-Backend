package co.com.park.gp.business.assembler.dto.impl.facturacion;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.sesionparqueo.SesionParqueoAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.tarifas.TarifaAssemblerDTO;
import co.com.park.gp.business.domain.facturacion.FacturacionDomain;
import co.com.park.gp.business.domain.facturacion.MetodoPagoDomain;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.facturacion.FacturacionDTO;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;
import co.com.park.gp.dto.tarifas.TarifaDTO;

import java.util.ArrayList;
import java.util.List;

public class FacturacionAssemblerDTO implements AssemblerDTO<FacturacionDomain, FacturacionDTO> {

    private static final AssemblerDTO<FacturacionDomain, FacturacionDTO> INSTANCE = new FacturacionAssemblerDTO();

    private static final AssemblerDTO<MetodoPagoDomain, MetodoPagoDTO> metodoPagoInstance = MetodoPagoAssemblerDTO.getInstance();

    private static final AssemblerDTO<SesionParqueoDomain, SesionParqueoDTO> sesionParqueoInstance = SesionParqueoAssemblerDTO.getInstance();

    private static final AssemblerDTO<TarifaDomain, TarifaDTO> tarifaInstance = TarifaAssemblerDTO.getInstance();

    private FacturacionAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<FacturacionDomain, FacturacionDTO> getInstance() {
        return INSTANCE;
    }

    @Override
    public FacturacionDTO toDto(FacturacionDomain domain) {
        var facturacionDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, FacturacionDomain.build());
        var sesionParqueoDto = sesionParqueoInstance.toDto(facturacionDomainTmp.getSesionParqueo());
        var metodoPagoDto = metodoPagoInstance.toDto(facturacionDomainTmp.getMetodoPago());
        var tarifaDto = tarifaInstance.toDto(facturacionDomainTmp.getTarifa());
        return FacturacionDTO.build().setId(facturacionDomainTmp.getId())
                .setSesionParqueo(sesionParqueoDto)
                .setDuracion(facturacionDomainTmp.getDuracion())
                .setTarifa(tarifaDto)
                .setValorPagar(facturacionDomainTmp.getValorPagar())
                .setMetodoPago(metodoPagoDto);
    }

    @Override
    public List<FacturacionDTO> toDTOCollection(List<FacturacionDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<FacturacionDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public FacturacionDomain toDomain(FacturacionDTO data) {
        var facturacionDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, FacturacionDTO.build());
        var sesionParqueoDomain = sesionParqueoInstance.toDomain(facturacionDtoTmp.getSesionParqueo());
        var metodoPagoDomain = metodoPagoInstance.toDomain(facturacionDtoTmp.getMetodoPago());
        var tarifaDomain = tarifaInstance.toDomain(facturacionDtoTmp.getTarifa());
        return FacturacionDomain.build(facturacionDtoTmp.getId(),
                sesionParqueoDomain, facturacionDtoTmp.getDuracion(),
                tarifaDomain, facturacionDtoTmp.getValorPagar(), metodoPagoDomain);
    }

    @Override
    public List<FacturacionDomain> toDomainCollection(List<FacturacionDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<FacturacionDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }
}
