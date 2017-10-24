<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/inventory/inventory_macro.ftl" as inventoryMacro>

<@commonMacro.backToHome />

<#if inventory?has_content>
    <#if displaySuccessMessage!false>
        <div class="success-message">
            <p>New inventory successfully added.</p>
        </div>
    </#if>

    <@inventoryMacro.displayInventory inventory />
<#else>
    <div class="failed-message">
        <p>Inventory doesn't present.</p>
    </div>
</#if>
