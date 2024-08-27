package co.com.park.gp.entity.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class SedeEntity {

    private UUID id;
    private ParqueaderoEntity parqueadero;
    private String nombre;
    private CiudadEntity ciudad;
    private String direccion;
    private String correoElectronico;
    private TipoSedeEntity tipoSede;
    private PaisEntity pais;
    private DepartamentoEntity departamento;

    public SedeEntity() {
        setId(UUIDHelper.getDefault());
        setParqueadero(ParqueaderoEntity.build());
        setNombre(TextHelper.EMPTY);
        setCiudad(CiudadEntity.build());
        setDireccion(TextHelper.EMPTY);
        setCorreoElectronico(TextHelper.EMPTY);
        setTipoSede(TipoSedeEntity.build());
        setPais(PaisEntity.build());
        setDepartamento(DepartamentoEntity.build());
    }

    public SedeEntity(final UUID id, final ParqueaderoEntity parqueadero, final String nombre,
                      final CiudadEntity ciudad, final String direccion, final String correoElectronico, final TipoSedeEntity tipoSede, final PaisEntity pais,
                      final DepartamentoEntity departamento) {
        setId(id);
        setParqueadero(parqueadero);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
        setCorreoElectronico(correoElectronico);
        setTipoSede(tipoSede);
        setPais(pais);
        setDepartamento(departamento);
    }

    public static SedeEntity build() {
        return new SedeEntity();
    }

    public SedeEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public SedeEntity setParqueadero(final ParqueaderoEntity parqueadero) {
        this.parqueadero = ObjectHelper.getObjectHelper().getDefaultValue(parqueadero, new ParqueaderoEntity());
        return this;
    }

    public SedeEntity setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public SedeEntity setCiudad(final CiudadEntity ciudad) {
        this.ciudad = ObjectHelper.getObjectHelper().getDefaultValue(ciudad, new CiudadEntity());
        return this;
    }

    public SedeEntity setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }

    public SedeEntity setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }
    public SedeEntity setTipoSede(final TipoSedeEntity tipoSede) {
        this.tipoSede = ObjectHelper.getObjectHelper().getDefaultValue(tipoSede, new TipoSedeEntity());
        return this;
    }

    public SedeEntity setPais(final PaisEntity pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, new PaisEntity());
        return this;
    }

    public SedeEntity setDepartamento(final DepartamentoEntity departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoEntity());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ParqueaderoEntity getParqueadero() {
        return parqueadero;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public TipoSedeEntity getTipoSede() {
        return tipoSede;
    }

    public PaisEntity getPais() {
        return pais;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

}
