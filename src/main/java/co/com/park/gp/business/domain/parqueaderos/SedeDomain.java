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
    private TipoSedeDomain tipoSede;
    private PaisDomain pais;
    private CeldaDomain celda;
    private DepartamentoDomain departamento;

    public SedeDomain() {
        super();
    }

    public SedeDomain(final UUID id, final ParqueaderoDomain parqueadero, final String nombre,
                      final CiudadDomain ciudad, final String direccion, final String correoElectronico, final TipoSedeDomain tipoSede, final PaisDomain pais,
                      final CeldaDomain celda, final DepartamentoDomain departamento) {
        setId(id);
        setParqueadero(parqueadero);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
        setCorreoElectronico(correoElectronico);
        setTipoSede(tipoSede);
        setPais(pais);
        setCelda(celda);
        setDepartamento(departamento);
    }

    public static SedeDomain build(final UUID id, final ParqueaderoDomain parqueadero, final String nombre,
                                   final CiudadDomain ciudad, final String direccion, final String correoElectronico, final TipoSedeDomain tipoSede, final PaisDomain pais,
                                   final CeldaDomain celda, final DepartamentoDomain departamento) {
        return new SedeDomain(id, parqueadero, nombre, ciudad, direccion, correoElectronico,tipoSede, pais, celda, departamento);
    }

    public static SedeDomain build(final UUID id) {
        return new SedeDomain(id, ParqueaderoDomain.build(), TextHelper.EMPTY, CiudadDomain.build(), TextHelper.EMPTY,
                TextHelper.EMPTY, TipoSedeDomain.build(), PaisDomain.build(), CeldaDomain.build(), DepartamentoDomain.build());
    }

    public static SedeDomain build() {
        return new SedeDomain(UUIDHelper.getDefault(), ParqueaderoDomain.build(), TextHelper.EMPTY, CiudadDomain.build(), TextHelper.EMPTY,
                TextHelper.EMPTY, TipoSedeDomain.build(), PaisDomain.build(), CeldaDomain.build(), DepartamentoDomain.build());
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

    private void setTipoSede(final TipoSedeDomain tipoSede) {
        this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, TipoSedeDomain.build());
    }

    private void setPais(final PaisDomain pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, PaisDomain.build());
    }

    private void setDepartamento(final DepartamentoDomain departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, DepartamentoDomain.build());
    }

    public CeldaDomain getCelda() {
        return celda;
    }

    public void setCelda(CeldaDomain celda) {
        this.celda = ObjectHelper.getObjectHelper().getDefaultValue(celda, CeldaDomain.build());
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
