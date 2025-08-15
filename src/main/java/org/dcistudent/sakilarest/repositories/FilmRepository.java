package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Film;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

  @NotNull Page<Film> findAll(@NotNull Pageable pageable);

  @NotNull Optional<Film> findById(@NotNull Long id);

  @NotNull Optional<Page<Film>> findByTitleContainingIgnoreCase(
      @NotNull String pattern,
      @NotNull Pageable pageable
  );

  @Query("SELECT f FROM Film f WHERE LOWER(CAST(f.description AS string)) LIKE LOWER(CONCAT('%', :pattern, '%'))")
  @NotNull Optional<Page<Film>> findByDescription(
      @NotNull String pattern,
      @NotNull Pageable pageable
  );

  @NotNull Optional<Page<Film>> findByReleaseYear(
      @NotNull Integer releaseYear,
      @NotNull Pageable pageable
  );
}