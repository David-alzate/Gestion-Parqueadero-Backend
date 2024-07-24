package co.com.park.gp.business.facade.impl.sesionesparqueo;

import co.com.park.gp.business.assembler.dto.impl.sesionparqueo.SesionParqueoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo.IngresarVehiculo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;

public class IngresarVehiculoFacade implements FacadeWhitoutReturn<SesionParqueoDTO> {

    private final DAOFactory daoFactory;

    public IngresarVehiculoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(SesionParqueoDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new IngresarVehiculo(daoFactory);
            var sesionParqueoDomain = SesionParqueoAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(sesionParqueoDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de Ingresar el Vehiculo.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de de Ingresar el Vehiculo.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
