package co.com.park.gp.business.facade.impl.empleados.empleado;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.empleados.EmpleadoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.empleados.empleado.ConsultarEmpleado;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.empleados.EmpleadoDTO;

public class ConsultarEmpleadoFacade implements FacadeWhitReturn<EmpleadoDTO, List<EmpleadoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarEmpleadoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<EmpleadoDTO> execute(EmpleadoDTO dto) {

        try {

            var useCase = new ConsultarEmpleado(daoFactory);
            var empleadoDomain = EmpleadoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(empleadoDomain);

            return EmpleadoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00089);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
