<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OTP Verification - Secure Banking</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #2c3e50;
        }
        
        .otp-container {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            padding: 2.5rem;
            width: 100%;
            max-width: 420px;
            text-align: center;
        }
        
        .bank-logo {
            width: 120px;
            margin-bottom: 1.5rem;
        }
        
        h2 {
            color: #2c3e50;
            margin-bottom: 1.5rem;
            font-weight: 600;
        }
        
        .otp-message {
            color: #7f8c8d;
            margin-bottom: 2rem;
            line-height: 1.5;
        }
        
        .otp-input-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 2rem;
        }
        
        .otp-input {
            width: 50px;
            height: 60px;
            text-align: center;
            font-size: 24px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            margin: 0 5px;
            transition: all 0.3s;
        }
        
        .otp-input:focus {
            border-color: #3498db;
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
            outline: none;
        }
        
        .submit-btn {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 14px 28px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s;
        }
        
        .submit-btn:hover {
            background-color: #2980b9;
        }
        
        .resend-otp {
            margin-top: 1.5rem;
            color: #7f8c8d;
            font-size: 14px;
        }
        
        .resend-otp a {
            color: #3498db;
            text-decoration: none;
            font-weight: 500;
        }
        
        .resend-otp a:hover {
            text-decoration: underline;
        }
        
        .secure-notice {
            margin-top: 1.5rem;
            font-size: 12px;
            color: #95a5a6;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 5px;
        }
        
        .secure-notice i {
            color: #2ecc71;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="otp-container">
        <img src="https://cdn-icons-png.flaticon.com/512/196/196578.png" alt="Bank Logo" class="bank-logo">
        <h2>Enter OTP</h2>
        <p class="otp-message">
            We've sent a 6-digit verification code to your registered mobile number ending with <strong>******7890</strong>.
            This code is valid for 5 minutes.
        </p>
        
        <form action="/simulator/verifyotp" method="post">
            <div class="otp-input-container">
                <input type="text" class="otp-input" name="otp1" maxlength="1" pattern="[0-9]" inputmode="numeric" required autofocus>
                <input type="text" class="otp-input" name="otp2" maxlength="1" pattern="[0-9]" inputmode="numeric" required>
                <input type="text" class="otp-input" name="otp3" maxlength="1" pattern="[0-9]" inputmode="numeric" required>
                <span style="width: 10px;"></span> <!-- Spacer -->
                <input type="text" class="otp-input" name="otp4" maxlength="1" pattern="[0-9]" inputmode="numeric" required>
                <input type="text" class="otp-input" name="otp5" maxlength="1" pattern="[0-9]" inputmode="numeric" required>
                <input type="text" class="otp-input" name="otp6" maxlength="1" pattern="[0-9]" inputmode="numeric" required>
            </div>
            <button type="submit" class="submit-btn">Verify & Proceed</button>
        </form>
        
        <p class="resend-otp">
            Didn't receive the code? <a href="#" id="resendLink">Resend OTP</a>
            <span id="countdown" style="display: none;">(1:00)</span>
        </p>
        
        <div class="secure-notice">
            <i>🔒</i> Your information is secure and encrypted
        </div>
    </div>

    <script>
        // Auto-focus next input when a digit is entered
        const inputs = document.querySelectorAll('.otp-input');
        
        inputs.forEach((input, index) => {
            // Handle input
            input.addEventListener('input', (e) => {
                if (e.target.value.length === 1) {
                    if (index < inputs.length - 1) {
                        inputs[index + 1].focus();
                    }
                }
            });
            
            // Handle backspace
            input.addEventListener('keydown', (e) => {
                if (e.key === 'Backspace' && e.target.value === '' && index > 0) {
                    inputs[index - 1].focus();
                }
            });
        });
        
        // Resend OTP functionality
        /*const resendLink = document.getElementById('resendLink');
        const countdownElement = document.getElementById('countdown');
        let countdown = 60;
        
        resendLink.addEventListener('click', (e) => {
            e.preventDefault();
            
            // Disable resend link and show countdown
            resendLink.style.pointerEvents = 'none';
            resendLink.style.opacity = '0.7';
            countdownElement.style.display = 'inline';
            
            // Start countdown
            const countdownInterval = setInterval(() => {
                countdown--;
                countdownElement.textContent = `(${Math.floor(countdown / 60)}:${String(countdown % 60).padStart(2, '0')})`;
                
                if (countdown <= 0) {
                    clearInterval(countdownInterval);
                    resendLink.style.pointerEvents = 'auto';
                    resendLink.style.opacity = '1';
                    countdownElement.style.display = 'none';
                    countdown = 60;
                }
            }, 1000);
            
            // Here you would typically make an API call to resend the OTP
            console.log('Resending OTP...');
        });*/
    </script>
</body>
</html>