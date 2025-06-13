<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 대시보드</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600;700&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        * {
            box-sizing: border-box;
            font-family: 'Noto Sans KR', sans-serif;
        }

        body {
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1100px;
            margin: 50px auto;
            background: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 6px 16px rgba(0,0,0,0.1);
        }

        .adminPage-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 40px;
        }

        .title {
            font-size: 28px;
            font-weight: 700;
            color: #2c3e50;
        }

        #adminlogoutLink {
            padding: 10px 20px;
            background-color: darkgreen;
            border: none;
            border-radius: 6px;
            color: white;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-card {
            background-color: #f9fafc;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            border: 1px solid #e0e0e0;
        }

        .stat-card span {
            display: block;
            font-size: 16px;
            color: #888;
            margin-bottom: 8px;
        }

        .stat-card strong {
            font-size: 22px;
            color: #2c3e50;
            font-weight: 700;
        }

        .action-section {
            display: flex;
            gap: 40px;
            flex-wrap: wrap;
            align-items: center;
        }

        .button-section {
            flex: 1;
            width: 200px;
        }

        .button-section h2 {
            font-size: 20px;
            margin: 30px 0 15px;
            color: #2c3e50;
            border-left: 4px solid darkgreen;
            padding-left: 10px;
        }

        .action-buttons {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 30px;
        }

        .btn {
            flex: 0 1 200px;
            background-color: darkgreen;
            color: white;
            text-align: center;
            padding: 12px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 600;
            transition: background-color 0.2s;
        }

        .chart-container {
            flex: 0 0 300px;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px 0;
            margin-top: 50px;
        }

    </style>
</head>
<body>
<div class="container">

    <div class="adminPage-header">
        <div class="title">관리자 대시보드</div>
        <form action="<c:url value='/admin/logout'/>" method="POST">
            <button type="submit" id="adminlogoutLink">로그아웃</button>
        </form>
    </div>

    <div class="stats">
        <div class="stat-card"><span>회원 수</span><strong>${custCount}</strong></div>
        <div class="stat-card"><span>게시글 수</span><strong>${boardCount}</strong></div>
        <div class="stat-card"><span>댓글 수</span><strong>${commentCount}</strong></div>
        <div class="stat-card"><span>앨범 수</span><strong>${albumCount}</strong></div>
        <div class="stat-card"><span>공연 수</span><strong>${performanceCount}</strong></div>
    </div>

    <div class="action-section">
        <div class="button-section">
            <h2>앨범 관리</h2>
            <div class="action-buttons">
                <a class="btn" href="/admin/album">앨범 업로드</a>
                <a class="btn" href="/admin/album_manage">앨범 관리</a>
            </div>

            <h2>공연 관리</h2>
            <div class="action-buttons">
                <a class="btn" href="/admin/performance">공연 업로드</a>
                <a class="btn" href="/admin/performance_manage">공연 관리</a>
            </div>

            <h2>게시판 관리</h2>
            <div class="action-buttons">
                <a class="btn" href="/admin/board_manage">게시글 관리</a>
                <a class="btn" href="/admin/comment_manage">댓글 관리</a>
            </div>

            <h2>회원 관리</h2>
            <div class="action-buttons">
                <a class="btn" href="/admin/cust_list">회원 목록</a>
            </div>
        </div>

        <div class="chart-container">
            <canvas id="donutChart" width="450" height="450"></canvas>
        </div>
    </div>
</div>

<script>
    window.onload = function () {
        if ("${sessionScope.aId}" !== "") {
            document.getElementById('adminlogoutLink').onclick = function () {
                if (confirm('관리자 페이지를 나가시겠습니까?')) {
                    alert('로그아웃이 되어 메인페이지로 이동합니다.');
                }
            };
        }

        if ("${adminWrite}" === "msg") {
            alert("앨범 등록에 성공했습니다.");
        }

        if ("${performanceWrite}" === "msg") {
            alert("공연 등록에 성공했습니다.");
        }

        // 원형 그래프 렌더링
        const ctx = document.getElementById('donutChart').getContext('2d');
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['회원', '게시글', '댓글', '앨범', '공연'],
                datasets: [{
                    label: '비율',
                    data: [
                        ${custCount},
                        ${boardCount},
                        ${commentCount},
                        ${albumCount},
                        ${performanceCount}
                    ],
                    backgroundColor: ['#3498db', '#e67e22', '#f1c40f', '#2ecc71', '#9b59b6'],
                    borderColor: '#ffffff',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: false,
                cutout: '60%',
                plugins: {
                    legend: {
                        position: 'bottom'
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return context.label + ': ' + context.formattedValue;
                            }
                        }
                    }
                }
            }
        });
    };
</script>
</body>
</html>
