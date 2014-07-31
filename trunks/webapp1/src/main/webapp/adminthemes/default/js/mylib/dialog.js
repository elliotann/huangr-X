(function ($)
{
    var l = $.jeap;
    $.jeapDefaults.dialog = {
        init:function(){

        },
        confirm:function(title,message){
            $.confirm({
                'title'		: title,
                'message'	: message,
                'buttons'	: {
                    '确定'	: {
                        'class'	: 'blue',
                        'action': function(){
                             return true;
                        }
                    },
                    '取消'	: {
                        'class'	: 'gray',
                        'action': function(){
                            return false;
                        }	// Nothing to do in this case. You can as well omit the action property.
                    }
                }
            });


        }
    }
})(jQuery);
