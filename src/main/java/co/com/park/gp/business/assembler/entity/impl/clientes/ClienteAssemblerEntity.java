package co.com.park.gp.business.assembler.entity.impl.clientes;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.comunes.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

import java.util.ArrayList;
import java.util.List;

public class ClienteAssemblerEntity implements AssemblerEntity<ClienteDomain, ClienteEntity> {

    private static final AssemblerEntity<ClienteDomain, ClienteEntity> instance = new ClienteAssemblerEntity();

    private static final AssemblerEntity<TipoIdentificacionDomain, TipoIdentificacionEntity> tipoIdentificacionAssemblerEntity = TipoIdentificacionAssemblerEntity.getInstance();

    private ClienteAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<ClienteDomain, ClienteEntity> getInstance() {
        return instance;
    }

    @Override
    public ClienteEntity toEntity(ClienteDomain domain) {
        var clienteDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, ClienteDomain.build());
        var tipoIdentificacionEntity = tipoIdentificacionAssemblerEntity.toEntity(clienteDomainTmp.getTipoIdentificacion());
        return ClienteEntity.build().setId(clienteDomainTmp.getId())
                .setTipoIdentificacion(tipoIdentificacionEntity)
                .setNumeroIdentificacion(clienteDomainTmp.getNumeroIdentificacion())
                .setNombre(clienteDomainTmp.getNombre())
                .setApellido(clienteDomainTmp.getApellido())
                .setCorreoElectronico(clienteDomainTmp.getCorreoElectronico());
    }

    @Override
    public List<ClienteEntity> toEntityCollection(List<ClienteDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<ClienteDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public ClienteDomain toDomain(ClienteEntity data) {
        var clienteEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, ClienteEntity.build());
        var tipoIdentificacionDomain = tipoIdentificacionAssemblerEntity.toDomain(clienteEntityTmp.getTipoIdentificacion());
        return ClienteDomain.build(clienteEntityTmp.getId(), tipoIdentificacionDomain, clienteEntityTmp.getNumeroIdentificacion(),
                clienteEntityTmp.getNombre(), clienteEntityTmp.getApellido(), clienteEntityTmp.getCorreoElectronico());
    }

    @Override
    public List<ClienteDomain> toDomainCollection(List<ClienteEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<ClienteEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

}
