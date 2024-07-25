package co.com.park.gp.business.facade.impl.sesionesparqueo;

import co.com.park.gp.business.assembler.dto.impl.sesionparqueo.SesionParqueoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo.ConsultarSesionParqueo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;

import java.util.List;

public class ConsultarSesionParqueoFacade implements FacadeWhitReturn<SesionParqueoDTO, List<SesionParqueoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarSesionParqueoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<SesionParqueoDTO> execute(SesionParqueoDTO dto) {
        try {
            var useCase = new ConsultarSesionParqueo(daoFactory);
            var sesionParqueoDomain = SesionParqueoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(sesionParqueoDomain);

            return SesionParqueoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de las sesiones de parqueo";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select en la tabla \"sesionParqueo\" de la base de datos.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
