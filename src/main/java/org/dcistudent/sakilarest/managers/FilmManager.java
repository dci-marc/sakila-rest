package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.repositories.FilmRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmManager {

  @NotNull
  private final FilmRepository filmRepository;

  public FilmManager(@NotNull FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public @NotNull Page<Film> findAll(@NotNull Integer limit, @NotNull Integer offset) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.filmRepository.findAll(pageable);
  }

  public @NotNull List<Film> findByTitle(@NotNull String pattern) {
    return this.filmRepository.findByTitleContaining(pattern)
        .orElseThrow(() -> new IllegalArgumentException("No films found with pattern: " + pattern));
  }
}
