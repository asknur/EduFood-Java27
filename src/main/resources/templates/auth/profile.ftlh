<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <h1 class="mb-4">Профиль: ${user.name}</h1>
    <p class="lead">${user.email}</p>
    <hr>
    <h3 class="mt-4 mb-3">История заказов</h3>

    <#if orders?? && orders?has_content>
        <div class="list-group">
            <#list orders as order>
                <div class="list-group-item">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">Заказ №${order.id}</h5>
                        <small>${order.orderDate}</small>
                    </div>
                    <p class="mb-1">Итого: <strong>${order.totalAmount} сом</strong></p>
                    <small class="text-muted">
                        Состав:
                        <#list order.orderItems as item>
                            ${item.dish.name} (${item.quantity} шт.)<#if item?has_next>,</#if>
                        </#list>
                    </small>
                </div>
            </#list>
        </div>
    <#else>
        <p class="text-muted">У вас еще нет заказов.</p>
    </#if>
</@main.layout>