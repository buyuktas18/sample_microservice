package com.javatpoint.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
//mark class as an Entity 




@Entity
@Table
public class User {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column
  @NotNull
  @Size(max = 100)
  private String fullName;

  @Column
  @NotNull
  @Email
  @Size(max = 100)
  private String email;

  @Column
  @Size(max = 100)
  private String normalizedName;

  @ManyToMany
  private List<Organization> organizations;

  public Status getStatus() {
    return this.status;
  }

  public String getFullName() {
    return this.fullName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getNormalizedName() {
    return this.normalizedName;
  }

  public void setStatus(Status status) {
  this.status = status;
}

public void setFullName(String fullName) {
  this.fullName = fullName;
}

public void setEmail(String email) {
  this.email = email;
}

public void setNormalizedName(String normalizedName) {
  this.normalizedName = normalizedName;
}

public List<Organization> getOrganizations() {
  return organizations;
}

public void setOrganizations(List<Organization> organizations) {
  this.organizations = organizations;
}


}