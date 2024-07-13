package co.com.park.gp.business.facade.impl.vehiculos.vehiculo;

import co.com.park.gp.business.assembler.dto.impl.vehiculos.VehiculoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.vehiculos.vehiculo.ModificarVehiculo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

public class ModificarVehiculoFacade implements FacadeWhitoutReturn<VehiculoDTO> {

    private final DAOFactory daoFactory;

    public ModificarVehiculoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(VehiculoDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarVehiculo(daoFactory);
            var vehiculoDomain = VehiculoAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(vehiculoDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información del Vehiculo.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar la información del Vehiculo.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
