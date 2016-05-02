/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pro
 */
@Entity
@Table(name = "metroline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metroline.findAll", query = "SELECT m FROM Metroline m"),
    @NamedQuery(name = "Metroline.findById", query = "SELECT m FROM Metroline m WHERE m.id = :id"),
    @NamedQuery(name = "Metroline.findByName", query = "SELECT m FROM Metroline m WHERE m.name = :name"),
    @NamedQuery(name = "Metroline.findByStationsNO", query = "SELECT m FROM Metroline m WHERE m.stationsNO = :stationsNO")})
public class Metroline implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StationsNO")
    private int stationsNO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metroLineID")
    private Collection<Metrostation> metrostationCollection;

    public Metroline() {
    }

    public Metroline(Integer id) {
        this.id = id;
    }

    public Metroline(Integer id, String name, int stationsNO) {
        this.id = id;
        this.name = name;
        this.stationsNO = stationsNO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStationsNO() {
        return stationsNO;
    }

    public void setStationsNO(int stationsNO) {
        this.stationsNO = stationsNO;
    }

    @XmlTransient
    public Collection<Metrostation> getMetrostationCollection() {
        return metrostationCollection;
    }

    public void setMetrostationCollection(Collection<Metrostation> metrostationCollection) {
        this.metrostationCollection = metrostationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metroline)) {
            return false;
        }
        Metroline other = (Metroline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "t.Metroline[ id=" + id + " ]";
    }
    
}
