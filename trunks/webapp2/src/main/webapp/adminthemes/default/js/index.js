/**
 * 后台界面构建js
 * @author andy
 */
var BackendUi = {
    menu: undefined,
    accordion: undefined,
    init: function (menu, accordion) {
        Jeap.AdminUI.init({wrapper:$("#right_content")});

        $(".desktop a").click(function () {
            //Jeap.AdminUI.load($(this));
            return false;
        });


        this.menu = menu;
        this.accordion = accordion;
        this.autoHeight();
        var self = this;
        $(window).resize(function () {
            self.autoHeight();
        });
    },
    disMenu: function () {
        this.disSysMenu();
        this.disAppMenu();
    },

    /**
     * 显示系统菜单
     */
    disSysMenu: function () {
        var self = this;
        var menu = this.menu;
        $.each(menu.sys, function (k, v) {
            var link = self.createLink(v);
            $("<li/>").appendTo($(".sysmenu>ul")).append(link);
            if (v.target != '_blank') {
                link.click(function () {
                    Jeap.AdminUI.load($(this));
                    return false;
                });
            }
        });
    },

    /**
     * 显示应用菜单
     */
    disAppMenu: function () {
        var self = this;
        var menu = this.menu;
        var i = 0;
        $.each(menu.app, function (k, v) {
            if (founder == 1 && (v.id == 237 || v.id == 244 || v.id == 266)) {
            } else {
                var link = $("<a class='dashboard' target='" + v.target + "' href='" + v.url + "'><span>" + v.text + "</span></a>");
                $("<li></li>").appendTo($(".tabmenu>ul")).append(link);
                var children = v.children;

                link.click(function () {
                    if (children) {
                        self.disAppChildren(children);
                    }
                    $(".tabmenu li").removeClass("current");
                    $(this).parent().addClass("current");

                    return false;
                });

                if (i == 0) {
                    var href = link.attr("href");
                    var target = link.attr("target");
                    link.attr("href", app_path + "/core/admin/index.do");
                    link.removeAttr("target");
                    link.click();
                    link.attr("href", href);
                    link.attr("target", target);
                }
                i++;
            }
        });
    },
    /**
     * 显示应用的子菜单
     */
    disAppChildren:function(children){

        var self= this;
        var leftMenu = $("#accordion");
        leftMenu.empty();

        $.each(children,function(k,v){
            leftMenu.append($("<h3 class=\"open\">"+ v.text+"</h3>"));

            if(this.children){

                leftMenu.append($("<div style=\"display: block;\" class=\"content\"><ul class='leftmenu' id='leftMenuItems"+ v.id+"'></ul></div>"));
                var leftMenuItems = $("#leftMenuItems"+ v.id);

                $.each(this.children,function(k,v){

                    var link = self.createLink(v);
                    leftMenuItems.append($("<li></li>").append(link));

                    link.click(function(){
                        Jeap.AdminUI.load($(this));
                        $("#accordion li").removeClass("current");
                        $(this).parent().addClass("current");
                        return false;
                    });
                });
            }

            leftMenu.append($("<h3 class=\"open\"></h3>"));
        });
    },
    createLink: function (v) {
        var link = $("<a class='home' target='" + v.target + "' href='" + v.url+"&menuId=1" + "' >" + v.text + "</a>");
        return link;
    },
    autoHeight: function () {
        var height = $(window).height() - 140;

        $("#right_content").height(height);

    }

};
var tab;
$(function () {
    BackendUi.init(menu, null);
    BackendUi.disMenu();
    $('.accordion-toggle').click(function() {

        if($(this).hasClass('collapse')) {
            $(this).removeClass('collapse');
            $(this).addClass("in");
            $("#collapseOne").removeClass('collapse');
            $("#collapseOne").addClass("in");

        } else {
            $(this).removeClass('in');
            $(this).addClass('collapse');
            $("#collapseOne").addClass('collapse');

        } return false;
    });


})


