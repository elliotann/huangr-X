<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>


<h3 class="heading">Wizard with validation</h3>

<div class="row">
    <div class="col-sm-12 col-md-12">
        <ul class="stepy-titles clearfix" id="validate_wizard-titles">
            <li class="current-step" id="validate_wizard-title-0">
                <div>第一步</div>
                <span>【jeap】最终用户授权协议…</span><span class="stepNb">1</span></li>
            <li id="validate_wizard-title-1">
                <div>第二步</div>
                <span>配置数据库信息…</span><span class="stepNb">2</span></li>
            <li id="validate_wizard-title-2">
                <div>Additional info</div>
                <span>Lorem ipsum dolor…</span><span class="stepNb">3</span></li>
        </ul>
        <form novalidate="novalidate" id="validate_wizard" class="stepy-wizzard form-horizontal">
            <fieldset id="validate_wizard-step-0" class="step" title="">
                <legend class="hide">【jeap】最终用户授权协议</legend>
                <div class="formSep form-group">
                    1:easysoft工作室
                </div>
                <div class="formSep form-group">
                    1:easysoft工作室
                </div>
                <div class="form-group">
                    1:easysoft工作室
                </div>
                <div class="formSep form-group">
                    <label for="v_street" class="col-md-2 control-label">Street Address:</label>

                    <div class="col-md-10">
                        <input name="v_street" id="v_street" class="input-sm form-control" type="text">
                    </div>
                </div>
                <p id="validate_wizard-buttons-0" class="validate_wizard-buttons">
                    <a class="btn btn-default button-next" href="javascript:void(0);" id="validate_wizard-next-0">下一步<i class="glyphicon glyphicon-chevron-right"></i></a></p></fieldset>
            <fieldset style="display: none;" id="validate_wizard-step-1" class="step" title="">
                <legend class="hide">Lorem ipsum dolor…</legend>
                <div class="formSep form-group">
                    <label for="v_street" class="col-md-2 control-label">Street Address:</label>

                    <div class="col-md-10">
                        <input name="v_street" id="v_street" class="input-sm form-control" type="text">
                    </div>
                </div>
                <div class="formSep form-group">
                    <label for="v_city" class="col-md-2 control-label">City:</label>

                    <div class="col-md-10">
                        <input name="v_city" id="v_city" class="input-sm form-control" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label for="v_country" class="col-md-2 control-label">Country:</label>

                    <div class="col-md-10">
                        <input name="v_country" id="v_country" class="input-sm form-control" type="text">
                    </div>
                </div>
                <p id="validate_wizard-buttons-1" class="validate_wizard-buttons"><a class="btn btn-link button-back"
                                                                                     href="javascript:void(0);"
                                                                                     id="validate_wizard-back-1"><i
                        class="glyphicon glyphicon-chevron-left"></i> Backward</a><a class="btn btn-default button-next"
                                                                                     href="javascript:void(0);"
                                                                                     id="validate_wizard-next-1">Forward
                    <i class="glyphicon glyphicon-chevron-right"></i></a></p></fieldset>
            <fieldset style="display: none;" id="validate_wizard-step-2" class="step" title="">
                <legend class="hide">Lorem ipsum dolor…</legend>
                <div class="formSep form-group">
                    <label for="v_message" class="col-md-2 control-label">Your Message:</label>

                    <div class="col-md-10">
                        <textarea name="v_message" id="v_message" rows="3" class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">Newsletter:</label>

                    <div class="col-md-10">
                        <label class="radio-inline" for="newsletter_yes">
                            <input value="yes" id="newsletter_yes" name="v_newsletter" type="radio"> Yes
                        </label>
                        <label class="radio-inline" for="newsletter_no">
                            <input value="no" id="newsletter_no" name="v_newsletter" type="radio"> No
                        </label>
                    </div>
                </div>
                <p id="validate_wizard-buttons-2" class="validate_wizard-buttons"><a class="btn btn-link button-back"
                                                                                     href="javascript:void(0);"
                                                                                     id="validate_wizard-back-2"><i
                        class="glyphicon glyphicon-chevron-left"></i> Backward</a>
                    <button type="button" class="finish btn btn-primary"><i class="glyphicon glyphicon-ok"></i> Send
                        registration
                    </button>
                </p>
            </fieldset>

            <div class="stepy-error"></div>
        </form>
    </div>
</div>


<h5>【JEAP】最终用户授权协议</h5>

<form method="post">
    <div style="height: 17em; border: 1px solid rgb(204, 204, 204); margin-bottom: 8px; padding: 5px; background: none repeat scroll 0% 0% rgb(255, 255, 255);line-height:20px; overflow-x: hidden; overflow-y: scroll;">
        <div style="float:right">easysoft工作室</div>
    </div>
    <table border="0" width="100%">
        <tbody>
        <tr>
            <td align="center">
                <input type="checkbox" id="readed"/>我已仔细阅读，并同意上述条款中的所有内容
            </td>
        </tr>
        <tr>
            <td align="center"><a id="donext" href="install.${ext }?step2">下一步：配置数据库信息</a></td>
        </tr>
        </tbody>
    </table>
</form>
<script type="text/javascript">
    $(function () {
        $("#donext").click(function () {
            if (!document.getElementById("readed").checked) {
                alert("您需仔细查看并同意协议内容，才能继续操作！");
                return false;
            }
        });
    });
</script>
<jsp:include page="footer.jsp"></jsp:include>