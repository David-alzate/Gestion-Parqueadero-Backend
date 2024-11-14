package co.com.park.gp.crosscutting.enums;

import java.util.UUID;

import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;

public enum EstadoEnum {
    ACTIVO("Activa"),
    INACTIVO("Inactiva");

    private final String nombre;
    private UUID id;

    EstadoEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getId(DAOFactory factory) {
        synchronized (this) {
            if (id == null) {
                var estadoEntity = factory.getEstadoDAO().consultarPorDescripcion(this.getNombre());
                if (estadoEntity == null || estadoEntity.getId() == null) {
                    throw new BusinessGPException(
                        "Error al consultar el estado " + this.getNombre() + " en la base de datos",
                        "El estado no existe o no tiene ID asociado"
                    );
                }
                this.id = estadoEntity.getId();
            }
        }
        return id;
    }

}