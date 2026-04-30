<#import "layout.ftlh" as main>

<@main.layout ; spring>
    <h1 class="mb-4">Рестораны</h1>

    <form action="/" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" name="query" class="form-control" placeholder="Найти ресторан..." value="${query!""}">
            <button class="btn btn-primary" type="submit">Поиск</button>
        </div>
    </form>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#if restaurant?has_content>
            <#list restaurant as restaurant>
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${restaurant.name}</h5>
                            <a href="/restaurant/${restaurant.id}" class="btn btn-primary mt-2">Перейти в меню</a>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted">Рестораны не найдены.</p>
        </#if>
    </div>

    <#if totalPages?? && totalPages gt 1>
        <nav class="mt-4">
            <ul class="pagination justify-content-center">
                <#list 1..totalPages as p>
                    <li class="page-item <#if p == currentPage>active</#if>">
                        <a class="page-link" href="/?page=${p-1}<#if query??>&query=${query}</#if>">${p}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </#if>
</@main.layout>