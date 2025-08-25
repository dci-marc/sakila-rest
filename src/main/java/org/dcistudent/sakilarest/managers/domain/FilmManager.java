package org.dcistudent.sakilarest.managers.domain;

import org.dcistudent.sakilarest.entities.domain.Film;
import org.dcistudent.sakilarest.managers.domain.specifications.FilmSpecifications;
import org.dcistudent.sakilarest.models.requests.domain.FilmRequest;
import org.dcistudent.sakilarest.repositories.domain.FilmRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public final class FilmManager {

  @NotNull
  private final FilmRepository filmRepository;

  public FilmManager(@NotNull FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public @NotNull Film findById(@NotNull UUID id) {
    return this.filmRepository
        .findByUuid(id)
        .orElseThrow(() -> new NoSuchElementException("Film not found with ID: " + id));
  }

  public @NotNull Page<Film> findAll(@NotNull FilmRequest request, @NotNull Pageable pageable) {
    Specification<Film> specification = FilmSpecifications.toSpecification(request);
    return this.filmRepository.findAll(specification, pageable);
  }
}
