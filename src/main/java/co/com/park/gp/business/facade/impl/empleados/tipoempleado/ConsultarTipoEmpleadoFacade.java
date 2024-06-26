package co.com.park.gp.business.facade.impl.empleados.tipoempleado;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.empleados.TipoEmpleadoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.empleados.tipoempleado.ConsultarTipoEmpleado;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.empleados.TipoEmpleadoDTO;

public class ConsultarTipoEmpleadoFacade implements FacadeWhitReturn<TipoEmpleadoDTO, List<TipoEmpleadoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarTipoEmpleadoFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<TipoEmpleadoDTO> execute(TipoEmpleadoDTO dto) {
        try {
            var useCase = new ConsultarTipoEmpleado(daoFactory);
            var tipoEmpleadoDomain = TipoEmpleadoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(tipoEmpleadoDomain);
            return TipoEmpleadoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los tipos de empleado.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los tipos de empleado.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

}
