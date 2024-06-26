package co.com.park.gp.business.domain.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public class SedeDomain {
    private UUID id;
    private ParqueaderoDomain parqueadero;
    private String nombre;
    private CiudadDomain ciudad;
    private String direccion;
    private String correoElectronico;
    private int celdasCarro;
    private int celdasMoto;
    private int celdascamion;
    private TipoSedeDomain tipoSede;
    private PaisDomain pais;
    private DepartamentoDomain departamento;

    public SedeDomain() {
        super();
    }

    public SedeDomain(final UUID id, final ParqueaderoDomain parqueadero, final String nombre,
                      final CiudadDomain ciudad, final String direccion, final String correoElectronico, final int celdasCarro,
                      final int celdasMoto, final int celdascamion, final TipoSedeDomain tipoSede, final PaisDomain pais,
                      final DepartamentoDomain departamento) {
        setId(id);
        setParqueadero(parqueadero);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
        setCorreoElectronico(correoElectronico);
        setCeldasCarro(celdasCarro);
        setCeldasMoto(celdasMoto);
        setCeldascamion(celdascamion);
        setTipoSede(tipoSede);
        setPais(pais);
        setDepartamento(departamento);
    }

    public static SedeDomain build(final UUID id, final ParqueaderoDomain parqueadero, final String nombre,
                                   final CiudadDomain ciudad, final String direccion, final String correoElectronico, final int celdasCarro,
                                   final int celdasMoto, final int celdascamion, final TipoSedeDomain tipoSede, final PaisDomain pais,
                                   final DepartamentoDomain departamento) {
        return new SedeDomain(id, parqueadero, nombre, ciudad, direccion, correoElectronico, celdasCarro, celdasMoto,
                celdascamion, tipoSede, pais, departamento);
    }

    public static SedeDomain build(final UUID id) {
        return new SedeDomain(id, ParqueaderoDomain.build(), TextHelper.EMPTY, CiudadDomain.build(), TextHelper.EMPTY,
                TextHelper.EMPTY, 0, 0, 0, TipoSedeDomain.build(), PaisDomain.build(), DepartamentoDomain.build());
    }

    public static SedeDomain build() {
        return new SedeDomain(UUIDHelper.getDefault(), ParqueaderoDomain.build(), TextHelper.EMPTY, CiudadDomain.build(), TextHelper.EMPTY,
                TextHelper.EMPTY, 0, 0, 0, TipoSedeDomain.build(), PaisDomain.build(), DepartamentoDomain.build());
    }

    private final void setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private final void setParqueadero(final ParqueaderoDomain parqueadero) {
        this.parqueadero = ObjectHelper.getObjectHelper().getDefaultValue(parqueadero, ParqueaderoDomain.build());
    }

    private final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    private final void setCiudad(final CiudadDomain ciudad) {
        this.ciudad = ObjectHelper.getObjectHelper().getDefaultValue(ciudad, CiudadDomain.build());
    }

    private final void setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
    }

    private final void setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
    }

    private final void setCeldasCarro(final int celdasCarro) {
        this.celdasCarro = celdasCarro;
    }

    private final void setCeldasMoto(final int celdasMoto) {
        this.celdasMoto = celdasMoto;
    }

    private final void setCeldascamion(final int celdascamion) {
        this.celdascamion = celdascamion;
    }

    private final void setTipoSede(final TipoSedeDomain tipoSede) {
        this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, TipoSedeDomain.build());
    }

    private final void setPais(final PaisDomain pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, PaisDomain.build());
    }

    private final void setDepartamento(final DepartamentoDomain departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, DepartamentoDomain.build());
    }

    public final UUID getId() {
        return id;
    }

    public final ParqueaderoDomain getParqueadero() {
        return parqueadero;
    }

    public final String getNombre() {
        return nombre;
    }

    public final CiudadDomain getCiudad() {
        return ciudad;
    }

    public final String getDireccion() {
        return direccion;
    }

    public final String getCorreoElectronico() {
        return correoElectronico;
    }

    public final int getCeldasCarro() {
        return celdasCarro;
    }

    public final int getCeldasMoto() {
        return celdasMoto;
    }

    public final int getCeldascamion() {
        return celdascamion;
    }

    public final TipoSedeDomain getTipoSede() {
        return tipoSede;
    }

    public final PaisDomain getPais() {
        return pais;
    }

    public final DepartamentoDomain getDepartamento() {
        return departamento;
    }

}
