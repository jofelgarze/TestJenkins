/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofelgarze.testentites;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josue
 */
@Entity
@Table(name = "persona", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre", "apellido"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Persona.Query.FIND_ALL, query = "SELECT p FROM Persona p")
    , @NamedQuery(name = Persona.Query.FIND_CODIGO, query = "SELECT p FROM Persona p WHERE p.codigo = :codigo")
    , @NamedQuery(name = Persona.Query.FIND_NOMBRE, query = "SELECT p FROM Persona p WHERE p.nombre = :nombre")
    , @NamedQuery(name = Persona.Query.FIND_APELLIDO, query = "SELECT p FROM Persona p WHERE p.apellido = :apellido")
    , @NamedQuery(name = Persona.Query.FIND_FECHANAC, query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "direcciones")
public class Persona implements Serializable {

     public static class Query {
        public static final String FIND_ALL = "Persona.findAll";
        public static final String FIND_CODIGO = "Persona.findByCodigo";
        public static final String FIND_NOMBRE = "Persona.findByNombre";
        public static final String FIND_APELLIDO = "Persona.findByApellido";
        public static final String FIND_FECHANAC = "Persona.findByFechaNacimiento";
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;    
    @OneToMany(mappedBy = "personaId", fetch = FetchType.EAGER
        ,cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Direccion> direcciones;

    public Persona() {
    }

    public Persona(Integer codigo) {
        this.codigo = codigo;
    }

    public Persona(Integer codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    //@XmlTransient
    public Collection<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Collection<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jofelgarze.testentites.Persona[ codigo=" + codigo + " ]";
    }
    
}
