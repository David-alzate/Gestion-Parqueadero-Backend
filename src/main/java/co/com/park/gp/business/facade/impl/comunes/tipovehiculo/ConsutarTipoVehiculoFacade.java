package co.com.park.gp.business.facade.impl.comunes.tipovehiculo;

import co.com.park.gp.business.assembler.dto.impl.comunes.TipoVehiculoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.comunes.tipovehiculo.ConsultarTipoVehiculo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;

import java.util.List;

public class ConsutarTipoVehiculoFacade implements FacadeWhitReturn<TipoVehiculoDTO, List<TipoVehiculoDTO>> {

    private final DAOFactory daoFactory;

    public ConsutarTipoVehiculoFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<TipoVehiculoDTO> execute(TipoVehiculoDTO dto) {
        try {
            var useCase = new ConsultarTipoVehiculo(daoFactory);
            var tipoVehiculoDomain = TipoVehiculoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(tipoVehiculoDomain);
            return TipoVehiculoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los tipos de vehiculo.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los tipos de vehiculo.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
