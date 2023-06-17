package org.a3sog.hq.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Blog implements Serializable {
  private Long id;
  private String title;
  private String date;
  private String content;
  private String author;
}
