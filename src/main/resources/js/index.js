//绑定初始化事件
$(function () {
//选项卡绑定点击事件
    $(document).on('click', '.content-header > .nav-bar > li', function () {
        // 切换选项卡
        $(".content-header > .nav-bar > li").removeClass("cur");
        $(this).addClass('cur');
        //切换 关闭按钮
        $(".content-header > .nav-bar > li > i").removeClass("cur");
        $(this).find("i").addClass("cur");
        //切换内容区域
        var index = $(this).data("index");
        $(".content > .iframe").removeClass("cur");
        $("#iframe_" + index).addClass("cur");
    });
    //触发首页菜单
    $("#my_home").click();
});

/**
 * 菜单 选项卡
 * @type {{}}
 */
var Tab = {
    //添加选项卡
    addTab: function (title, url) {
        var index = url.replace(/\./g, '_').replace(/\//g, '_').replace(/:/g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
        var nav_bar = $(".content-header > .nav-bar");
        if ($("#tab_" + index).length === 0) {
            //去掉所有的选项卡以及关闭按钮激活样式
            $(".content-header > .nav-bar > li").removeClass("cur");
            $(".content-header > .nav-bar > li > i").removeClass("cur");
            $(".content > .iframe").removeClass("cur");
            //组装菜单选项卡
            var tab = '<li id="tab_' + index + '" class="cur" data-index="' + index + '">' + title + '<i class="fa fa-times cur" onclick="Tab.closeTab($(\'#tab_' + index + '\'))"></i></li>';
            nav_bar.append(tab);
            //内容区域
            var content = $(".content");
            var iframe = '<div id="iframe_' + index + '" class="iframe cur" "><iframe class="tab_iframe" src="' + url + '" width="100%" frameborder="0" scrolling="auto" "></iframe></div>';
            content.append(iframe);
        } else {
            //菜单选项卡已经存在，激活选项卡
            $("#tab_" + index).click();
        }
    },
    closeTab: function ($item) {
        var hasClass = $item.hasClass("cur");
        var index = $item.data("index");
        if (hasClass) {
            //点击激活菜单,length为1 表示有元素，如果为0 表示没有元素  ---是否取得有效值
            if ($item.prev().length === 1) {
                //如果有前一个选项卡激活
                $item.prev().click();
                $("#iframe_" + index).remove();
                $item.remove();
                return;
            } else if ($item.next().length === 1) {
                //如果没有前面的选项卡，有后面的则激活后面的
                $item.next().click();
                $("#iframe_" + index).remove();
                $item.remove();
                return;
            } else {
                $("#iframe_" + index).remove();
                $item.remove();
                return;
            }
        } else {
            //点击的不是激活菜单就直接关掉
            $("#iframe_" + index).remove();
            $item.remove();
        }
    }
};