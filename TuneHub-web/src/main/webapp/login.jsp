<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>

        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background-color: #f8f9fa;
        }

        .image-background {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            z-index: -1; /* Это гарантирует, что изображение будет на заднем фоне */
        }

        .registration-form {
            margin-top: 50px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        .form-header {
            text-align: center;
            margin-bottom: 20px;
        }

    </style>
</head>
<body>
<img src="assets/images/back3.jpg" class="image-background" alt="Background Image">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 registration-form">
            <h2 class="form-header">Вход</h2>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>

                <div class="mb-3 text-center">
                    <button type="submit" class="btn btn-primary">Войти</button>
                </div>

                <div class="text-center">
                    <p>Еще нет аккаунта? <a href="registration.jsp">Зарегистрироваться</a></p>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Подключение JavaScript для Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>