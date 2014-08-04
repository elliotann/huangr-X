<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />

    <title>Description Demo</title>

    <link rel="stylesheet" href="/jeap/adminthemes/default/css/form.css" />
    <link rel="stylesheet" href="/jeap/adminthemes/default/css/validate.css" />
</head>

<body>


<div class="container">
    <form id="form">
        <table>
            <tr>
                <td><label for="username">用户名</label>:</td>
                <td>
                    <input class="form-control" type="text" nam="username" id="username" data-describedby="messages" data-description="username" data-required  />
                    <span id="messages" class="error"></span>
                </td>
            </tr>
        </table>



            <br/>

            <button type="submit" class="btn btn-primary">Send</button>


    </form>
</div>

<script src="/jeap/js/common/jquery-1.9.0.min.js"></script>
<script src="/jeap/js/common/jquery-validate.js"></script>

<script>
    $('form').validate({
        eachValidField : function() {

            $(this).removeClass('error').addClass('success');
        },
        eachInvalidField : function() {

            $(this).removeClass('success').addClass('error');
        },
        description : {
            username : {
                required : '用户名不能为空!',
                pattern : '<div class="error">Pattern</div>',
                conditional : '<div class="error">Conditional</div>'
            }
        }
    });

</script>
<style>

</style>
</body>
</html>