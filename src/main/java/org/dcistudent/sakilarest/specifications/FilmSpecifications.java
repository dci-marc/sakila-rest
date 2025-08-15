package org.dcistudent.sakilarest.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class FilmSpecifications {

  private static final @NotNull Map<String, Function<FilmRequest, Specification<Film>>> FILTERS = new LinkedHashMap<>();

  static {
    FILTERS.put("title", request -> FilmSpecifications.hasTitle(request.getTitle()));
    FILTERS.put("description", request -> FilmSpecifications.hasDescription(request.getDescription()));
    FILTERS.put("releaseYear", request -> FilmSpecifications.hasReleaseYear(request.getReleaseYear()));
  }

  private FilmSpecifications() {
  }

  public static @NotNull Specification<Film> toSpecification(@NotNull FilmRequest request) {
    Specification<Film> spec = Specification.anyOf();

    for (Map.Entry<String, Function<FilmRequest, Specification<Film>>> entry : FILTERS.entrySet()) {
      String fieldName = entry.getKey();
      Object fieldValue = request.getRequestValue(fieldName);

      if (!fieldValue.toString().isEmpty() && !fieldValue.equals(0)) {
        spec = spec.and(entry.getValue().apply(request));
      }
    }

    return spec;
  }

  public static Specification<Film> hasTitle(@NotNull String title) {
    // non-Lambda version
    return new Specification<>() {
      @Override
      public Predicate toPredicate(
          Root<Film> root,
          CriteriaQuery<?> query,
          CriteriaBuilder cb) {
        return cb.like(root.get("title"), "%" + title + "%");
      }
    };

    /**
     * Lambda version:
     * return (root, query, cb) ->
     *     cb.like(root.get("title"), "%" + title + "%");
     */
  }

  public static Specification<Film> hasDescription(@NotNull String description) {
    return (root, query, cb) ->
        cb.like(cb.lower(cb.toString(root.get("description"))), "%" + description.toLowerCase() + "%");
  }

  public static Specification<Film> hasReleaseYear(int releaseYear) {
    return (root, query, cb) ->
        cb.equal(root.get("releaseYear"), releaseYear);
  }
}
