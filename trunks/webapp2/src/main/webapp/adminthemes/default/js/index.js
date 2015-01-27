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
                var link = $("<a  target='" + v.target + "' href='" + v.url + "'><span>" + v.text + "</span></a>");
      
                var parentDiv = $("<div></div>");
                parentDiv.addClass('l-scroll');
                parentDiv.attr('title',v.text);
                parentDiv.appendTo($("#accordion1"));
                var treeUL = $("<ul></ul>");
                treeUL.attr('id','tree'+v.id);
                treeUL.css('margin-top','3px');
                treeUL.appendTo(parentDiv);
                var children = v.children;
                $("#tree"+v.id).ligerTree({
                    data : children,
                    checkbox: false,
                    slide: false,
                    nodeWidth: 120,
                    attribute: ['nodename', 'url'],
                    onSelect: function (node)
                    {
                        if (!node.data.url) return;
                        var tabid = $(node.target).attr("tabid");
                        if (!tabid)
                        {
                            tabid = new Date().getTime();
                            $(node.target).attr("tabid", tabid)
                        } 
                        f_addTab(tabid, node.data.text, node.data.url);
                    }
                });
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
        var leftMenu = $("#side_accordion");
        leftMenu.empty();
        $.each(children,function(k,v){
            var mainPanel = $("<div></div>");
            mainPanel.addClass("panel panel-default")

            var firstChild = $("<div></div>")
            firstChild.addClass("panel-heading");
            firstChild.appendTo(mainPanel);
            var linkNode = $("<a></a>");

            linkNode.addClass("accordion-toggle");
            linkNode.attr("href","#");
            linkNode.appendTo(firstChild);
            linkNode.append($("<i class=\"glyphicon glyphicon-folder-close\"></i>"));
            linkNode.append(v.text);

            linkNode.click(function(){
                $(".accordion-toggle").removeClass("collapse");
                $(".accordion-toggle").addClass("collapse");
                $(".accordion-body").removeClass("in");
                $(".accordion-body").addClass("collapse");
                if($(this).hasClass('collapse')) {
                    $(this).removeClass('collapse');
                    $(this).addClass("in");
                    $(this).parent().next().removeClass("collapse");
                    $(this).parent().next().addClass("in");
                } else {
                    $(this).removeClass('in');
                    $(this).addClass('collapse');
                    $(this).parent().next().removeClass("in");
                    $(this).parent().next().addClass("collapse");

                };
            });

            var secondChild = $("<div></div>");
            secondChild.addClass("accordion-body collapse");
            secondChild.css("height","auto");
            secondChild.appendTo(mainPanel);

            var panelBody = $("<div></div>");
            panelBody.addClass("panel-body");
            panelBody.appendTo(secondChild);

            var ulNode = $("<ul></ul>");
            ulNode.addClass("nav nav-pills nav-stacked");
            ulNode.appendTo(panelBody);
            if(this.children){
                $.each(this.children,function(k,v){

                    var link = self.createLink(v);
                    ulNode.append($("<li></li>").append(link));

                    link.click(function(){
                        Jeap.AdminUI.load($(this));
                        $("#accordion li").removeClass("current");
                        $(this).parent().addClass("current");
                        return false;
                    });
                });
            }

            leftMenu.append(mainPanel);

        });
    },
    createLink: function (v) {
        var link = $("<a  target='" + v.target + "' href='" + v.url+"&menuId=1" + "' >" + v.text + "</a>");
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



})


