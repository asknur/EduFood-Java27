<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <div class="row justify-content-center mt-4">
        <div class="col-lg-4 col-md-6">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h2 class="card-title text-center mb-4">Вход</h2>

                    <#if RequestParameters.error??>
                        <div class="alert alert-danger">
                            Неверный email или пароль.
                        </div>
                    </#if>

                    <#if RequestParameters.success??>
                        <div class="alert alert-success">
                            ${RequestParameters.success}
                        </div>
                    </#if>

                    <form action="/login" method="post">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>
                        <div class="mb-3">
                            <label for="username" class="form-label">Email</label>
                            <input type="email" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="d-grid mt-4">
                            <button type="submit" class="btn btn-primary btn-lg">Войти</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>