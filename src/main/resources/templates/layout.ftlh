<#import "/spring.ftl" as spring>
<#macro layout>
    <#assign cartCount = (cartItemCount!0)>

    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>EduFood</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body class="d-flex flex-column min-vh-100">

    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary shadow-sm">
            <div class="container">
                <a class="navbar-brand" href="/"><strong>EduFood</strong></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="/">Рестораны</a>
                        </li>
                    </ul>
                    <div class="d-flex align-items-center">
                        <a href="/cart" class="btn btn-outline-dark position-relative me-3 fs-5"
                           style="border-radius: 50%; width: 45px; height: 45px;">
                            <#if cartCount gt 0>
                                <i class="bi bi-cart-fill"></i>
                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                                      style="font-size: 0.7rem;">
            ${cartItemCount}
        </span>
                            <#else>
                                <i class="bi bi-cart"></i>
                            </#if>
                        </a>
                        <#if Session["SPRING_SECURITY_CONTEXT"]?? && Session["SPRING_SECURITY_CONTEXT"].authentication.principal != "anonymousUser">
                            <#assign currentUser = Session["SPRING_SECURITY_CONTEXT"].authentication.principal>
                            <div class="dropdown">
                                <a class="btn btn-outline-primary dropdown-toggle" href="#" role="button"
                                   data-bs-toggle="dropdown">
                                    <i class="bi bi-person-circle me-1"></i>
                                    ${currentUser.username}
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="/profile">Мой профиль</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li>
                                        <form action="/logout" method="post" class="px-3">
                                            <#if _csrf??>
                                                <input type="hidden"
                                                       name="${_csrf.parameterName}"
                                                       value="${_csrf.token}">
                                            </#if>
                                            <button type="submit"
                                                    class="btn btn-link p-0 text-decoration-none text-dark">Выйти
                                            </button>
                                        </form>
                                    </li>
                                </ul>
                            </div>
                        <#else>
                            <a href="/login" class="btn btn-outline-success me-2">Войти</a>
                            <a href="/register" class="btn btn-success">Регистрация</a>
                        </#if>
                    </div>
                </div>
            </div>
        </nav>
    </header>

    <main class="container mt-4 mb-5 flex-grow-1">
        <#nested spring>
    </main>

    <footer class="bg-light text-center text-lg-start mt-auto">
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.05);">
            © ${( .now?string("yyyy"))} EduFood
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>