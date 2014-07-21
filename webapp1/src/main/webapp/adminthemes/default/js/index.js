var AdminUI = {
    menu:undefined,
    init:function(menu){
        this.menu =menu;
        var self =this;
    },
    disMenu:function(){
        this.disAppMenu();
    },
    disAppMenu:function(){
        var self=this;
        var appmenu = this.menu;
        var i=0;
        $.each(appmenu.app,function(k,v){
            var link = $("<a class='dashboard'  href='#' ><span>" + v.name + "</span></a>");
            $("<li></li>").appendTo($(".tabmenu>ul")).append(link);
            var children = v.children;
            link.click(function(){
                if(children){
                    self.disAppChildren(children);
                }
                $(".tabmenu li").removeClass("current");
                $(this).parent().addClass("current");
                return false;
            });
            if(i==0){
                link.click();
            }
            i++;
        });
    },
    disAppChildren:function(children){
        self = this;
        var leftMenu = $("#accordion");

        leftMenu.empty();

        $.each(children,function(k,v){
            leftMenu.append($("<h3 class=\"open\">"+ v.name+"</h3>"));
            if(this.children){
                leftMenu.append($("<div style=\"display: block;\" class=\"content\"><ul class='leftmenu' id='leftMenuItems'></ul></div>"));
                var leftMenuItems = $("#leftMenuItems");
                var i=0;
                $.each(this.children,function(k,v){
                    var link = self.createLink(v);
                    leftMenuItems.append($("<li></li>").append(link));
                    link.click(function(){
                        $("#accordion li").removeClass("current");
                        $(this).parent().addClass("current");
                    });
                    if(i==0){
                        link.click();
                    }
                    i++;

                });
            }
            leftMenu.append($("<h3 class=\"open\"></h3>"));
        });
    },
    createLink:function(v){
        var link = $("<a class='home' target='iframepage' href='"+ v.url +"' >" + v.name + "</a>");
        return link;
    }
}

$(function(){

    AdminUI.init(menu);
    AdminUI.disMenu();
    /**
     * Sidebar accordion
     **/
    $('#accordion h3').click(function() {
        if($(this).hasClass('open')) {
            $(this).removeClass('open');
            $(this).next().slideUp('fast');
        } else {
            $(this).addClass('open');
            $(this).next().slideDown('fast');
        } return false;
    });
});