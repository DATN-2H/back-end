package com.menuplus.backend.library.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menuplus.backend.library.enumeration.GeneralStatus;
import com.menuplus.backend.library.security.UserDetailsCustom;
import com.menuplus.backend.user.model.User;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@Data
public abstract class EntityBase implements Serializable {

  private LocalDateTime createdAt;

  private Long createdBy;

  private LocalDateTime updatedAt;

  private Long updatedBy;

  @Enumerated(EnumType.STRING)
  private GeneralStatus status = GeneralStatus.ACTIVE;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "createdBy",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  @EqualsAndHashCode.Exclude
  private User createdUser;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "updatedBy",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  @EqualsAndHashCode.Exclude
  private User updatedUser;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
    UserDetailsCustom user = UserDetailsCustom.getCurrentUser();
    if (user != null) {
      if (this.createdBy == null) this.createdBy = user.getUserId();
      this.updatedBy = user.getUserId();
    }
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = LocalDateTime.now();
    UserDetailsCustom user = UserDetailsCustom.getCurrentUser();
    if (user != null) {
      this.updatedBy = user.getUserId();
    }
  }

  @Transient
  public String getCreatedUsername() {
    if (createdUser == null) return "System";
    return createdUser.getUsername();
  }

  @Transient
  public String getUpdatedUsername() {
    if (updatedUser == null) return "System";
    return updatedUser.getUsername();
  }
}
