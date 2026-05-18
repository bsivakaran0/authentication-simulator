<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #3498db;
            --primary-hover: #2980b9;
            --text-color: #333;
            --light-gray: #f5f5f5;
            --border-color: #e0e0e0;
            --success-color: #2ecc71;
        }
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f9f9f9;
            color: var(--text-color);
            line-height: 1.6;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        header {
            background-color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 15px 0;
            margin-bottom: 30px;
        }
        
        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .logo {
            font-size: 24px;
            font-weight: 700;
            color: var(--primary-color);
            text-decoration: none;
        }
        
        .cart-icon {
            position: relative;
            font-size: 20px;
            color: var(--text-color);
            text-decoration: none;
        }
        
        .cart-count {
            position: absolute;
            top: -8px;
            right: -8px;
            background-color: var(--primary-color);
            color: white;
            border-radius: 50%;
            width: 18px;
            height: 18px;
            font-size: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        
        .cart-container {
            display: flex;
            flex-direction: column;
            gap: 30px;
            margin-bottom: 40px;
        }
        
        .cart-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        
        .cart-title {
            font-size: 28px;
            font-weight: 500;
        }
        
        .continue-shopping {
            color: var(--primary-color);
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 5px;
        }
        
        .cart-items {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            overflow: hidden;
        }
        
        .cart-item {
            display: grid;
            grid-template-columns: 100px 1fr 150px 150px 50px;
            align-items: center;
            padding: 20px;
            border-bottom: 1px solid var(--border-color);
        }
        
        .cart-item:last-child {
            border-bottom: none;
        }
        
        .item-image {
            height: 40px;
            object-fit: cover;
            border-radius: 4px;
        }
        
        .item-details {
            padding: 0 20px;
        }
        
        .item-title {
            font-weight: 500;
            margin-bottom: 5px;
        }
        
        .item-sku {
            color: #666;
            font-size: 14px;
        }
        
        .item-price {
            font-weight: 500;
            color: var(--text-color);
        }
        
        .quantity-selector {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        
        .quantity-btn {
            width: 30px;
            height: 30px;
            border: 1px solid var(--border-color);
            background: white;
            border-radius: 4px;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 16px;
        }
        
        .quantity-input {
            width: 50px;
            height: 30px;
            text-align: center;
            border: 1px solid var(--border-color);
            border-radius: 4px;
        }
        
        .remove-item {
            color: #999;
            background: none;
            border: none;
            cursor: pointer;
            font-size: 18px;
            transition: color 0.2s;
        }
        
        .remove-item:hover {
            color: #e74c3c;
        }
        
        .cart-summary {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            padding: 25px;
            margin-top: 20px;
        }
        
        .summary-title {
            font-size: 20px;
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 1px solid var(--border-color);
        }
        
        .summary-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }
        
        .summary-label {
            color: #666;
        }
        
        .summary-value {
            font-weight: 500;
        }
        
        .total-row {
            border-top: 1px solid var(--border-color);
            padding-top: 15px;
            margin-top: 15px;
            font-size: 18px;
            font-weight: 500;
        }
        
        .checkout-btn {
            display: block;
            width: 100%;
            padding: 15px;
            background-color: var(--primary-color);
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 500;
            text-align: center;
            cursor: pointer;
            margin-top: 25px;
            transition: background-color 0.2s;
            text-decoration: none;
        }
        
        .checkout-btn:hover {
            background-color: var(--primary-hover);
        }
        
        .checkout-btn:disabled {
            background-color: #cccccc;
            cursor: not-allowed;
        }
        
        .empty-cart {
            text-align: center;
            padding: 50px 20px;
        }
        
        .empty-cart i {
            font-size: 60px;
            color: #ddd;
            margin-bottom: 20px;
        }
        
        .empty-cart h2 {
            margin-bottom: 15px;
            color: #555;
        }
        
        .empty-cart p {
            color: #777;
            margin-bottom: 25px;
        }
        
        .continue-shopping-btn {
            display: inline-block;
            padding: 12px 30px;
            background-color: var(--primary-color);
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            transition: background-color 0.2s;
        }
        
        .continue-shopping-btn:hover {
            background-color: var(--primary-hover);
        }
        
        @media (max-width: 768px) {
            .cart-item {
                grid-template-columns: 80px 1fr;
                grid-template-rows: auto auto auto;
                gap: 15px;
                position: relative;
                padding: 15px;
            }
            
            .item-image {
                grid-row: 1 / 3;
            }
            
            .item-details {
                grid-column: 2;
                padding: 0;
            }
            
            .item-price {
                grid-column: 2;
                grid-row: 3;
            }
            
            .quantity-selector {
                grid-column: 1 / 3;
                grid-row: 4;
                justify-content: flex-start;
                margin-top: 10px;
            }
            
            .remove-item {
                position: absolute;
                top: 15px;
                right: 15px;
            }
        }
    </style>
</head>
<body>
    <header>
        <div class="container header-content">
            <a href="/" class="logo">ShopEasy</a>
            <a href="#" class="cart-icon">
                <i class="fas fa-shopping-cart"></i>
                <span class="cart-count">${cart.itemCount}</span>
            </a>
        </div>
    </header>
    
    <div class="container">
        <div class="cart-container">
            <div class="cart-header">
                <h1 class="cart-title">Your Shopping Cart</h1>
                <!-- <a href="/products" class="continue-shopping">
                    <i class="fas fa-arrow-left"></i> Continue Shopping
                </a> -->
            </div>
            
            <c:choose>
                <c:when test="${empty cart.items}">
                    <div class="empty-cart">
                        <i class="fas fa-shopping-cart"></i>
                        <h2>Your cart is empty</h2>
                        <p>Looks like you haven't added any items to your cart yet.</p>
                        <a href="/products" class="continue-shopping-btn">
                            Continue Shopping
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="cart-items">
                        <c:forEach items="${cart.items}" var="item">
                            <div class="cart-item">
                                <img src="${pageContext.request.contextPath}/resources/images/${item.product.imageUrl}" alt="${item.product.name}" class="item-image">
                                <div class="item-details">
                                    <div class="item-title">${item.product.name}</div>
                                    <div class="item-sku">SKU: ${item.product.sku}</div>
                                </div>
                                <div class="item-price">$${item.product.price}</div>
                                <%-- <div class="quantity-selector">
                                    <button class="quantity-btn minus" onclick="updateQuantity(${item.product.id}, -1)">-</button>
                                    <input type="number" class="quantity-input" value="${item.quantity}" min="1" 
                                           onchange="updateQuantityInput(${item.product.id}, this.value)">
                                    <button class="quantity-btn plus" onclick="updateQuantity(${item.product.id}, 1)">+</button>
                                </div>
                                <button class="remove-item" onclick="removeItem(${item.product.id})">
                                    <i class="fas fa-times"></i>
                                </button> --%>
                            </div>
                        </c:forEach>
                    </div>
                    
                    <div class="cart-summary">
                        <h3 class="summary-title">Order Summary</h3>
                        <div class="summary-row">
                            <span class="summary-label">Subtotal (${cart.itemCount} items)</span>
                            <span class="summary-value">$${cart.subtotal}</span>
                        </div>
                        <div class="summary-row">
                            <span class="summary-label">Shipping</span>
                            <span class="summary-value">
                                <c:choose>
                                    <c:when test="${cart.subtotal >= 50}">Free</c:when>
                                    <c:otherwise>$5.99</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <div class="summary-row">
                            <span class="summary-label">Tax</span>
                            <span class="summary-value">$${cart.tax}</span>
                        </div>
                        <div class="summary-row total-row">
                            <span>Total</span>
                            <span>$${cart.total}</span>
                        </div>
                        
                        <a href="/simulator/cardcapture" class="checkout-btn">
                            Proceed to Checkout
                        </a>
                        
                        <p style="text-align: center; margin-top: 15px; color: #666; font-size: 14px;">
                            <i class="fas fa-lock"></i> Secure Checkout
                        </p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    <script>
        function updateQuantity(productId, change) {
            const input = document.querySelector(`input[onchange*="${productId}"]`);
            let newValue = parseInt(input.value) + change;
            if (newValue < 1) newValue = 1;
            input.value = newValue;
            updateCart(productId, newValue);
        }
        
        function updateQuantityInput(productId, value) {
            let quantity = parseInt(value);
            if (isNaN(quantity) || quantity < 1) {
                quantity = 1;
                document.querySelector(`input[onchange*="${productId}"]`).value = 1;
            }
            updateCart(productId, quantity);
        }
        
        function updateCart(productId, quantity) {
            // In a real application, you would make an AJAX call to update the cart
            console.log(`Updating product ${productId} quantity to ${quantity}`);
            // Example:
            // fetch('/api/cart/update', {
            //     method: 'POST',
            //     headers: { 'Content-Type': 'application/json' },
            //     body: JSON.stringify({ productId, quantity })
            // }).then(response => response.json())
            //   .then(data => window.location.reload());
        }
        
        function removeItem(productId) {
            if (confirm('Are you sure you want to remove this item from your cart?')) {
                // In a real application, you would make an AJAX call to remove the item
                console.log(`Removing product ${productId} from cart`);
                // Example:
                // fetch(`/api/cart/remove/${productId}`, { method: 'POST' })
                //     .then(response => response.json())
                //     .then(data => window.location.reload());
            }
        }
    </script>
</body>
</html>