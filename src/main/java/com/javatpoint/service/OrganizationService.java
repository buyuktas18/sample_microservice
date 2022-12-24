package com.javatpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatpoint.model.Organization;
import com.javatpoint.model.User;
import com.javatpoint.model.ResourceNotFoundException;
import com.javatpoint.repository.OrganizationRepository;
import com.javatpoint.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

  private final OrganizationRepository organizationRepository;
  private final UserRepository userRepository;

  @Autowired
  public OrganizationService(OrganizationRepository organizationRepository, UserRepository userRepository) {
    this.organizationRepository = organizationRepository;
    this.userRepository = userRepository; 
  }

  public Organization createOrganization(String name, String normalizedName, String registryNumber,
                                        String contactEmail, Integer yearFounded, String phone,
                                        Integer companySize) {
    Optional<Organization> existingOrganization = organizationRepository.findByRegistryNumber(registryNumber);
    if (existingOrganization.isPresent()) {
      throw new IllegalArgumentException("Registry number is already in use");
    }

    Organization organization = new Organization();
    organization.setName(name);
    organization.setNormalizedName(normalizeName(organization.getName()));
    organization.setRegistryNumber(registryNumber);
    organization.setContactEmail(contactEmail);
    organization.setYearFounded(yearFounded);
    organization.setPhone(phone);
    organization.setCompanySize(companySize);

    return organizationRepository.save(organization);
  }

  public Organization updateOrganization(Long organizationId, String name, String normalizedName,
                                        String registryNumber, String contactEmail, Integer yearFounded,
                                        String phone, Integer companySize) {
    Organization organization = organizationRepository.findById(organizationId)
      .orElseThrow(() -> new IllegalArgumentException("Organization not found"));

    organization.setName(name);
    organization.setNormalizedName(normalizeName(normalizedName));
    organization.setRegistryNumber(registryNumber);
    organization.setContactEmail(contactEmail);
    organization.setYearFounded(yearFounded);
    organization.setPhone(phone);
    organization.setCompanySize(companySize);

    return organizationRepository.save(organization);
  }

 

  public void addUserToOrganization(Long userId, Long organizationId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

    user.getOrganizations().add(organization);
    

    userRepository.save(user);
    
}

public List<Organization> searchOrganizations(String normalizedName, Integer year, Integer companySize) {
    return organizationRepository.findByNormalizedNameContainingAndYearFoundedAndCompanySize(normalizedName, year, companySize);
}

public Organization getOrganizationByRegistryNumber(String registryNumber) {
    return organizationRepository.findByRegistryNumber(registryNumber).orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
}

  //Normalizer function for organization service, it takes a string, convert all chars into lowercase and remove non-englsih and non-numeric characters
  public String normalizeName(String name) {
    String normalizedName = name.toLowerCase();
    normalizedName = normalizedName.replaceAll("[^a-z0-9]", "");
    return normalizedName;
    }
}



