package org.dcistudent.sakilarest.models.responses.domain.s3.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Permission {

  @JsonProperty("read")
  private boolean read = false;
  @JsonProperty("write")
  private boolean write = false;
  @JsonProperty("delete")
  private boolean delete = false;

  public Permission(boolean read, boolean write, boolean delete) {
    this.read = read;
    this.write = write;
    this.delete = delete;
  }

  public boolean canRead() {
    return this.read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  public boolean canWrite() {
    return this.write;
  }

  public void setWrite(boolean write) {
    this.write = write;
  }

  public boolean canDelete() {
    return this.delete;
  }

  public void setDelete(boolean delete) {
    this.delete = delete;
  }

  @Override
  public String toString() {
    return String.format("[r=%s,w=%s,d=%s]", this.read, this.write, this.delete);
  }
}
