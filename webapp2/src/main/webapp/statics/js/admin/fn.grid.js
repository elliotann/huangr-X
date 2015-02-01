/**
 * 后台grid构建js
 * @author andy
 */
var GridUI = {
    searchFields: undefined,
    accordion: undefined,
    init: function (searchFields, accordion) {
        this.searchFields = searchFields;
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
    buildSearch: function () {
        var self = this;
        var searchFields = this.searchFields;
        searchForm = $("#searchForm").ligerForm({fields: searchFields});
        var container = $('<ul><li id="btn1container"><div class="button button2 buttonnoicon" style="width: 60px"><div class="button-l"></div><div class="button-r"></div><span>搜索</span></div></li></ul><div class="l-clear"></div>').appendTo($("#searchForm"));
    },
    autoHeight: function () {
        var height = $(window).height() - 140;

        $("#right_content").height(height);

    }

};


