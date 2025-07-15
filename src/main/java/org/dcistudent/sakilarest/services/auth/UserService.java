package org.dcistudent.sakilarest.services.auth;

import org.dcistudent.sakilarest.entities.auth.User;
import org.dcistudent.sakilarest.models.auth.UserRequest;
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
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      return false;
    }

    this.userRepository.save(
        new User(request.getName(), request.getEmail(), request.getPassword())
    );

    return true;
  }
}
