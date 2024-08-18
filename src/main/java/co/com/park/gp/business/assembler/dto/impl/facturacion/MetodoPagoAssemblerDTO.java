package co.com.park.gp.business.assembler.dto.impl.facturacion;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.facturacion.MetodoPagoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;

import java.util.ArrayList;
import java.util.List;

public class MetodoPagoAssemblerDTO implements AssemblerDTO<MetodoPagoDomain, MetodoPagoDTO> {

    private static final AssemblerDTO<MetodoPagoDomain, MetodoPagoDTO> instance = new MetodoPagoAssemblerDTO();

    private MetodoPagoAssemblerDTO(){
        super();
    }

    public static AssemblerDTO<MetodoPagoDomain, MetodoPagoDTO> getInstance(){
        return instance;
    }

    @Override
    public MetodoPagoDTO toDto(MetodoPagoDomain domain) {
        var metodoPagoTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, MetodoPagoDomain.build());
        return MetodoPagoDTO.build().setId(metodoPagoTmp.getId()).setNombre(metodoPagoTmp.getNombre());
    }

    @Override
    public List<MetodoPagoDTO> toDTOCollection(List<MetodoPagoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<MetodoPagoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public MetodoPagoDomain toDomain(MetodoPagoDTO data) {
        var metodoPagoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, MetodoPagoDTO.build());
        return MetodoPagoDomain.build(metodoPagoDtoTmp.getId(), metodoPagoDtoTmp.getNombre());
    }

    @Override
    public List<MetodoPagoDomain> toDomainCollection(List<MetodoPagoDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<MetodoPagoDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }
}
