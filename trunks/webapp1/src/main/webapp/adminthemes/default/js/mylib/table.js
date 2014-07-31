(function ($)
{
    var l = $.jeap;
    $.jeapDefaults.table = {
        selectRow:function(){
            $(".select_rows").click(function(){
                var tableid = $(this).data('tableid');
                $('#'+tableid).find('input[name=row_sel]').attr('checked', this.checked);
            });
        },
        changeColor:function(){


            $("#smpl_tbl tr td").hover(function(){
                $(this).parent().find("td").css("background","#DCF0F8");
            },function(){
                $(this).parent().find("td").css("background","#ffffff")
            });
            $("#smpl_tbl tr td").each(function(i,v){
                $(this).click(function(){

                        $(this).parent().find("td").css("background","#DCF0F8");

                });
            });
        }
    }
})(jQuery);
