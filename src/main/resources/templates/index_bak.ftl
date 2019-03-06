<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#setting boolean_format="yes,no"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>首页</title>
</head>
<body>
<div>
    欢迎,<@sec.authentication property="principal.username"/>
    <@sec.authentication property="principal.menus" var="menus"/>
    菜单：
    <ul>
        <#--宏定义 递归加载菜单-->
        <#macro Tree children>
            <#if children?has_content>
                <#list children as item>
                    <li>${item.text}
                    <#if item.children?has_content>
                        <ul>
                        <@Tree children=item.children/>
                        </ul>
                    </#if>
                    </li>
                </#list>
            </#if>
        </#macro>
        <#--调用宏 递归生成树-->
        <@Tree children=menus/>
    </ul>
</div>
</body>
</html>