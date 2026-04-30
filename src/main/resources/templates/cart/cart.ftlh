<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <h1 class="mb-4">Ваша корзина</h1>

    <#if cartItems?has_content>
        <div class="row">
            <div class="col-md-8">
                <table class="table align-middle">
                    <thead>
                    <tr>
                        <th>Блюдо</th>
                        <th class="text-center">Количество</th>
                        <th class="text-end">Цена</th>
                        <th class="text-end">Сумма</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list cartItems as item>
                        <tr>
                            <td>
                                <div class="fw-bold">${item.dish.name}</div>
                                <div class="text-muted small">
                                    Ресторан: <a href="/restaurant/${item.dish.restaurant.id}">${item.dish.restaurant.name}</a>
                                </div>
                            </td>
                            <td class="text-center">${item.quantity}</td>
                            <td class="text-end">${item.dish.price} ₽</td>
                            <td class="text-end fw-bold">${item.dish.price * item.quantity} ₽</td>
                            <td class="text-end">
                                <form action="/cart/remove" method="post">
                                    <#if _csrf??>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    </#if>
                                    <input type="hidden" name="dishId" value="${item.dish.id}">
                                    <button type="submit" class="btn btn-sm btn-outline-danger" title="Удалить">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Итого</h5>
                        <p class="card-text fs-3 fw-bold">${totalAmount} сом</p>

                        <#if Session["SPRING_SECURITY_CONTEXT"]?? && Session["SPRING_SECURITY_CONTEXT"].authentication.principal != "anonymousUser">
                            <form action="/orders/create" method="post">
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </#if>
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary btn-lg">Оформить заказ</button>
                                </div>
                            </form>
                        <#else>
                            <div class="alert alert-info">
                                <a href="/login">Войдите</a> или <a href="/register">зарегистрируйтесь</a>, чтобы оформить заказ.
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        <div class="alert alert-secondary">
            Ваша корзина пуста. <a href="/">Перейти к выбору ресторанов</a>.
        </div>
    </#if>
</@main.layout>