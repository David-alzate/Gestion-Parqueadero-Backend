package co.com.park.gp.business.facade.impl.tarifas.tipotarifa;

import co.com.park.gp.business.assembler.dto.impl.tarifas.TipoTarifaAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.tarifas.tipotarifa.ConsultarTipoTarifa;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;

import java.util.List;

public class ConsultarTipoTarifaFacade implements FacadeWhitReturn<TipoTarifaDTO, List<TipoTarifaDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarTipoTarifaFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<TipoTarifaDTO> execute(TipoTarifaDTO dto) {
        try {
            var useCase = new ConsultarTipoTarifa(daoFactory);
            var tipoTarifaDomain = TipoTarifaAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(tipoTarifaDomain);
            return TipoTarifaAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los tipos de tarifa.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los tipos de tarifa.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
