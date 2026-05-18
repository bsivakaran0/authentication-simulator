<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Challenge Request</title>
    <script type="text/javascript">
        function autoSubmit() {
            document.forms[0].submit();
        }
    </script>
</head>
<body onload="autoSubmit()">
    <form action="${acsurl}" method="post">
        <input type="hidden" name="creq" value="${creq}">
    </form>
</body>
</html>