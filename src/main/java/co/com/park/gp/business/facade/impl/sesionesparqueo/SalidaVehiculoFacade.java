package co.com.park.gp.business.facade.impl.sesionesparqueo;

import co.com.park.gp.business.assembler.dto.impl.sesionparqueo.SesionParqueoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo.SalidaVehiculo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;

public class SalidaVehiculoFacade implements FacadeWhitoutReturn<SesionParqueoDTO> {

    private final DAOFactory daoFactory;

    public SalidaVehiculoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(SesionParqueoDTO dto) {
            daoFactory.iniciarTransaccion();

            try {
                var useCase = new SalidaVehiculo(daoFactory);
                var sesionParqueoDomain = SesionParqueoAssemblerDTO.getInstance().toDomain(dto);

                useCase.execute(sesionParqueoDomain);

                daoFactory.confirmarTransaccion();
            } catch (GPException excepcion) {
                daoFactory.cancelarTransaccion();
                throw excepcion;
            } catch (Exception excepcion) {
                daoFactory.cancelarTransaccion();

                var mensajeUsuario = "Se ha presentado un problema tratando de Realizar la salida del Vehiculo.";
                var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de la salida del Vehiculo.";

                throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
            } finally {
                daoFactory.cerrarConexion();
            }
        }

    }

