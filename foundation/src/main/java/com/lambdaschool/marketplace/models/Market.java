package com.lambdaschool.marketplace.models;

import javax.persistence.*;

/**
 * The entity allowing interaction with the categories table
 */
@Entity
@Table(name = "markets")
public class Market extends Auditable {
  /**
   * The primary key (long) of the items table.
   */
  @Id
  @GeneratedValue
  private long marketId;

  /**
   * Item name. Cannot be null.
   */
  @Column(nullable = false)
  private String name;

  /**
   * Default constructor used primarily by the JPA.
   */
  public Market() {}

  /**
   * Give the params, create a new market object.
   * marketId is autogenerated
   *
   * @param name The name (String) of the market
   */
  public Market(String name) {
    this.name = name;
  }

  /**
   * Getter for marketId
   *
   * @return the marketId (long) of the market
   */
  public long getMarketId() {
    return marketId;
  }

  /**
   * Setter for marketId. Used primary for seeding data
   *
   * @param marketId the new marketId (long) of the market
   */
  public void setMarketId(long marketId) {
    this.marketId = marketId;
  }

  /**
   * Getter for name
   *
   * @return the name (String) of the market
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for item name
   *
   * @param name (string) of the market
   */
  public void setName(String name) {
    this.name = name;
  }
}