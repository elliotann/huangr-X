(function ($)
{
    var l = $.jeap;
    $.jeapDefaults.dialog = {
        init:function(){

        },
        confirm:function(title,message,f){
            $.confirm({
                'title'		: title,
                'message'	: message,
                'buttons'	: {
                    '确定'	: {
                        'class'	: 'blue',
                        'action': f
                    },
                    '取消'	: {
                        'class'	: 'gray',
                        'action': function(){
                            return false;
                        }
                    }
                }
            });
        },
        waiting:function(message){
            $.confirm({
                'title'		: "提示",
                'message'	: message
            });
        }
    }
})(jQuery);
