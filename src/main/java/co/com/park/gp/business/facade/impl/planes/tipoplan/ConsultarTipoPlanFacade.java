package co.com.park.gp.business.facade.impl.planes.tipoplan;

import co.com.park.gp.business.assembler.dto.impl.planes.TipoPlanAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.planes.tipoplan.ConsultarTipoPlan;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.planes.TipoPlanDTO;

import java.util.List;

public class ConsultarTipoPlanFacade implements FacadeWhitReturn<TipoPlanDTO, List<TipoPlanDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarTipoPlanFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<TipoPlanDTO> execute(TipoPlanDTO dto) {
        try {
            var useCase = new ConsultarTipoPlan(daoFactory);
            var tipoPlanDomain = TipoPlanAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(tipoPlanDomain);
            return TipoPlanAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los tipos de plan.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los tipos de plan.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
