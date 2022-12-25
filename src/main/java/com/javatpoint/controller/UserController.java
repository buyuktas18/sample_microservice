package com.javatpoint.controller;
import java.util.List;  
//import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RestController;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;    
import com.javatpoint.model.User;  
import com.javatpoint.model.Organization;  
import com.javatpoint.service.UserService;  
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")

public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping
  public Iterable<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

  @GetMapping("organizations/{organizationId}")
    public ResponseEntity<List<User>> getUsersByOrganization(@PathVariable Long organizationId) {
        List<User> users = userService.getUsersByOrganization(organizationId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}/organizations")
    public ResponseEntity<List<Organization>> getOrganizationsByUser(@PathVariable Long userId) {
        List<Organization> organizations = userService.getOrganizationsByUser(userId);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/searchUserByNormalizedName")
    public List<User> searchUsersByNormalizedName(@RequestParam("normalizedName") String normalizedName) {
        return userService.searchByName(normalizedName);
    }

    @GetMapping("/searchUserByEmail")
    public User searchUsersByEmail(@RequestParam("email") String email) {
        return userService.searchByEmail(email);
    }

}
