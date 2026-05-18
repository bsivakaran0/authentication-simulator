// Cart.java
package com.paymoni.simulator.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private int itemCount;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;

    public void calculateTotals() {
        // Calculate subtotal
        this.subtotal = items.stream()
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Calculate tax (example: 8% tax rate)
        this.tax = this.subtotal.multiply(new BigDecimal("0.08"));
        
        // Calculate total
        this.total = this.subtotal.add(this.tax);
        
        // Calculate item count
        this.itemCount = items.stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
    }

    // Getters and setters
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    public int getItemCount() { return itemCount; }
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getTax() { return tax; }
    public void setTax(BigDecimal tax) { this.tax = tax; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}

// CartItem.java
