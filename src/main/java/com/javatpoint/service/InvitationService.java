package com.javatpoint.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatpoint.model.Invitation;
import com.javatpoint.model.User;
import com.javatpoint.model.Status;
import com.javatpoint.model.InvitationStatus;
import com.javatpoint.repository.InvitationRepository;
import com.javatpoint.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;


@Service
public class InvitationService {

  private final InvitationRepository invitationRepository;
  private final UserRepository userRepository;

  @Autowired
  public InvitationService(InvitationRepository invitationRepository, UserRepository userRepository) {
    this.invitationRepository = invitationRepository;
    this.userRepository = userRepository;
  }

  public Invitation createInvitation(Long userId, String message) {
    Optional<Invitation> pendingInvitation = invitationRepository.findByUserIdAndStatus(userId, InvitationStatus.PENDING);
    if (pendingInvitation.isPresent()) {
      throw new IllegalStateException("Cannot create invitation, user already has a pending invitation");
    }
    Optional<Invitation> rejectedInvitation = invitationRepository.findByUserIdAndStatus(userId, InvitationStatus.REJECTED);
        if (rejectedInvitation.isPresent()) {
            throw new IllegalStateException("User cannot be reinvited after a rejected invitation");
        }

    Invitation invitation = new Invitation();
    invitation.setUserId(userId);
    invitation.setMessage(message);
    invitation.setStatus(InvitationStatus.PENDING);
    invitation.setExpiryDate(LocalDateTime.now().plusWeeks(1));

    return invitationRepository.save(invitation);
  }

  public Invitation updateInvitation(Long userId, Invitation invitation) {
    Optional<Invitation> pendingInvitation = invitationRepository.findByUserIdAndStatus(userId, InvitationStatus.PENDING);
    if (!pendingInvitation.isPresent()) {
        throw new IllegalStateException("User does not have a pending invitation");
        
    }
    Invitation oldInvitation = pendingInvitation.orElse(null);
    oldInvitation.setStatus(invitation.getStatus());
    User user = userRepository.findById(userId).orElse(null);

    //change the status of the user if s/he accpets the invitation
    if(invitation.getStatus() == InvitationStatus.ACCEPTED){
        user.setStatus(Status.ACTIVE);
    }
    

    return invitationRepository.save(oldInvitation);
}

  public Invitation updateInvitationStatus(Long invitationId, InvitationStatus status) {
    Invitation invitation = invitationRepository.findById(invitationId)
      .orElseThrow(() -> new IllegalArgumentException("Invitation not found"));

    invitation.setStatus(status);
    return invitationRepository.save(invitation);
  }

  public Iterable <Invitation> getAllInvitations() {
    return invitationRepository.findAll();
                          
  }

  public void deleteInvitation(Long id) {
    invitationRepository.deleteById(id);
  }


  @Scheduled(cron = "0 0 * * * *") // run every hour
  public void expireInvitations() {
    LocalDateTime now = LocalDateTime.now();
    List<Invitation> expiredInvitations = invitationRepository.findByExpiryDateBefore(now);
    expiredInvitations.forEach(invitation -> invitation.setStatus(InvitationStatus.EXPIRED));
    invitationRepository.saveAll(expiredInvitations);
  }

}