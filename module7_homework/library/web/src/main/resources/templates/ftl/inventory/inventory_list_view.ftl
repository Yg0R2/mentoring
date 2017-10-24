<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/inventory/inventory_macro.ftl" as inventoryMacro>

<@commonMacro.backToHome />

<div class="inventories">
    <legend>Inventories:</legend>

    <#list inventories as inventory>
        <@inventoryMacro.displayInventory inventory />
    </#list>
</div>
