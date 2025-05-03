package com.menuplus.backend.modelTemp;

import com.menuplus.backend.library.common.EntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class IntegrationConfig extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 100)
  private String serviceName;

  @Column(nullable = false)
  private String apiKey;

  private String purpose;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "configurationId", nullable = false)
  private Configuration configuration;
}
