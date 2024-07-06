package co.com.park.gp.business.facade.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.dto.impl.tarifas.TarifaAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.tarifas.tarifa.RegistrarTarifa;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.tarifas.TarifaDTO;

public class RegistrarTarifaFacade implements FacadeWhitoutReturn<TarifaDTO> {

    private final DAOFactory daoFactory;

    public RegistrarTarifaFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(TarifaDTO dto) {
        try {
            daoFactory.iniciarTransaccion();

            var useCase = new RegistrarTarifa(daoFactory);
            var tarifaDomain = TarifaAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(tarifaDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información de las tarifas.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de registrar la información de las tarifas.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
