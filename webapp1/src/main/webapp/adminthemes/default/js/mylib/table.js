(function ($)
{
    var l = $.jeap;
    $.jeapDefaults.table = {
        selectRow:function(){
            $(".select_rows").click(function(){
                var tableid = $(this).data('tableid');
                $('#'+tableid).find('input[name=row_sel]').attr('checked', this.checked);
            });
        }
    }
})(jQuery);
