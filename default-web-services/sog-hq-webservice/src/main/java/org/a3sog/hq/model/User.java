package org.a3sog.hq.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
  private Long id;
  private String name;
  private String email;
}
