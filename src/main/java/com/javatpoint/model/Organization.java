package com.javatpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "organization")
public class Organization {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "normalized_name")
  private String normalizedName;

  @Column(name = "registry_number")
  private String registryNumber;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "year_founded")
  private Integer yearFounded;

  @Column(name = "phone")
  private String phone;

  @Column(name = "company_size")
  private Integer companySize;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNormalizedName() {
    return normalizedName;
  }

  public void setNormalizedName(String normalizedName) {
    this.normalizedName = normalizedName;
  }

  public String getRegistryNumber() {
    return registryNumber;
  }

  public void setRegistryNumber(String registryNumber) {
    this.registryNumber = registryNumber;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public Integer getYearFounded() {
    return yearFounded;
  }

  public void setYearFounded(Integer yearFounded) {
    this.yearFounded = yearFounded;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getCompanySize() {
    return companySize;
  }

  public void setCompanySize(Integer companySize) {
    this.companySize = companySize;
  }


  
}

