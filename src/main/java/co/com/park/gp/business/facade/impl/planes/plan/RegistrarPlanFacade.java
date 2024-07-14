package co.com.park.gp.business.facade.impl.planes.plan;

import co.com.park.gp.business.assembler.dto.impl.planes.PlanAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.planes.plan.RegistrarPlan;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.planes.PlanDTO;

public class RegistrarPlanFacade implements FacadeWhitoutReturn<PlanDTO> {

    private final DAOFactory daoFactory;

    public RegistrarPlanFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(PlanDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new RegistrarPlan(daoFactory);
            var planDomain = PlanAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(planDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de Registrar la información del Plan.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Registrar la información del Plan.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
