package co.com.park.gp.business.facade.impl.planes.plan;

import co.com.park.gp.business.assembler.dto.impl.planes.PlanAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.planes.plan.ConsultarPlan;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.planes.PlanDTO;

import java.util.List;

public class ConsultarPlanFacade implements FacadeWhitReturn<PlanDTO, List<PlanDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarPlanFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<PlanDTO> execute(PlanDTO dto) {
        try {
            var useCase = new ConsultarPlan(daoFactory);
            var planDomain = PlanAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(planDomain);

            return PlanAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los planes";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert del plan en la tabla \"plan\" de la base de datos.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
