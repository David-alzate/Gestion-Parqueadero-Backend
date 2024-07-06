package co.com.park.gp.business.facade.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.dto.impl.tarifas.TarifaAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.tarifas.tarifa.ModificarTarifa;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.tarifas.TarifaDTO;

public class ModificarTarifaFacade implements FacadeWhitoutReturn<TarifaDTO> {

    private final DAOFactory daoFactory;

    public ModificarTarifaFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(TarifaDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarTarifa(daoFactory);
            var tarifaDomain = TarifaAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(tarifaDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información de la tarifa.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar la información de la tarifa.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }

    }
}
