package co.com.park.gp.business.facade.impl.clientes.cliente;

import co.com.park.gp.business.assembler.dto.impl.clientes.ClienteAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.clientes.cliente.RegistrarCliente;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.clientes.ClienteDTO;

public class RegistrarClienteFacade implements FacadeWhitoutReturn<ClienteDTO> {

    private final DAOFactory daoFactory;

    public RegistrarClienteFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(ClienteDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new RegistrarCliente(daoFactory);
            var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(clienteDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de Registrar la información del Cliente.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Registrar la información del Cliente.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
