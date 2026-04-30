<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <a href="/" class="btn btn-outline-secondary mb-3"><i class="bi bi-arrow-left"></i> Все рестораны</a>
    <h1 class="mb-4">${restaurant.name}</h1>

    <h3>Меню</h3>
    <table class="table table-hover align-middle">
        <tbody>
        <#if dishesPage.content?has_content>
            <#list dishesPage.content as dish>
                <tr>
                    <td>
                        <div class="fw-bold">${dish.name}</div>
                        <div class="text-muted small">${dish.description!""}</div>
                    </td>
                    <td class="text-end h5">${dish.price} сом</td>
                    <td class="text-end" style="width: 120px;">
                        <form action="/cart/add" method="post">
                            <#if _csrf??>
                                <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}">
                            </#if>
                            <input type="hidden" name="dishId" value="${dish.id}">
                            <button type="submit" class="btn btn-sm btn-success">Добавить</button>
                        </form>
                    </td>
                </tr>
            </#list>
        <#else>
            <tr>
                <td colspan="3" class="text-center text-muted">Меню в данный момент пустое.</td>
            </tr>
        </#if>
        </tbody>
    </table>

    <#if dishesPage.totalPages gt 1>
        <nav class="mt-4">
            <ul class="pagination justify-content-center">
                <#list 1..dishesPage.totalPages as p>
                    <li class="page-item <#if p == dishesPage.number + 1>active</#if>">
                        <a class="page-link" href="/restaurant/${restaurant.id}?page=${p-1}">${p}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </#if>

</@main.layout>