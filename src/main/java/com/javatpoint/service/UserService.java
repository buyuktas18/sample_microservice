package com.javatpoint.service;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatpoint.model.User;
import com.javatpoint.model.Organization;
import com.javatpoint.repository.UserRepository;
import java.util.List;
import com.javatpoint.model.ResourceNotFoundException;
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {

    if (userRepository.existsByEmail(user.getEmail())) {
        throw new IllegalArgumentException("Email already in use");
    }

    user.setNormalizedName(normalizeName(user.getFullName()));
    return userRepository.save(user);

  }
  

  public Iterable <User> getAllUsers() {
    return userRepository.findAll();
                          
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User updateUser(Long id, User user) {
    User existingUser = userRepository.findById(id).orElse(null);
    if (existingUser != null) {
      // Update the fields of the existing user entity with the values from the incoming user object
      existingUser.setStatus(user.getStatus());
      existingUser.setFullName(user.getFullName());
      existingUser.setEmail(user.getEmail());
      existingUser.setNormalizedName(user.getNormalizedName());
      return userRepository.save(existingUser);
    }
    return null;
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public List<User> searchByName(String name) {
    return userRepository.findByNormalizedNameContaining(name);
}

  public User searchByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getUsersByOrganization(Long organizationId) {
        return userRepository.findByOrganizationsId(organizationId);
    }

    public List<Organization> getOrganizationsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user.getOrganizations();
    }


    //Normalizer function for user service, it takes a string, convert all chars into lowercase and remove non-englsih characters
    public String normalizeName(String name) {
        String normalizedName = name.toLowerCase();
        normalizedName = normalizedName.replaceAll("[^a-z]", "");
        return normalizedName;
    }
    
    

}