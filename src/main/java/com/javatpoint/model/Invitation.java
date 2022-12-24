package com.javatpoint.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;



@Entity
@Table(name = "invitation")
public class Invitation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "message")
  private String message;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private InvitationStatus status;

  @Column(name = "expiry_date")
  private LocalDateTime expiryDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public InvitationStatus getStatus() {
    return status;
  }

  public void setStatus(InvitationStatus status) {
    this.status = status;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }
}


