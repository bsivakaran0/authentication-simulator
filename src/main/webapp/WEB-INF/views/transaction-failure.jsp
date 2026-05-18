<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction Failed</title>
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
            background-color: #f44336;
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
            color: #c62828;
            margin-bottom: 1rem;
            font-weight: 500;
        }
        
        .error-message {
            background: #ffebee;
            border-left: 4px solid #f44336;
            padding: 1rem;
            margin: 1.5rem 0;
            text-align: left;
            border-radius: 4px;
        }
        
        .btn {
            display: inline-block;
            padding: 12px 28px;
            background-color: #f44336;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            margin: 1rem 0.5rem;
            transition: background-color 0.3s;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        
        .btn-secondary {
            background-color: #757575;
        }
        
        .btn:hover {
            background-color: #d32f2f;
        }
        
        .btn-secondary:hover {
            background-color: #616161;
        }
        
        .help-text {
            margin-top: 1.5rem;
            color: #666;
            font-size: 14px;
        }
        
        .action-buttons {
            margin-top: 1.5rem;
        }
    </style>
</head>
<body>
    <div class="status-container">
        <div class="status-icon">
            <i>✕</i>
        </div>
        <h1>Transaction Failed</h1>
        <p>We couldn't process your payment. Please try again.</p>
        
        <div class="error-message">
            <strong>Error:</strong> ${errorMessage}
        </div>
        
        <div class="action-buttons">
            <a href="/simulator/shoppingcart" class="btn">Try Again</a>
            <a href="/simulator/shoppingcart" class="btn btn-secondary">Back to Home</a>
        </div>
        
        <p class="help-text">
            If the problem persists, please contact our support team.
            <br>Reference ID: ${transactionId}
        </p>
    </div>
</body>
</html>