<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/styles/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <title>Вопрос</title>
</head>
<body onload="connect(${user.id})">
<header>
    <nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
        <div class="container-fluid">
            <a class="navbar-brand" href="/questions">найтиответ.тут</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02"
                    aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/questions">Вопросы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/articles">Статьи</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/main_chat">Чат</a>
                    </li>
                    <#if user??>
                        <li class="nav-item">
                            <a class="nav-link active" href="/profile">Профиль</a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link active" href="/signIn">Войти</a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div style="margin: auto; padding: 10px">
    <h2 style="text-align: center; margin: auto; width: 100%;">Чат</h2>

    <div style="width: 50%">
        <div id="messagesList"></div>
        <div style="text-align: center">
            <button id="enterChatButton" class="btn btn-primary btn-lg" onclick="enterChat('${user.username}')">Войти</button>
        </div>
        <label for="message"></label><input name="message" id="message" disabled placeholder="Сообщение" hidden>
        <button onclick="sendMessage('${user.username}', $('#message').val())" class="btn btn-primary" id="sendMessageButton" hidden>Отправить
        </button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="/resources/javascript/chat.js"></script>
</body>
</html>
