package com.javatpoint.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


import com.javatpoint.model.Organization;  
import com.javatpoint.service.OrganizationService;  
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

  private final OrganizationService organizationService;


  @Autowired
  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;

  }

  @PostMapping
  public ResponseEntity<Organization> createOrganization(@Valid @RequestBody Organization organization) {
    Organization createdOrganization = organizationService.createOrganization(organization.getName(),
      organization.getNormalizedName(), organization.getRegistryNumber(), organization.getContactEmail(),
      organization.getYearFounded(), organization.getPhone(), organization.getCompanySize());
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest().path("/{id}")
      .buildAndExpand(createdOrganization.getId()).toUri();
    return ResponseEntity.created(location).body(createdOrganization);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Organization> updateOrganization(@PathVariable Long id,
                                                        @Valid @RequestBody Organization organization) {
    Organization updatedOrganization = organizationService.updateOrganization(id, organization.getName(),
      organization.getNormalizedName(), organization.getRegistryNumber(), organization.getContactEmail(),
      organization.getYearFounded(), organization.getPhone(), organization.getCompanySize());
    return ResponseEntity.ok(updatedOrganization);
  }

  @PostMapping("/{organizationId}/users/{userId}")
    public ResponseEntity<?> addUserToOrganization(@PathVariable Long userId, @PathVariable Long organizationId) {
        organizationService.addUserToOrganization(userId, organizationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchOrganizations")
    public ResponseEntity<List<Organization>> searchOrganizations(
            @RequestParam(required = false) String normalizedName,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer companySize
    ) {
        List<Organization> organizations = organizationService.searchOrganizations(normalizedName, year, companySize);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{registryNumber}")
    public ResponseEntity<Organization> getOrganizationByRegistryNumber(@PathVariable String registryNumber) {
        Organization organization = organizationService.getOrganizationByRegistryNumber(registryNumber);
        return ResponseEntity.ok(organization);
    }


    @GetMapping
    public Iterable<Organization> getAllUsers() {
      return organizationService.getAllOrganizations();
    }


    @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    organizationService.deleteOrganization(id);
  }

    
}
