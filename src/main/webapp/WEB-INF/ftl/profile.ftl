<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/styles/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <title>Профиль</title>
</head>
<body>
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

<div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">${user.username!""}</h1>
    <h2 class="display-5">${user.points!"0"} $</h2>
    <p>${user.email!""}</p>

    <a href="/logout" class="btn btn-danger btn-lg px-4 gap-3">Выйти</a>
</div>

<h1 style="text-align: center; margin: auto; width: 100%;">Вопросы</h1>
<a href="/questions/create" class="button" style="margin: auto; width: 100%;"><h2 style="text-align: center">Задать
        вопрос</h2></a>
<#list questions as question>
    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-5">
            <h1 class="display-5 fw-bold">${question.title}</h1>
            <p class="col-md-8 fs-4">Ответов: ${question.getAnswers()?size}</p>
            <a class="btn btn-primary btn-lg" href="/questions/${question.id}">Открыть</a>
        </div>
    </div>
</#list>
<br>
<h1 style="text-align: center; margin: auto; width: 100%;">Статьи</h1>
<a href="/articles/create" class="button" style="margin: auto; width: 100%;"><h2 style="text-align: center">Написать
        статью и получить 200$</h2></a>
<#list articles as article>
    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-5">
            <h1 class="display-5 fw-bold">${article.title}</h1>
            <a class="btn btn-primary btn-lg" href="/articles/${article.id}">Открыть</a>
        </div>
    </div>
</#list>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="/resources/javascript/question.js"></script>
</body>
</html>
