(function ($)
{
    var l = $.jeap;
    $.jeapDefaults.dialog = {
        init:function(){

        },
        confirm:function(title){
            $.confirm({
                'title'		: title,
                'message'	: '确定删除?',
                'buttons'	: {
                    '确定'	: {
                        'class'	: 'blue',
                        'action': function(){

                        }
                    },
                    '取消'	: {
                        'class'	: 'gray',
                        'action': function(){}	// Nothing to do in this case. You can as well omit the action property.
                    }
                }
            });


        }
    }
})(jQuery);
