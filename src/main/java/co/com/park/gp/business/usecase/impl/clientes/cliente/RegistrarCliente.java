package co.com.park.gp.business.usecase.impl.clientes.cliente;

import co.com.park.gp.business.assembler.entity.impl.comunes.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.clientes.ClienteEntity;

import java.util.UUID;

public class RegistrarCliente implements UseCaseWithoutReturn<ClienteDomain> {

    private final DAOFactory factory;

    public RegistrarCliente(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo el Registro de los clientes...";
            var mensajeTecnico = "El dao factory para Registrar el cliente llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(ClienteDomain data) {

        var clienteEntity = ClienteEntity.build().setId(generarIdentificadorCliente())
                .setTipoIdentificacion(
                        TipoIdentificacionAssemblerEntity.getInstance().toEntity(data.getTipoIdentificacion()))
                .setNumeroIdentificacion(data.getNumeroIdentificacion()).setNombre(data.getNombre())
                .setApellido(data.getApellido()).setCorreoElectronico(data.getCorreoElectronico());

        factory.getClienteDAO().crear(clienteEntity);
    }

    private UUID generarIdentificadorCliente() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var clienteEntity = ClienteEntity.build().setId(id);
            var resultados = factory.getClienteDAO().consultar(clienteEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }
}
