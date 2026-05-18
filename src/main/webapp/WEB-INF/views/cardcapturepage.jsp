<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Card Payment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .card-form {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }
        .form-title {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 2rem;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #34495e;
            font-weight: 500;
        }
        .form-control {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .form-control:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
        }
        .form-row {
            display: flex;
            gap: 1rem;
        }
        .form-row .form-group {
            flex: 1;
        }
        .btn-submit {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.2s;
        }
        .btn-submit:hover {
            background-color: #2980b9;
        }
        .card-icon {
            text-align: center;
            margin-bottom: 1.5rem;
        }
        .card-icon img {
            height: 40px;
        }
    </style>
</head>
<body>
    <div class="card-form">
        <h1 class="form-title">Card Payment</h1>
<!--         <div class="card-icon">
            <img src="https://cdn.iconscout.com/icon/free/png-512/credit-card-2712170-2249169.png" alt="Credit Card">
        </div>
 -->        <form action="/simulator/submit" method="post">
            <div class="form-group">
                <label for="cardName">Cardholder Name</label>
                <input type="text" id="cardName" name="cardName" class="form-control" placeholder="Name on card">
            </div>
            
            <div class="form-group">
                <label for="cardNumber">Card Number</label>
                <input type="text" id="cardNumber" name="cardNumber" class="form-control" 
                       placeholder="1234 5678 9012 3456" required>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="expiryDate">Expiry Date (MM/YY)</label>
                    <input type="text" id="expiryDate" name="expiryDate" class="form-control" 
                           pattern="(0[1-9]|1[0-2])\/[0-9]{2}" 
                           placeholder="MM/YY" maxlength="5">
                </div>
                
                <div class="form-group">
                    <label for="cvv">CVV</label>
                    <input type="password" id="cvv" name="cvv" class="form-control" 
                           pattern="[0-9]{3,4}" inputmode="numeric" maxlength="4" 
                           placeholder="•••">
                </div>
            </div>
            <button type="submit" class="btn-submit">Pay Now</button>
        </form>
    </div>
    
    <script>
        // Format card number with spaces
        document.getElementById('cardNumber').addEventListener('input', function(e) {
            let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
            let formattedValue = value.replace(/(\d{4})(?=\d)/g, '$1 ').trim();
            e.target.value = formattedValue;
        });
        
        // Format expiry date
        document.getElementById('expiryDate').addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            if (value.length >= 2) {
                value = value.substring(0, 2) + '/' + value.substring(2, 4);
            }
            e.target.value = value;
        });
        
        // Only allow numeric input for CVV
        document.getElementById('cvv').addEventListener('input', function(e) {
            e.target.value = e.target.value.replace(/\D/g, '');
        });
    </script>
</body>
</html>