package co.com.park.gp.data.dao.factory.concrete;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import co.com.park.gp.data.dao.entity.clientes.ClienteDAO;
import co.com.park.gp.data.dao.entity.comunes.TipoVehiculoDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.clientes.ClientePostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.comunes.TipoVehiculoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion.FacturacionPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion.MetodoPagoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.parqueaderos.*;
import co.com.park.gp.data.dao.entity.concrete.postgresql.planes.PlanPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.planes.TipoPlanPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.sesionparqueo.SesionParqueoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.tarifas.EstadoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.tarifas.TarifaPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.tarifas.TipoTarifaPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.vehiculos.VehiculoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.facturacion.FacturacionDAO;
import co.com.park.gp.data.dao.entity.facturacion.MetodoPagoDAO;
import co.com.park.gp.data.dao.entity.parqueaderos.*;
import co.com.park.gp.data.dao.entity.planes.PlanDAO;
import co.com.park.gp.data.dao.entity.planes.TipoPlanDAO;
import co.com.park.gp.data.dao.entity.sesionparqueo.SesionParqueoDAO;
import co.com.park.gp.data.dao.entity.tarifas.EstadoDAO;
import co.com.park.gp.data.dao.entity.tarifas.TarifaDAO;
import co.com.park.gp.data.dao.entity.tarifas.TipoTarifaDAO;
import co.com.park.gp.data.dao.entity.vehiculos.VehiculoDAO;
import co.com.park.gp.entity.planes.PlanEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.SQLHelper;
import co.com.park.gp.data.dao.entity.empleados.EmpleadoDAO;
import co.com.park.gp.data.dao.entity.empleados.TipoEmpleadoDAO;
import co.com.park.gp.data.dao.entity.comunes.TipoIdentificacionDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.concrete.postgresql.empleados.EmpleadoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.empleados.TipoEmpleadoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.comunes.TipoIdentificacionPostgresqlDAO;
import co.com.park.gp.data.dao.factory.DAOFactory;


@Component
public final class PostgresqlDAOFactory extends SqlConnection implements DAOFactory {

    @Value("${spring.datasource.url}")
    private String connectionUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public PostgresqlDAOFactory() {
        super();
        abrirConexion();
    }

    private void abrirConexion() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find database.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            var mensajeUsuario = "Error al cargar la configuración de la base de datos.";
            var mensajeTecnico = "No se pudo cargar el archivo database.properties";
            throw new DataGPException(mensajeTecnico, mensajeUsuario, ex);
        }

        final String host = properties.getProperty("database.host");
        final String port = properties.getProperty("database.port");
        final String dbName = properties.getProperty("database.name");
        final String user = properties.getProperty("database.user");
        final String password = properties.getProperty("database.password");

        final String connectionUrl = String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", host, port, dbName,
                user, password);

        try {
            setConexion(DriverManager.getConnection(connectionUrl));
        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00055);
            throw new DataGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00055);
            throw new DataGPException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }

    @Override
    public void cerrarConexion() {
        SQLHelper.close(getConexion());
    }

    @Override
    public void iniciarTransaccion() {
        SQLHelper.initTransaction(getConexion());
    }

    @Override
    public void confirmarTransaccion() {
        SQLHelper.commit(getConexion());
    }

    @Override
    public void cancelarTransaccion() {
        SQLHelper.rollback(getConexion());
    }

    @Override
    public PaisDAO getPaisDAO() {
        return new PaisPostgresqlDAO(getConexion());
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new DepartamentoPostgresqlDAO(getConexion());
    }

    @Override
    public CiudadDAO getCiudadDAO() {
        return new CiudadPostgresqlDAO(getConexion());
    }

    @Override
    public ParqueaderoDAO getParqueaderoDAO() {
        return new ParqueaderoPostgresqlDAO(getConexion());
    }

    @Override
    public SedeDAO getSedeDAO() {
        return new SedePostgresqlDAO(getConexion());
    }

    @Override
    public TipoSedeDAO getTipoSedeDAO() {
        return new TipoSedePostgresqlDAO(getConexion());
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        return new EmpleadoPostgresqlDAO(getConexion());
    }

    @Override
    public TipoEmpleadoDAO getTipoEmpleadoDAO() {
        return new TipoEmpleadoPostgresqlDAO(getConexion());
    }

    @Override
    public TipoIdentificacionDAO geTipoIdentificacionDAO() {
        return new TipoIdentificacionPostgresqlDAO(getConexion());
    }

    @Override
    public TipoVehiculoDAO getTipoVehiculoDAO() {
        return new TipoVehiculoPostgresqlDAO(getConexion());
    }

    @Override
    public TipoTarifaDAO getTipoTarifaDAO() {
        return new TipoTarifaPostgresqlDAO(getConexion());
    }

    @Override
    public EstadoDAO getEstadoDAO() {
        return new EstadoPostgresqlDAO(getConexion());
    }

    @Override
    public TarifaDAO getTarifaDAO() {
        return new TarifaPostgresqlDAO(getConexion());
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClientePostgresqlDAO(getConexion());
    }

    @Override
    public VehiculoDAO getVehiculoDAO() {
        return new VehiculoPostgresqlDAO(getConexion());
    }

    @Override
    public TipoPlanDAO getTipoPlanDAO() {
        return new TipoPlanPostgresqlDAO(getConexion());
    }

    @Override
    public PlanDAO getPlanDAO() {
        return new PlanPostgresqlDAO(getConexion(), this);
    }

    @Override
    public SesionParqueoDAO getSesionParqueoDAO() {
        return new SesionParqueoPostgresqlDAO(getConexion(), this);
    }

    @Override
    public CeldaDAO getCeldaDao() {
        return new CeldaPostgresqlDAO(getConexion());
    }

    @Override
    public FacturacionDAO getFacturacionDao() {
        return new FacturacionPostgresqlDAO(getConexion());
    }

    @Override
    public MetodoPagoDAO getMetodoPagoDao() {
        return new MetodoPagoPostgresqlDAO(getConexion());
    }


//    public static void main(String[] args) {
//        DAOFactory factory = DAOFactory.getFactory();
//        System.out.println("Iniciando transacción...");
//        factory.iniciarTransaccion();
//
//
//        System.out.println("Consultar planes");
//        var resultadosPlanes = factory.getPlanDAO().consultar(PlanEntity.build().setVehiculo(VehiculoEntity.build().setPlaca("UZM06F")));
//
//        for (PlanEntity planEntity : resultadosPlanes) {
//            System.out.println("idPlan : " + planEntity.getId() + ", Placa: " + planEntity.getVehiculo().getPlaca());
//        }

//        System.out.println("Consultar Tarifas");
//        var resultadosTarifa = factory.getTarifaDAO().consultar(TarifaEntity.build());
//
//        for (TarifaEntity tarifaEntity : resultadosTarifa) {
//            System.out.println("idTarifa : " + tarifaEntity.getId() + ", idSede: " + tarifaEntity.getSede().getId());
//        }
//
//
//		System.out.println("Consultar Sede");
//		var resultadosSede = factory.getSedeDAO().consultar(SedeEntity.build().setParqueadero(ParqueaderoEntity.build().setId(UUIDHelper.convertToUUID("e1a1e0a0-0000-0000-0000-000000000001"))));
//
//		for (SedeEntity sedeEntity : resultadosSede) {
//			System.out.println("idSede: " + sedeEntity.getId() + ", nombreSede: " + sedeEntity.getNombre() + ", direccionSede: " + sedeEntity.getDireccion()
//                    + ", correoElectronico: " + sedeEntity.getCorreoElectronico() + ", celdasCarro: " + sedeEntity.getCeldasCarro() + ", celdasMoto: " + sedeEntity.getCeldasMoto()
//                    + ", celdasCamion: " + sedeEntity.getCeldascamion() + ", idPais: " + sedeEntity.getPais().getId() + ", idDepartamento: " + sedeEntity.getDepartamento().getId()
//                    + ", idCiudad: " + sedeEntity.getCiudad().getId() + ", idParqueadero: " + sedeEntity.getParqueadero().getId() + ", idTipoSede: " + sedeEntity.getTipoSede().getId());
//		}

//
//        System.out.println("Confirmar transacción...");
//        factory.confirmarTransaccion();
//
//        System.out.println("Cerrando conexión...");
//        factory.cerrarConexion();
//
//    }


}
