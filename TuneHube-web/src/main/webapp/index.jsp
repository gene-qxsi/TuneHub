<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <!-- Подключение Bootstrap для стилизации страницы -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Можно добавить свои стили -->
    <style>
        /*body {*/
        /*    margin: 0;*/
        /*    padding: 0;*/
        /*    height: 100vh;*/
        /*    overflow: hidden;*/
        /*}*/
        /*.video-background {*/
        /*    position: fixed;*/
        /*    top: 0;*/
        /*    left: 0;*/
        /*    width: 100%;*/
        /*    height: 100%;*/
        /*    object-fit: cover;*/
        /*    z-index: -1;*/
        /*}*/
        /*.content {*/
        /*    position: relative;*/
        /*    z-index: 1;*/
        /*    color: #fff;*/
        /*    text-align: center;*/
        /*    padding-top: 20%;*/
        /*}*/
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
<img src="assets/images/back2.jpg" class="image-background" alt="Background Image">
<!-- Видео на заднем фоне -->
<%--<video class="video-background" autoplay loop muted playsinline>--%>
<%--    <source src="assets/videos/back2.mp4" type="video/mp4">--%>
<%--    Ваш браузер не поддерживает видео.--%>
<%--</video>--%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 registration-form">
            <h2 class="form-header">Регистрация</h2>
            <form action="${pageContext.request.contextPath}/registration" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Электронная почта</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>

                <div class="mb-3">
                    <label for="birthday" class="form-label">Дата рождения</label>
                    <input type="date" class="form-control" id="birthday" name="birthday" required>
                </div>

                <div class="mb-3">
                    <label for="role" class="form-label">Роль</label>
                    <select class="form-select" id="role" name="role" required>
                        <option value="user">Пользователь</option>
                        <option value="admin">Администратор</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Пол</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="male" required>
                        <label class="form-check-label" for="male">Мужской</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="female" required>
                        <label class="form-check-label" for="female">Женский</label>
                    </div>
                </div>

                <div class="mb-3 text-center">
                    <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                </div>

                <div class="text-center">
                    <p>Уже есть аккаунт? <a href="login.jsp">Войти</a></p>
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