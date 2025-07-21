package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Film;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

  @NotNull Optional<List<Film>> findByTitleContaining(@NotNull String pattern);
}