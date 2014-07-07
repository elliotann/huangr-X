<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../adminthemes/default/css/main.css" />
    <link rel="stylesheet" type="text/css" href="../adminthemes/default/css/menu.css" />
    <link rel="stylesheet" type="text/css" href="../adminthemes/default/css/tab.css" />
    <link type="text/css" rel="stylesheet" href="../adminthemes/default/css/workspace.css" />
    <link type="text/css" rel="stylesheet" href="../adminthemes/default/css/welcome.css" />


    <script type="text/javascript" src="../js/common/jquery.min.js"></script>
    <script type="text/javascript" src="../adminthemes/default/js/tab-control.js"></script>
</head>
<body>

    <div  class="navbar">
        <div class="navbar-inner">
            <div class="logo"><a href="#">logo</a><span class="version">v${version}</span></div>
            <div id="short_msg"  >
                您好${user.username }, <span>您没有新短消息</span>
                <div class="msglist">
                    <ul></ul>
                </div>
            </div>
        </div>
    </div>
    <div id="navigator">
        <div class="msgContent">您好admin, <span>您没有新短消息</span></div>
        <div id="leftMenu">
            <ul class="menu">
                <li class="item1" id="one"><a href="#one">权限管理 <span>340</span></a>
                    <ul>
                        <li class="subitem1"><a href="fsadfas.do">管理员管理 <span>14</span></a></li>
                        <li class="subitem2"><a href="#">权限管理 <span>6</span></a></li>
                    </ul>
                </li>
                <li class="item2" id="two"><a href="#two">Videos <span>147</span></a>
                    <ul>
                        <li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
                        <li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
                        <li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
                        <li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
                        <li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
                        <li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
                    </ul>
                </li>
                <li class="item3" id="three"><a href="#three">Galleries <span>340</span></a>
                    <ul>
                        <li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
                    </ul>
                </li>
                <li class="item4" id="four"><a href="#four">Podcasts <span>222</span></a>
                    <ul>
                        <li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
                        <li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
                        <li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
                    </ul>
                </li>
                <li class="item5" id="five"><a href="#five">Robots <span>16</span></a>
                    <ul>
                        <li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
                        <li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
                        <li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>


    <div id="rightContent">
        <!-- 选项卡 -->
        <div class="tab-control">
            <!-- 标签 -->
            <div class="tab simple">
                <ul>
                    <li>管理员管理<a href="javascript:;">关闭</a></li>
                    <li>角色管理<a href="javascript:;">关闭</a></li>
                </ul>
            </div>
        </div>
        <div class="main">

            <iframe scrolling="auto" frameborder="0" src="../adminthemes/default/main.html"></iframe>

        </div>

    </div>

    <div id="footer">Copyright © 20014-2017 All Rights Reserved Powered By JEAP</div>

</body>
<script type="text/javascript" src="../js/common/jquery.min.js"></script>
<script type="text/javascript" src="../adminthemes/default/js/tabs.js"></script>
<script type="text/javascript">
    function screenAdapter(){
        document.getElementById('footer').style.top=document.documentElement.scrollTop+document.documentElement.clientHeight- document.getElementById('footer').offsetHeight+"px";
        document.getElementById('navigator').style.height=document.documentElement.clientHeight-150+"px";
        document.getElementById('rightContent').style.height=document.documentElement.clientHeight-150+"px";
        document.getElementById('rightContent').style.width=window.screen.width-230+"px";
    }

    window.onscroll=function(){screenAdapter()};
    window.onresize=function(){screenAdapter()};
    window.onload=function(){screenAdapter()};
</script>

</html>
