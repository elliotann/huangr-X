/* [ ---- Gebo Admin Panel - wizard ---- ] */

$(document).ready(function() {

    //* wizard with validation
    gebo_wizard.validation();
    //* add step numbers to titles
    gebo_wizard.steps_nb();


});

gebo_wizard = {

    validation: function(){
        $('#validate_wizard').stepy({
            nextLabel:      '下一步',
            backLabel:      '返回',
            block		: true,
            errorImage	: true,
            titleClick	: true,
            validate :true
        });
        stepy_validation = $('#validate_wizard').validate({
            onfocusout: false,
            errorPlacement: function(error, element) {
                error.appendTo( element.closest("div.controls") );
            },
            highlight: function(element) {
                $(element).closest("div.form-group").addClass("error f_error");
                var thisStep = $(element).closest('form').prev('ul').find('.current-step');
                thisStep.addClass('error-image');
            },
            unhighlight: function(element) {
                $(element).closest("div.form-group").removeClass("error f_error");
                if(!$(element).closest('form').find('div.error').length) {
                    var thisStep = $(element).closest('form').prev('ul').find('.current-step');
                    thisStep.removeClass('error-image');
                };
            },

            rules: {
                'readed':'required',
                'dbHost': 'required',
                'db_uname':'required',
                'db_passwd':'required',
                'dbName':'required'
            }, messages: {
                'v_username'	: { required:  'Username field is required!' },
                'dbHost'	: { required:  '请输入数据库主机!' },
                'db_uname'	: { required:  '请输入数据库用户名!' },
                'db_passwd'		: { required:  '请输入数据库密码!' },
                'dbName'		: { required:  '请输入数据库名!' }
            }
        });
    },
    //* add numbers to step titles
    steps_nb: function(){
        $('.stepy-titles').each(function(){
            $(this).children('li').each(function(index){
                var myIndex = index + 1
                $(this).append('<span class="stepNb">'+myIndex+'</span>');
            })
        })
    }
};