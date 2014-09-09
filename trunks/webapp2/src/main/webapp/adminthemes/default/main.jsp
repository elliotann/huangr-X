<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${title }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <script type="text/javascript" src="menu.do"></script>
    <script>
        var founder= ${user.founder};
    </script>

    <link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/main.css" rel="stylesheet" type="text/css" />
    <link href="/jeap/install/css/bootstrap.min1.css" rel="stylesheet" type="text/css" />
    <link href="/jeap/install/css/style1.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>




    <script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
    <script type="text/javascript" src="${context}/js/index.js"></script>


    <style type="text/css">

        body{ padding:0px; margin:0;}

        .l-page-top{ height:80px; background:#f8f8f8; margin-bottom:3px;}
        h4{ margin:20px;}
    </style>
    <style type="text/css">
        /* 菜单列表 */
        .menulist { margin-left: 2px; margin-right: 2px; margin-top: 2px; text-align: left; color: #000; }
        .menulist li { height: 24px; line-height: 24px; padding-left: 24px; display: block; position: relative; cursor: pointer; text-align: left; }
        .menulist li img { position: absolute; left: 4px; top: 4px; width: 16px; height: 16px; }
        .menulist li.over, .menulist li.selected { background: url('${context}/images/menubg.gif') repeat-x 0px 0px; }
        .menulist li.over .menuitem-l, .menulist li.selected .menuitem-l { background: url('${context}/images/menubg.gif') repeat-x 0px -24px; width: 2px; height: 24px; position: absolute; left: 0; top: 0; }
        .menulist li.over .menuitem-r, .menulist li.selected .menuitem-r { background: url('${context}/images/menubg.gif') repeat-x -1px -24px; width: 2px; height: 24px; position: absolute; right: 0; top: 0; }
        #portrait { border-radius: 4px; box-shadow: 1px 1px 1px #111; position: absolute; width: 48px; height: 48px; right: 7px; top: 10px; background: #d2d2f2 /*url(images/icons/32X32/user.gif) no-repeat center center*/; border: 3px solid #fff; behavior: url(css/pie.htc); text-align: center; }

    </style>
</head>
<body class="bodygrey">
<input id="context" value="${context}" type="hidden"/>
<div class="header" id="top">
    <!-- logo -->
    <a href=""><img alt="Logo" src="images/logo2.png"></a>
    <div class="topheader">

    </div>
    <!-- topheader -->



    <!-- tabmenu start-->
    <div class="tabmenu">
        <ul>

        </ul>

    </div>
    <!-- tabmenu end-->

    <div class="accountinfo">
        <img alt="Avatar" src="images/avatar.png">

        <div class="info">
            <h3>${user.username }</h3>
            <small>360042212@qq.com</small>
            <p>
                <a href="">账号设置</a> <a href="../admin/logout.do" target="_self">退出</a>
            </p>
        </div>
        <!-- info -->
    </div>
    <!-- accountinfo -->
</div>
<div class="sidebar" style="height: 100%">
    <div style="position: relative; overflow: hidden; width: auto; height: 371px;" class="slimScrollDiv">
        <div style="overflow: hidden; width: auto; height: 371px;" class="sidebar_inner_scroll">
            <div style="margin-bottom: -92px; min-height: 100%;" class="sidebar_inner">

                <div id="side_accordion" class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#collapseOne" data-parent="#side_accordion" data-toggle="collapse"
                               class="accordion-toggle">
                                <i class="glyphicon glyphicon-folder-close"></i> Content
                            </a>
                        </div>
                        <div style="height: auto;" class="accordion-body collapse" id="collapseOne">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="javascript:void(0)">Articles</a></li>
                                    <li><a href="javascript:void(0)">News</a></li>
                                    <li><a href="javascript:void(0)">Newsletters</a></li>
                                    <li><a href="javascript:void(0)">Comments</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="javascript:void(0)" data-parent="#side_accordion" data-toggle="collapse"
                               class="accordion-toggle">
                                <i class="glyphicon glyphicon-th"></i> Modules
                            </a>
                        </div>
                        <div style="" class="accordion-body collapse" id="collapseTwo">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="javascript:void(0)">Content blocks</a></li>
                                    <li><a href="javascript:void(0)">Tags</a></li>
                                    <li><a href="javascript:void(0)">Blog</a></li>
                                    <li><a href="javascript:void(0)">FAQ</a></li>
                                    <li><a href="javascript:void(0)">Formbuilder</a></li>
                                    <li><a href="javascript:void(0)">Location</a></li>
                                    <li><a href="javascript:void(0)">Profiles</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#collapseThree" data-parent="#side_accordion" data-toggle="collapse"
                               class="accordion-toggle collapsed">
                                <i class="glyphicon glyphicon-user"></i> Account manager
                            </a>
                        </div>
                        <div style="height: 0px;" class="accordion-body collapse" id="collapseThree">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="javascript:void(0)">Members</a></li>
                                    <li><a href="javascript:void(0)">Members groups</a></li>
                                    <li><a href="javascript:void(0)">Users</a></li>
                                    <li><a href="javascript:void(0)">Users groups</a></li>
                                </ul>

                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#collapseFour" data-parent="#side_accordion" data-toggle="collapse"
                               class="accordion-toggle collapsed">
                                <i class="glyphicon glyphicon-cog"></i> Configuration
                            </a>
                        </div>
                        <div style="height: 0px;" class="accordion-body collapse" id="collapseFour">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li class="nav-header">People</li>
                                    <li class="active"><a href="javascript:void(0)">Account Settings</a></li>
                                    <li><a href="javascript:void(0)">IP Adress Blocking</a></li>
                                    <li class="nav-header">System</li>
                                    <li><a href="javascript:void(0)">Site information</a></li>
                                    <li><a href="javascript:void(0)">Actions</a></li>
                                    <li><a href="javascript:void(0)">Cron</a></li>
                                    <li class="divider"></li>
                                    <li><a href="javascript:void(0)">Help</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#collapseLong" data-parent="#side_accordion" data-toggle="collapse"
                               class="accordion-toggle collapsed">
                                <i class="glyphicon glyphicon-leaf"></i> Long content (scrollbar)
                            </a>
                        </div>
                        <div style="height: 0px;" class="accordion-body collapse" id="collapseLong">
                            <div class="panel-body">
                                Some text to show sidebar scroll bar<br>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus rhoncus, orci ac
                                fermentum imperdiet, purus sapien pharetra diam, at varius nibh tellus tristique sem.
                                Nulla congue odio ut augue volutpat congue. Nullam id nisl ut augue posuere ullamcorper
                                vitae eget nunc. Quisque justo turpis, tristique non fermentum ac, feugiat quis lorem.
                                Ut pellentesque, turpis quis auctor laoreet, nibh erat volutpat est, id mattis mi elit
                                non massa. Suspendisse diam dui, fringilla id pretium non, dapibus eget enim. Duis
                                fermentum quam a leo luctus tincidunt euismod sit amet arcu. Duis bibendum ultricies
                                libero sed feugiat. Duis ut sapien risus. Morbi non nulla sit amet eros fringilla
                                blandit id vel augue. Nam placerat ligula lacinia tellus molestie molestie vestibulum
                                leo tincidunt.
                                Duis auctor varius risus vitae commodo. Fusce nec odio massa, ut dapibus justo. Lorem
                                ipsum dolor sit amet, consectetur adipiscing elit. Curabitur dapibus, mauris sit amet
                                feugiat tempor, nulla diam gravida magna, in facilisis sapien tellus non ligula. Mauris
                                sapien turpis, sodales ac lacinia sit amet, porttitor in lacus. Pellentesque tincidunt
                                malesuada magna, in egestas augue sodales vel. Praesent iaculis sapien at ante sodales
                                facilisis.
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#collapse7" data-parent="#side_accordion" data-toggle="collapse"
                               class="accordion-toggle collapsed">
                                <i class="glyphicon glyphicon-th"></i> Calculator
                            </a>
                        </div>
                        <div style="height: 0px;" class="accordion-body collapse" id="collapse7">
                            <div class="panel-body">
                                <form name="Calc" id="calc">
                                    <div class="formSep input-group input-group-sm">
                                        <input class="form-control" name="Input" type="text">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" name="clear" value="c"
                                                onclick="Calc.Input.value = ''">
                                            <i class="glyphicon glyphicon-remove"></i>
                                        </button>
									</span>
                                    </div>
                                    <div class="form-group">
                                        <input class="btn form-control btn-default input-sm" name="seven" value="7"
                                               onclick="Calc.Input.value += '7'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="eight" value="8"
                                               onclick="Calc.Input.value += '8'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="nine" value="9"
                                               onclick="Calc.Input.value += '9'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="div" value="/"
                                               onclick="Calc.Input.value += ' / '" type="button">
                                    </div>
                                    <div class="form-group">
                                        <input class="btn form-control btn-default input-sm" name="four" value="4"
                                               onclick="Calc.Input.value += '4'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="five" value="5"
                                               onclick="Calc.Input.value += '5'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="six" value="6"
                                               onclick="Calc.Input.value += '6'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="times" value="x"
                                               onclick="Calc.Input.value += ' * '" type="button">
                                    </div>
                                    <div class="form-group">
                                        <input class="btn form-control btn-default input-sm" name="one" value="1"
                                               onclick="Calc.Input.value += '1'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="two" value="2"
                                               onclick="Calc.Input.value += '2'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="three" value="3"
                                               onclick="Calc.Input.value += '3'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="minus" value="-"
                                               onclick="Calc.Input.value += ' - '" type="button">
                                    </div>
                                    <div class="formSep form-group">
                                        <input class="btn form-control btn-default input-sm" name="dot" value="."
                                               onclick="Calc.Input.value += '.'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="zero" value="0"
                                               onclick="Calc.Input.value += '0'" type="button">
                                        <input class="btn form-control btn-default input-sm" name="DoIt" value="="
                                               onclick="Calc.Input.value = Math.round( eval(Calc.Input.value) * 1000)/1000"
                                               type="button">
                                        <input class="btn form-control btn-default input-sm" name="plus" value="+"
                                               onclick="Calc.Input.value += ' + '" type="button">
                                    </div>
                                    Contributed by <a class="external_link" target="_blank"
                                                      href="http://themeforest.net/user/maumao">maumao</a>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>

                <div style="height: 92px;" class="push"></div>
            </div>


        </div>


    </div>
</div>
<!--左侧菜单结束-->
<div class="maincontent">

    <!-- breadcrumbs -->
    <div class="left" id="right_content" >
        <iframe src="../core/admin/index.do?list" scrolling="no" id="iframe" name="iframe" style="width: 100%;height: 800px"></iframe>
    </div>
</div>


</body>
</html>

</html>