<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/7/8
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/style.css"/>
    <script src="../../js/common/jquery-1.8.3.js"></script>
    <script src="../../adminthemes/default/js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/jquery.flot.min.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/general.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/dashboard.js" type="text/javascript"></script>

</head>
<body style="background-color: #EEEEEE;">


<form method="post" action="" id="form" novalidate="novalidate">

    <div class="form_default">
        <fieldset>
            <legend>增加管理员</legend>

            <p>
                <label for="name">Name</label>

                <input type="text" class="sf" id="name" name="name">
            </p>

            <p>
                <label for="email">Email</label>
                <input type="text" class="sf" id="email" name="email">
            </p>

            <p>
                <label for="location">Location</label>

                <textarea rows="" cols="" class="mf" name="location"></textarea>
            </p>

            <p>
                <label class="nopadding" for="gender">Gender</label>
                <input type="radio" value="0" name="gender"> Male &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="1" name="gender"> Female
            </p>


            <p>
                <label class="nopadding" for="language">Language</label>
                <input type="checkbox" value="0" name="language[]"> English &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox" value="1" name="language[]"> Mandarin &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox" value="1" name="language[]"> German
            </p>

            <p>

                <label for="occupation">Occupation</label>
                <select id="occupation" name="occupation">
                    <option value="">Choose One</option>
                    <option value="0">Web Designer</option>
                    <option value="1">Web Developer</option>
                    <option value="2">Software Engineer</option>

                    <option value="3">Application Engineer</option>
                    <option value="4">Programmer</option>
                    <option value="5">Analyst</option>

                </select>
            </p>

            <p>
                <button>Submit</button>

            </p>

        </fieldset>
    </div><!--form-->


</form>
</body>
</html>
