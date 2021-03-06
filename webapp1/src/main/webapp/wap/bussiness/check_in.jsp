<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
    <script type="text/javascript" src="../js/common/jquery.min.js"></script>
    <script type="text/javascript">
        var $ = jQuery.noConflict();
        $(function() {
            $('#box_login').animate({'top':'65px'},500);
            $('#activator').click(function(){
                $('#box').animate({'top':'65px'},500);
            });
            $('#boxclose').click(function(){
                $('#box').animate({'top':'-400px'},500);
            });
            $('#activator_share').click(function(){
                $('#box_share').animate({'top':'65px'},500);
            });
            $('#boxclose_share').click(function(){
                $('#box_share').animate({'top':'-400px'},500);
            });
            $('#activator_login').click(function(){
                $('#box_login').animate({'top':'65px'},500);
            });
            $('#boxclose_login').click(function(){
                $('#box_login').animate({'top':'-400px'},500);
            });

        });
        $(document).ready(function(){
            $(".toggle_container").hide();
            $(".trigger").click(function(){
                $(this).toggleClass("active").next().slideToggle("slow");
                return false;
            });

        });
    </script>
    <!-- Hide Mobiles Browser Navigation Bar -->
    <script type="text/javascript">
        window.addEventListener("load",function() {
            // Set a timeout...
            setTimeout(function(){
                // Hide the address bar!
                window.scrollTo(0, 1);
            }, 0);
        });
    </script>
</head>
<body>
<div id="main_container">
    <div class="header">
        <a href="main.do" class="left_bt">首页</a>
        <span>登记</span>
        <a href="#" class="right_bt" id="activator"><img src="images/search.png" alt="" title="" border="0" /></a>
    </div>

    <div class="box" id="box">
        <div class="box_content">

            <div class="box_content_tab">
                Search
            </div>

            <div class="box_content_center">
                <div class="form_content">
                    <form>
                        <input type="text" class="form_input_box" />
                        <a class="boxclose" id="boxclose">Close</a>
                        <input type="submit" class="form_submit" value="Submit" />
                    </form>
                </div>

                <div class="clear"></div>
            </div>

        </div>
    </div>

    <div class="box" id="box_share">
        <div class="box_content">
            <div class="box_content_tab">
                Social Share
            </div>
            <div class="box_content_center">

                <div class="social_share">
                    <ul>
                        <li><a href="#"><img src="images/social/rss.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/google.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/twitter.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/delicious.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/digg.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/linkedin.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/facebook.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/reddit.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/myspace.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/technorati.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/stumbleupon.png" alt="" title="" border="0" /></a></li>
                        <li><a href="#"><img src="images/social/flickr.png" alt="" title="" border="0" /></a></li>
                    </ul>
                </div>

                <a class="boxclose_right" id="boxclose_share">close</a>
                <div class="clear"></div>

            </div>
        </div>
    </div>

    <div class="box" id="box_login">
        <div class="box_content">
            <div class="box_content_tab">
                注册
            </div>

            <div class="box_content_center">
                <div class="form_content">
                    <form action="#">
                        <label>微信号</label>
                        <input type="text" class="form_input_box" />
                        <label>人员名称</label>
                        <input type="text" class="form_input_box" />
                        <label>所属大区</label>
                        <input type="text" class="form_input_box" />
                        <label>手机号码</label>
                        <input type="text" class="form_input_box" />
                        <a class="boxclose" id="boxclose_login">关闭</a>
                        <input type="submit" class="form_submit" value="提交" />
                    </form>
                </div>
                <div class="clear"></div>
            </div>



        </div>
    </div>


    <div class="content">

        <div class="toogle_wrap">
            <div class="trigger" id="attendList"><a href="#">签到列表</a></div>
            <div class="toggle_container">
                <ul>
                    <li><a href="#">店铺001在2014-06-30在深圳进行签到</a><a class="right_bt">登记</a></li>
                    <li><a href="#">店铺002在2014-06-30在东莞进行签到</a><a class="right_bt">登记</a></li>
                </ul>
            </div>
        </div>
        <div class="shadow_wrap"></div>




    </div>

</div>
<div id="footer">

    <span>jeap</span>
    <a onclick="jQuery('html, body').animate( { scrollTop: 0 }, 'slow' );"  href="javascript:void(0);" title="Go on top" class="right_bt"><img src="images/top.png" alt="" title="" border="0" /></a>
</div>
</body>
</html>
