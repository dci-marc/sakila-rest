package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

  default @NotNull List<Log> findWithLimit(@NotNull Integer limit) {
    if (limit < 1 || limit > 100) {
      throw new IllegalArgumentException("Limit must be between 1 and 100");
    }
    return findWithLimitStatement(limit);
  }

  @Query(
      "SELECT l " +
          "FROM Log AS l " +
          "ORDER BY datetime DESC " +
          "LIMIT :limit"
  )
  @NotNull List<Log> findWithLimitStatement(@NotNull @Param("limit") Integer limit);

  @Modifying
  @Query(
      "INSERT INTO Log (id, level, datetime, message) " +
          "VALUES (:id, :level, :datetime, :message)"
  )
  void insert(
      @NotNull @Param("level") Integer level,
      @NotNull @Param("datetime") String datetime,
      @NotNull @Param("message") String message
  );
}
