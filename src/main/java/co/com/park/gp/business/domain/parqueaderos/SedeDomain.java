package co.com.park.gp.business.domain.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class SedeDomain {
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

    private void setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private void setParqueadero(final ParqueaderoDomain parqueadero) {
        this.parqueadero = ObjectHelper.getObjectHelper().getDefaultValue(parqueadero, ParqueaderoDomain.build());
    }

    private void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    private void setCiudad(final CiudadDomain ciudad) {
        this.ciudad = ObjectHelper.getObjectHelper().getDefaultValue(ciudad, CiudadDomain.build());
    }

    private void setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
    }

    private void setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
    }

    private void setCeldasCarro(final int celdasCarro) {
        this.celdasCarro = celdasCarro;
    }

    private void setCeldasMoto(final int celdasMoto) {
        this.celdasMoto = celdasMoto;
    }

    private void setCeldascamion(final int celdascamion) {
        this.celdascamion = celdascamion;
    }

    private void setTipoSede(final TipoSedeDomain tipoSede) {
        this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, TipoSedeDomain.build());
    }

    private void setPais(final PaisDomain pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, PaisDomain.build());
    }

    private void setDepartamento(final DepartamentoDomain departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, DepartamentoDomain.build());
    }

    public UUID getId() {
        return id;
    }

    public ParqueaderoDomain getParqueadero() {
        return parqueadero;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadDomain getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getCeldasCarro() {
        return celdasCarro;
    }

    public int getCeldasMoto() {
        return celdasMoto;
    }

    public int getCeldascamion() {
        return celdascamion;
    }

    public TipoSedeDomain getTipoSede() {
        return tipoSede;
    }

    public PaisDomain getPais() {
        return pais;
    }

    public DepartamentoDomain getDepartamento() {
        return departamento;
    }

}
