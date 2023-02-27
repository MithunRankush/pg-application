package com.my.pgproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class PgEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  private String pgname;
  private double price;
  private int rating;
  private String add;
}
