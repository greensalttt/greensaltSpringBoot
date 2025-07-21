<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결제 완료</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f4f8;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background-color: white;
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #2d8fdd;
            font-size: 28px;
            margin-bottom: 16px;
        }

        p {
            font-size: 18px;
            color: #333;
        }

        .btn-group {
            margin-top: 30px;
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .btn {
            padding: 10px 24px;
            background-color: #2d8fdd;
            color: white;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #1b6fb8;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>✅ 결제가 성공했습니다!</h2>
    <p>감사합니다. 예매가 정상적으로 완료되었습니다.</p>

    <div class="btn-group">
        <a href="/" class="btn">홈으로 돌아가기</a>
        <a href="/mypage" class="btn">예매 확인하기</a>
    </div>
</div>

</body>
</html>
