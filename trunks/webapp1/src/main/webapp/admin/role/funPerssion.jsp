<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/index.css" />
</head>
<body>
<div id="wrapper">

    <div id="navigator">
        <iframe src="tree.jsp"></iframe>
    </div>
    <div id="main">
        <iframe name="MainFrame" src="main.html"></iframe>
    </div>

</div>
</body>
<script type="text/javascript">
    function screenAdapter(){

        document.getElementById('navigator').style.height=document.documentElement.clientHeight-100+"px";
        document.getElementById('main').style.height=document.documentElement.clientHeight-100+"px";
        document.getElementById('main').style.width=window.screen.width-500+"px";
    }

    window.onscroll=function(){screenAdapter()};
    window.onresize=function(){screenAdapter()};
    window.onload=function(){screenAdapter()};
</script>
</html>
