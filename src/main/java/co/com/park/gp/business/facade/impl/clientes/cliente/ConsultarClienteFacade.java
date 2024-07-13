package co.com.park.gp.business.facade.impl.clientes.cliente;

import co.com.park.gp.business.assembler.dto.impl.clientes.ClienteAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.clientes.cliente.ConsultarCliente;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.clientes.ClienteDTO;

import java.util.List;

public class ConsultarClienteFacade implements FacadeWhitReturn<ClienteDTO, List<ClienteDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarClienteFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<ClienteDTO> execute(ClienteDTO dto) {
        try {
            var useCase = new ConsultarCliente(daoFactory);
            var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(clienteDomain);

            return ClienteAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los clientes";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert del cliente en la tabla \"cliente\" de la base de datos.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
