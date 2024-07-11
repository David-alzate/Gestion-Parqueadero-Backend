package co.com.park.gp.business.usecase.impl.clientes.cliente;

import co.com.park.gp.business.assembler.entity.impl.clientes.ClienteAssemblerEntity;
import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarCliente implements UseCaseWithReturn<ClienteDomain, List<ClienteDomain>> {

    private final DAOFactory factory;

    public ConsultarCliente(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de los clientes...";
            var mensajeTecnico = "El dao factory para consultar el cliente llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<ClienteDomain> execute(ClienteDomain data) {
        var clienteEntityFilter = ClienteAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getClienteDAO().consultar(clienteEntityFilter);

        return ClienteAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
