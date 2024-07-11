package co.com.park.gp.business.assembler.dto.impl.clientes;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.comunes.TipoIdentificacionAssemblerDTO;
import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.clientes.ClienteDTO;
import co.com.park.gp.dto.comunes.TipoIdentificacionDTO;

import java.util.ArrayList;
import java.util.List;

public class ClienteAssemblerDTO implements AssemblerDTO<ClienteDomain, ClienteDTO> {

    private static final AssemblerDTO<ClienteDomain, ClienteDTO> instance = new ClienteAssemblerDTO();

    private static final AssemblerDTO<TipoIdentificacionDomain, TipoIdentificacionDTO> tipoIdentificacionAssemblerDTO = TipoIdentificacionAssemblerDTO.getInstance();

    private ClienteAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<ClienteDomain, ClienteDTO> getInstance() {
        return instance;
    }

    @Override
    public ClienteDTO toDto(ClienteDomain domain) {
        var clienteDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, ClienteDomain.build());
        var tipoIdentificacionDTO = tipoIdentificacionAssemblerDTO.toDto(clienteDomainTmp.getTipoIdentificacion());
        return ClienteDTO.build().setId(clienteDomainTmp.getId())
                .setTipoIdentificacion(tipoIdentificacionDTO)
                .setNumeroIdentificacion(clienteDomainTmp.getNumeroIdentificacion())
                .setNombre(clienteDomainTmp.getNombre())
                .setApellido(clienteDomainTmp.getApellido())
                .setCorreoElectronico(clienteDomainTmp.getCorreoElectronico());
    }

    @Override
    public List<ClienteDTO> toDTOCollection(List<ClienteDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<ClienteDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public ClienteDomain toDomain(ClienteDTO data) {
        var clienteDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, ClienteDTO.build());
        var tipoIdentificacionDomain = tipoIdentificacionAssemblerDTO.toDomain(clienteDtoTmp.getTipoIdentificacion());
        return ClienteDomain.build(clienteDtoTmp.getId(), tipoIdentificacionDomain, clienteDtoTmp.getNumeroIdentificacion(),
                clienteDtoTmp.getNombre(), clienteDtoTmp.getApellido(), clienteDtoTmp.getCorreoElectronico());
    }

    @Override
    public List<ClienteDomain> toDomainCollection(List<ClienteDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<ClienteDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

}
