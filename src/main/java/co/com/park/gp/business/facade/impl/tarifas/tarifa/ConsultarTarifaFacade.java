package co.com.park.gp.business.facade.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.dto.impl.tarifas.TarifaAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.tarifas.tarifa.ConsultarTarifas;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.tarifas.TarifaDTO;

import java.util.List;

public class ConsultarTarifaFacade implements FacadeWhitReturn<TarifaDTO, List<TarifaDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarTarifaFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<TarifaDTO> execute(TarifaDTO dto) {
        try {
            var useCase = new ConsultarTarifas(daoFactory);
            var tarifaDomain = TarifaAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(tarifaDomain);

            return TarifaAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de las tarifas";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar las tarifas";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
