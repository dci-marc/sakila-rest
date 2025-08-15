package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.repositories.FilmRepository;
import org.dcistudent.sakilarest.specifications.FilmSpecifications;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FilmManager {

  @NotNull
  private final FilmRepository filmRepository;

  public FilmManager(@NotNull FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public @NotNull Film findById(@NotNull Long id) {
    return this.filmRepository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("Film not found with ID: " + id));
  }

  public @NotNull Page<Film> findAll(@NotNull FilmRequest request, @NotNull Pageable pageable) {
    Specification<Film> specification = FilmSpecifications.toSpecification(request);
    return this.filmRepository.findAll(specification, pageable);
  }
}
