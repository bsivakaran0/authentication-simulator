<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Successful</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }
        
        .status-container {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
            padding: 2.5rem;
            width: 100%;
            max-width: 450px;
            text-align: center;
        }
        
        .status-icon {
            width: 80px;
            height: 80px;
            background-color: #4CAF50;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1.5rem;
        }
        
        .status-icon i {
            color: white;
            font-size: 40px;
        }
        
        h1 {
            color: #2e7d32;
            margin-bottom: 1rem;
            font-weight: 500;
        }
        
        .amount {
            font-size: 2rem;
            font-weight: 500;
            color: #2e7d32;
            margin: 1rem 0;
        }
        
        .details {
            background: #f5f5f5;
            border-radius: 8px;
            padding: 1.25rem;
            margin: 1.5rem 0;
            text-align: left;
        }
        
        .detail-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 0.75rem;
            padding-bottom: 0.75rem;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .detail-row:last-child {
            border-bottom: none;
            margin-bottom: 0;
            padding-bottom: 0;
        }
        
        .detail-label {
            color: #666;
        }
        
        .detail-value {
            font-weight: 500;
        }
        
        .btn {
            display: inline-block;
            padding: 12px 28px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            margin-top: 1.5rem;
            transition: background-color 0.3s;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        
        .btn:hover {
            background-color: #3d8b40;
        }
        
        .help-text {
            margin-top: 1.5rem;
            color: #666;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="status-container">
        <div class="status-icon">
            <i>✓</i>
        </div>
        <h1>Payment Successful</h1>
        <p>Your transaction has been completed successfully.</p>
        
        <div class="amount">$${amount}</div>
        
        <div class="details">
            <div class="detail-row">
                <span class="detail-label">Transaction ID:</span>
                <span class="detail-value">${transactionId}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Date & Time:</span>
                <span class="detail-value">${transactionDate}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Payment Method:</span>
                <span class="detail-value">${paymentMethod}</span>
            </div>
        </div>
        
        <a href="/simulator/shoppingcart" class="btn">Back to Home</a>
        
        <p class="help-text">
            A receipt has been sent to your email at ${email}
        </p>
    </div>
</body>
</html>