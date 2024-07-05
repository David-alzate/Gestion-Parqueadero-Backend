package co.com.park.gp.business.facade.impl.tarifas.estado;

import co.com.park.gp.business.assembler.dto.impl.tarifas.EstadoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.tarifas.estado.ConsultarEstado;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.tarifas.EstadoDTO;

import java.util.List;

public class ConsultarEstadoFacade implements FacadeWhitReturn<EstadoDTO, List<EstadoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarEstadoFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<EstadoDTO> execute(EstadoDTO dto) {
        try {
            var useCase = new ConsultarEstado(daoFactory);
            var estadoDomain = EstadoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(estadoDomain);
            return EstadoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los estados.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los estados.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
