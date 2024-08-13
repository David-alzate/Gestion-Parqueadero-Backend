package co.com.park.gp.business.facade.impl.empleados.empleado;

import co.com.park.gp.business.assembler.dto.impl.empleados.EmpleadoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.empleados.empleado.RegistrarEmpleado;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.empleados.EmpleadoDTO;

public class RegistrarEmpleadoFacade implements FacadeWhitoutReturn<EmpleadoDTO> {

    private final DAOFactory daoFactory;

    public RegistrarEmpleadoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(EmpleadoDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new RegistrarEmpleado(daoFactory);
            var empleadoDomain = EmpleadoAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(empleadoDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00087);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00089);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

}
