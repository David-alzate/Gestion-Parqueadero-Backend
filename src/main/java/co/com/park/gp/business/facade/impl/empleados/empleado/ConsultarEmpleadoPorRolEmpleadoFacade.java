package co.com.park.gp.business.facade.impl.empleados.empleado;

import co.com.park.gp.business.assembler.dto.impl.empleados.EmpleadoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.empleados.empleado.ConsultarEmpleadoPorRolEmpleado;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.empleados.EmpleadoDTO;

import java.util.List;

public class ConsultarEmpleadoPorRolEmpleadoFacade implements FacadeWhitReturn<EmpleadoDTO, List<EmpleadoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarEmpleadoPorRolEmpleadoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<EmpleadoDTO> execute(EmpleadoDTO dto) {
        try {
            var useCase = new ConsultarEmpleadoPorRolEmpleado(daoFactory);
            var empleadoDomain = EmpleadoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(empleadoDomain);

            return EmpleadoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los empleados por rol empleado";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select en la tabla \"empleado\" de la base de datos.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
