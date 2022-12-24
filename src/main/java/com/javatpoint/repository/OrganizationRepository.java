
package com.javatpoint.repository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import com.javatpoint.model.Organization; 
import java.util.List;


public interface OrganizationRepository extends CrudRepository<Organization, Long>  {
  Optional<Organization> findByRegistryNumber(String registryNumber);
  List<Organization> findByNormalizedNameContainingAndYearFoundedAndCompanySize(String normalizedName, Integer year, Integer companySize);
  
}
