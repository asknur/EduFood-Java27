<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <div class="row justify-content-center mt-4">
        <div class="col-lg-5 col-md-7">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h2 class="card-title text-center mb-4">Регистрация</h2>

                    <#if registrationError??>
                        <div class="alert alert-danger" role="alert">
                            ${registrationError}
                        </div>
                    </#if>


                    <form action="/register" method="post">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">
                            <label for="name" class="form-label">Ваше имя</label>
                            <@spring.formInput "userDto.name" 'id="name" class="form-control"'/>
                            <@spring.showErrors "<br>" "error text-danger" />

                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <@spring.formInput "userDto.email" 'id="email" class="form-control" type="email"'/>
                            <@spring.showErrors "<br>" "error text-danger" />

                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Пароль</label>
                            <@spring.formInput "userDto.password" 'id="password" class="form-control" type="password"'/>
                            <@spring.showErrors "<br>" "error text-danger" />

                        </div>

                        <div class="d-grid mt-4">
                            <button type="submit" class="btn btn-primary btn-lg">Зарегистрироваться</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p>Уже есть аккаунт? <a href="/login">Войдите</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>