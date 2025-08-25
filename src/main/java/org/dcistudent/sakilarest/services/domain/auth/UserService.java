package org.dcistudent.sakilarest.services.domain.auth;

import org.dcistudent.sakilarest.entities.domain.auth.User;
import org.dcistudent.sakilarest.models.requests.domain.UserRequest;
import org.dcistudent.sakilarest.repositories.domain.auth.UserRepository;
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
