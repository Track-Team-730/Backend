package com.lambdaschool.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * The entity allowing interaction with the items table
 */
@Entity
@Table(name = "items")
public class Item extends Auditable {
    /**
     * The primary key (long) of the items table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    /**
     * Item name. Cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Item description.
     */
    private String description;

    /**
     * Item price (double). Cannot be null.
     */
    @Column(nullable = false)
    private double price;

    /**
     * The userId of the user assigned to this item (e.g. the item seller).
     * This is the entire user object!
     * <p>
     * Forms a Many to One relationship between items and users.
     * A user can have many items.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private User user;

    /**
     * The marketId of the market assigned to this item.
     * This is the entire market object!
     * <p>
     * Forms a Many to One relationship between items and markets.
     * A market can have many items.
     */
    @ManyToOne
    @JoinColumn(name = "marketId", nullable = false)
    @JsonIgnoreProperties(value = "market", allowSetters = true)
    private Market market;

    /**
     * The productId of the product (category) assigned to this item.
     * This is the entire product object!
     * <p>
     * Forms a Many to One relationship between items and products.
     * A product can have many items.
     */
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    @JsonIgnoreProperties(value = "product", allowSetters = true)
    private Product product;

    /**
     * Default constructor used primarily by the JPA.
     */
    public Item() {
    }

    /**
     * Given the params, create a new item object
     * <p>
     * itemId is autogenerated
     *
     * @param name The name (String) of the item
     * @param description The description (String) of the item
     * @param price The price (double) of the item
     */
    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Getter for itemId
     *
     * @return the itemId (long) of the item
     */
    public long getItemId() {
        return itemId;
    }

    /**
     * Setter for itemId. Used primary for seeding data
     *
     * @param itemId the new itemId (long) of the item
     */
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter for name
     *
     * @return the name (String) of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for item name
     *
     * @param name (string) of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for description
     *
     * @return the description (String) of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the item description
     *
     * @param description (String) of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for price
     *
     * @return the price (double) of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price of the item
     *
     * @param price (double) of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for user
     *
     * @return the user assigned to the item
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for the user assigned to the item
     *
     * @param user the user object to replace the one assigned to this item object
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for market
     *
     * @return the market assigned to the item
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Setter for the market assigned to the item
     *
     * @param market the market object to replace the one assigned to this item object
     */
    public void setMarket(Market market) {
        this.market = market;
    }

    /**
     * Getter for product (category)
     *
     * @return the product (category) assigned to the item
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Setter for the product (category) assigned to the item
     *
     * @param product the product object to replace the one assigned to this item object
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}