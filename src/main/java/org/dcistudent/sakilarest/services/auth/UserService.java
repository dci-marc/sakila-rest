package org.dcistudent.sakilarest.services.auth;

import org.dcistudent.sakilarest.entities.auth.User;
import org.dcistudent.sakilarest.models.requests.UserRequest;
import org.dcistudent.sakilarest.repositories.auth.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @NotNull
  private final UserRepository userRepository;

  public UserService(@NotNull UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean create(@NotNull UserRequest request) {
    if (this.userRepository.findByEmail(request.getEmail()).isPresent()) {
      return false;
    }

    this.userRepository.save(
        new User(request.getEmail(), request.getPassword())
    );

    return true;
  }
}
