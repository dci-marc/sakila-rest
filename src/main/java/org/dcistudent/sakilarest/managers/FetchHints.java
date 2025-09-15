package org.dcistudent.sakilarest.managers;

import org.jetbrains.annotations.NotNull;

public final class FetchHints {
  /**
   * Only fetches fields eager defined in the entity graph.
   */
  public static final @NotNull String GRAPH_FETCH = "jakarta.persistence.fetchgraph";

  /**
   * Fetches all fields eager defined in the entity graph and defined eager in
   * the entity.
   */
  public static final @NotNull String GRAPH_LOAD = "jakarta.persistence.loadgraph";

  private FetchHints() {
  }
}
