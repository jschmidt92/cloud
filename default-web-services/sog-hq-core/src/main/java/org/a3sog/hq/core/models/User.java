package org.a3sog.hq.core.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
  private Long id;
  private String name;
  private String email;
}
