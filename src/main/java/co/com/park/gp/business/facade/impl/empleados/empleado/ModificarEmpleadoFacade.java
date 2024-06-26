package co.com.park.gp.business.facade.impl.empleados.empleado;

import co.com.park.gp.business.assembler.dto.impl.empleados.EmpleadoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.empleados.empleado.ModificarEmpleado;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.empleados.EmpleadoDTO;

public class ModificarEmpleadoFacade implements FacadeWhitoutReturn<EmpleadoDTO> {

    private final DAOFactory daoFactory;

    public ModificarEmpleadoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(EmpleadoDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarEmpleado(daoFactory);
            var empleadoDomain = EmpleadoAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(empleadoDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información del empleado.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar la información del empleado.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
