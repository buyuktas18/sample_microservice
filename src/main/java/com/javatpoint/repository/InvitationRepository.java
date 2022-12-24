package com.javatpoint.repository;
import org.springframework.data.repository.CrudRepository;
import com.javatpoint.model.Invitation;
import java.util.Optional;
import java.util.List;
import com.javatpoint.model.InvitationStatus;
import java.time.LocalDateTime;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {
    Optional<Invitation> findByUserIdAndStatus(Long userId, InvitationStatus status);
    List<Invitation> findByExpiryDateBefore(LocalDateTime expiryDate);
  }