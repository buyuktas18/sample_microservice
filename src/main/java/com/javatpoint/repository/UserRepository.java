package com.javatpoint.repository;
import org.springframework.data.repository.CrudRepository;
import com.javatpoint.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByNormalizedNameContaining(String name);
    List<User> findByOrganizationsId(Long organizationId);
    
    
  }
