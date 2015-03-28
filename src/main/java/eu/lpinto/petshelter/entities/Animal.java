/**
 * Animal.java Created on 12-Aug-2014, 21:15:03
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Entity
@XmlRootElement
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findById", query = "SELECT a FROM Animal a WHERE a.id = :id"),
    @NamedQuery(name = "Animal.findByCreated", query = "SELECT a FROM Animal a WHERE a.created = :created"),
    @NamedQuery(name = "Animal.findByUpdated", query = "SELECT a FROM Animal a WHERE a.updated = :updated"),
    @NamedQuery(name = "Animal.findByName", query = "SELECT a FROM Animal a WHERE a.name = :name"),
    @NamedQuery(name = "Animal.findByAge", query = "SELECT a FROM Animal a WHERE a.age = :age"),
    @NamedQuery(name = "Animal.findBySpecies", query = "SELECT a FROM Animal a WHERE a.species = :species"),
    @NamedQuery(name = "Animal.findByBreed", query = "SELECT a FROM Animal a WHERE a.breed = :breed"),
    @NamedQuery(name = "Animal.findByDrugs", query = "SELECT a FROM Animal a WHERE a.drugs = :drugs"),
    @NamedQuery(name = "Animal.findBySex", query = "SELECT a FROM Animal a WHERE a.sex = :sex"),
    @NamedQuery(name = "Animal.findByStatus", query = "SELECT a FROM Animal a WHERE a.status = :status"),
    @NamedQuery(name = "Animal.findBySterilized", query = "SELECT a FROM Animal a WHERE a.sterilized = :sterilized"),
    @NamedQuery(name = "Animal.findByObservations", query = "SELECT a FROM Animal a WHERE a.observations = :observations"),
    @NamedQuery(name = "Animal.findByOrganizationId", query = "SELECT a FROM Animal a WHERE a.organization = :organization")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;
    @Basic(optional = false)
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updated;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Size(max = 30)
    @Column(name = "species")
    private String species;
    @Size(max = 30)
    @Column(name = "breed")
    private String breed;
    @Size(max = 200)
    @Column(name = "drugs")
    private String drugs;
    @Size(max = 1)
    @Column(name = "sex")
    private String sex;
    @Column(name = "sterilized")
    private Boolean sterilized;
    @Column(name = "status")
    private Integer status;
    @Lob
    @Column(name = "picture")
    private String picture;
    @Size(max = 250)
    @Column(name = "observations")
    private String observations;
    @Column(name = "admission")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar admission;
    @Size(max = 30)
    @Column(name = "furPattern")
    private String furPattern;
    @Column(name = "proportion")
    private Integer proportion;
    @Column(name = "weight")
    private float weight;
    @Size(max = 30)
    @Column(name = "primaryColor")
    private String primaryColor;
    @Size(max = 30)
    @Column(name = "secondaryColor")
    private String secondaryColor;
    @OneToMany(mappedBy = "animal")
    private Set<ClinicalEpisode> clinicalEpisodes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /* Constructors */
    public Animal() {
    }

    public Animal(Integer id) {
        this.id = id;
    }

    public Set<ClinicalEpisode> getClinicalEpisodes() {
        return clinicalEpisodes;
    }

    public void setClinicalEpisodes(Set<ClinicalEpisode> clinicalEpisodes) {
        this.clinicalEpisodes = clinicalEpisodes;
    }

    public Animal(Integer id, Calendar created, Calendar updated, int organization) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    /* Getters/Setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSterilized() {
        return sterilized;
    }

    public void setSterilized(Boolean sterilized) {
        this.sterilized = sterilized;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Calendar getAdmission() {
        return admission;
    }

    public void setAdmission(Calendar admission) {
        this.admission = admission;
    }

    public String getFurPattern() {
        return furPattern;
    }

    public void setFurPattern(String furPattern) {
        this.furPattern = furPattern;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
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
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.lpinto.petshelter.entities.Animal[ id=" + id + " ]";
    }

}
