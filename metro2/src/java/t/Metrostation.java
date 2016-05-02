/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pro
 */
@Entity
@Table(name = "metrostation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metrostation.findAll", query = "SELECT m FROM Metrostation m"),
    @NamedQuery(name = "Metrostation.findById", query = "SELECT m FROM Metrostation m WHERE m.id = :id"),
    @NamedQuery(name = "Metrostation.findByName", query = "SELECT m FROM Metrostation m WHERE m.name = :name"),
    @NamedQuery(name = "Metrostation.findByXs", query = "SELECT m FROM Metrostation m WHERE m.xs = :xs"),
    @NamedQuery(name = "Metrostation.findByYs", query = "SELECT m FROM Metrostation m WHERE m.ys = :ys")})
public class Metrostation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "xs")
    private double xs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ys")
    private double ys;
    @JoinColumn(name = "MetroLineID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Metroline metroLineID;

    public Metrostation() {
    }

    public Metrostation(Integer id) {
        this.id = id;
    }

    public Metrostation(Integer id, String name, double xs, double ys) {
        this.id = id;
        this.name = name;
        this.xs = xs;
        this.ys = ys;
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

    public double getXs() {
        return xs;
    }

    public void setXs(double xs) {
        this.xs = xs;
    }

    public double getYs() {
        return ys;
    }

    public void setYs(double ys) {
        this.ys = ys;
    }

    public Metroline getMetroLineID() {
        return metroLineID;
    }

    public void setMetroLineID(Metroline metroLineID) {
        this.metroLineID = metroLineID;
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
        if (!(object instanceof Metrostation)) {
            return false;
        }
        Metrostation other = (Metrostation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "t.Metrostation[ id=" + id + " ]";
    }
    
}
