package com.javatpoint.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javatpoint.model.Invitation;
import com.javatpoint.service.InvitationService;

import javax.validation.Valid;


import java.net.URI;


@RestController
@RequestMapping("/invitations")
public class InvitationController {

  private final InvitationService invitationService;

  @Autowired
  public InvitationController(InvitationService invitationService) {
    this.invitationService = invitationService;
  }

  @PostMapping
  public ResponseEntity<Invitation> createInvitation(@Valid @RequestBody Invitation invitationRequest) {
    Invitation createdInvitation = invitationService.createInvitation(invitationRequest.getUserId(),
      invitationRequest.getMessage());
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest().path("/{id}")
      .buildAndExpand(createdInvitation.getId()).toUri();
    return ResponseEntity.created(location).body(createdInvitation);
  }

  @PutMapping("/users/{userId}")
    public Invitation updateInvitation(@PathVariable Long userId, @Valid @RequestBody Invitation invitation) {
        return invitationService.updateInvitation(userId, invitation);
    }
}



