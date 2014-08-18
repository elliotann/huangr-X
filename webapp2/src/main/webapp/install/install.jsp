<%@ page import="com.easysoft.core.common.SysParamSetting" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String runmode = SysParamSetting.runmode;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Gebo Admin Panel</title>

    <!-- Bootstrap framework -->
    <link href="/jeap/install/css/bootstrap.min.css" rel="stylesheet">

    <!-- wizard -->
    <link href="/jeap/install/css/jquery.stepy.css" rel="stylesheet">

    <link href="/jeap/install/css/style.css" rel="stylesheet">
    <!-- theme color-->
    <link id="link_theme" href="/jeap/install/css/blue.css" rel="stylesheet">

    <![endif]-->

</head>
<body>
<script>
    function finnish(){
        alert($(dbName).val());
    }
</script>

<div class="clearfix" id="maincontainer">
    <header>




    </header>




    <div class="row">
        <div class="col-sm-12 col-md-12">
            <h3 class="heading">安装向导</h3>

            <div class="row">
                <div class="col-sm-12 col-md-12">

                    <form class="stepy-wizzard form-horizontal" id="validate_wizard" novalidate="novalidate">
                        <fieldset title="第一步" class="step" id="validate_wizard-step-0">
                            <legend class="hide">[jeap]最终协议…</legend>
                            <div class="formSep form-group">
                                1、本软件的所有权归jeap作者
                            </div>
                            <div class="formSep form-group">
                                2、此软件为开源软件，可用于研究学习用，未经作者允许不得用于商业用途
                            </div>
                            <div class="form-group">

                                <label class="col-md-2 control-label" for="readed"></label>
                                <div class="col-md-10">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="readed" name="readed" value="option2"/>我已仔细阅读，并同意上述条款中的所有内容
                                    </label>
                                </div>
                            </div>
                            <p class="validate_wizard-buttons" id="validate_wizard-buttons-0"></p></fieldset>
                        <fieldset title="第二步"
                                  style="display: none;">
                            <legend class="hide">数据库配置…</legend>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="dbType">数据库类型:</label>

                                <div class="col-md-10">
                                    <select class="form-control" name="dbType" id="dbType">
                                        <option value="mysql">MySQL</option>
                                        <option value="oracle">Oracle</option>
                                        <option value="sqlserver">SQLServer</option>
                                    </select>

                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="dbHost">数据库主机:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="dbHost" name="dbHost" value="localhost:3306">

                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="db_uname">数据库用户名:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="db_uname"
                                           name="db_uname">警告！如果您指定的数据库名称已存在，此安装有可能会破坏原有库中的数据！
                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="db_passwd">数据库密码:</label>

                                <div class="col-md-10">
                                    <input type="password" class="input-sm form-control" id="db_passwd"
                                           name="db_passwd">
                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="dbHost">数据库名:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="dbName" name="dbName">
                                </div>
                            </div>
                            <% if("2".equals(runmode)){ %>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="domain">域名:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="domain" name="domain">
                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="solutionpath">解决方案磁盘目录:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="solutionpath" name="solutionpath">
                                </div>
                            </div>

                            <%} %>

                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="solutionpath">模式:</label>

                                <div class="col-md-10">
                                    <label class="radio-inline">
                                        <input name="devmodel" type="radio" value="1" checked="true" id="dev_yes">
                                        开发模式
                                    </label>
                                    <label class="radio-inline">
                                        <input  name="devmodel" type="radio" id="dev_no" value="0">
                                        上线运行模式
                                    </label>
                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="solutionpath">静态资源分离:</label>

                                <div class="col-md-10">
                                    <label class="radio-inline">
                                        <input name="resourcemode" type="radio" value="2" checked="true" id="res_no">
                                        否
                                    </label>
                                    <label class="radio-inline">
                                        <input  name="resourcemode" type="radio" id="res_yes" value="1">
                                        是
                                    </label>
                                </div>
                            </div>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="staticpath">静态服务磁盘目录:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="staticpath" name="staticpath">默认静态资源将被安装至此目录
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label" for="staticdomain">静态服务域名:</label>

                                <div class="col-md-10">
                                    <input type="text" class="input-sm form-control" id="staticdomain" name="staticdomain">使此域名可访问上述的"静态服务磁盘目录
                                </div>
                            </div>
                            <p class="validate_wizard-buttons" id="validate_wizard-buttons-1"></p></fieldset>
                        <fieldset title="第三步" class="step" id="validate_wizard-step-2"
                                  style="display: none;">
                            <legend class="hide">完成安装…</legend>
                            <div class="formSep form-group">
                                <label class="col-md-2 control-label" for="v_message">Your Message:</label>

                                <div class="col-md-10">
                                    <textarea class="form-control" rows="3" id="v_message"
                                              name="v_message"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Newsletter:</label>

                                <div class="col-md-10">
                                    <label for="newsletter_yes" class="radio-inline">
                                        <input type="radio" name="v_newsletter" id="newsletter_yes" value="yes"> Yes
                                    </label>
                                    <label for="newsletter_no" class="radio-inline">
                                        <input type="radio" name="v_newsletter" id="newsletter_no" value="no"> No
                                    </label>
                                </div>
                            </div>
                            <p class="validate_wizard-buttons" id="validate_wizard-buttons-2">
                                <button class="finish btn btn-primary" type="button" onclick="finnish()">Send registration</button>
                            </p>

                        </fieldset>
                        <div class="finish" style="display: none"></div>
                        <div class="stepy-error"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>




    <script src="/jeap/install/css/jquery.min.js"></script>
    <!-- validation -->
    <script src="/jeap/install/css/jquery.validate.min.js"></script>
    <!-- wizard -->
    <script src="/jeap/install/css/jquery.stepy.js"></script>
    <!-- wizard functions -->
    <script src="/jeap/install/css/install.js"></script>






</div>



</body>
</html>
