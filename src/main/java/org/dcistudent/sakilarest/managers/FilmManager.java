package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.repositories.FilmRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

  public @NotNull Film findById(@NotNull Long id) {
    return this.filmRepository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("Film not found with ID: " + id));
  }

  public @NotNull Page<Film> findByTitle(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull String pattern
  ) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.filmRepository
        .findByTitleContainingIgnoreCase(pattern, pageable)
        .orElseThrow(() -> new NoSuchElementException("No films found with pattern: " + pattern));
  }

  public @NotNull Page<Film> findByDescription(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull String pattern
  ) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.filmRepository
        .findByDescription(pattern, pageable)
        .orElseThrow(() -> new NoSuchElementException("No films found with pattern: " + pattern));
  }

  public @NotNull Page<Film> findByReleaseYear(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull Integer pattern
  ) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.filmRepository
        .findByReleaseYear(pattern, pageable)
        .orElseThrow(() -> new NoSuchElementException("No films found with release year: " + pattern));
  }
}
