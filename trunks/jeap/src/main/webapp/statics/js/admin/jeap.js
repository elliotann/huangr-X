/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-23
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
var Jeap=Jeap||{};
$.ajaxSetup({ cache:false });
Jeap.AdminUI={
    opts:undefined,
    init:function(opations){

        if(typeof opations=="string") {
            this.opts ={};
            this.opts.wrapper = $( opations);

        }

        if(typeof opations=="object"){
            this.opts = opations;
        }

    },

    load:function(link){
        Jeap.Help.close();
        if(Jeap.onRedirect){
            if(!Jeap.onRedirect()){
                return false;
            }
            Jeap.onRedirect=undefined;
        }
        var target = link.attr("target");
        var url = link.attr("href");
        if(target){
            if(target=="_self"){
                location.href=url;
                return true;
            }
            if(target=='ajax'){
                url = url.replace('http://'+location.hostname,'');
                url = url.replace(':'+location.port,'');
                this.loadUrl(url);
                return false;
            }

            if(target=='iframe'){
                this.loadUrlInFrm(url);
                return false;
            }
            if(target=='_blank'){
                return true;
            }
            if(target=='_top'){
                return true;
            }
            this.loadUrlInFrm(url);


        }else{
            this.loadUrlInFrm(url);
        }
        return false;
    }
    ,
    loadUrlInFrm:function(url){
        $.Loading.show('正在加载所需内容，请稍侯...');
        this.opts.wrapper.empty();


        url= url+(url.indexOf("?") == -1 ? "?" : "&") + "_="+(new Date()).getTime();

        this.opts.frm   =$("<iframe id='auto_created_frame' width='100%' height='100%' frameborder='0' ></iframe>");

        this.opts.frm.appendTo( this.opts.wrapper ).load(function(){
            $.Loading.hide();
        });
        this.opts.frm.attr("src",url);
    }
    ,
    loadUrl:function(url,fun){
        //alert("load:"+url);
        $.Loading.show('正在加载所需内容，请稍侯...');
        basePath= url.substring(0,url.lastIndexOf("/")+1);
        //alert("basePath is " + basePath);
        var self =this;
        $.ajax({
            type: "GET",
            url: url,
            data:"ajax=yes&rmd="+ new Date().getTime(),
            dataType:"html",
            success: function(html){
                self.opts.wrapper.empty().append($(html));
                if(fun) fun();
                self.requestProxy();
                $.Loading.hide();
                Jeap.Help.init();
            },error:function(e){

                //alert("出错了:(" +e.status);
                $.Loading.hide();
            }
        });
    },
    requestProxy:function(){
        var self =this;

        //代理a连接
        this.opts.wrapper.find("a").click(function(){
            if($(this).attr("target") == '_blank' ) return true;
            if($(this).attr("target") == '_top' ) return true;
            var href=$(this).attr("href");
            href = href.substring( href.lastIndexOf("/")+1, href.length );

            //alert("href1:"+href);
            if(href!="javascript:;"){
                loadUrl(href);
                return false;
            }
            return true;
        });

        //代理表单提交
        var $form = this.opts.wrapper.find("form");
        $form.append("<input type='hidden' name='ajax' value='yes' />");
        $form.submit(function(){
            if("false"==$(this).attr("validate")) return false;
            var method = $(this).attr("method");
            if(!method) method="POST";

            $.Loading.show('正在提交数据...');
            var options = {
                url :basePath+$form.attr("action"),
                type : method,
                dataType : 'html',
                success : function(result) {
                    self.opts.wrapper.html(result);
                    self.requestProxy();
                    Jeap.onRedirect=undefined;
                    $.Loading.hide();
                    $.Loading.text = undefined;
                },
                error : function(e) {
                    //alert("出错啦:(");
                    $.Loading.hide();
                }
            };

            $(this).ajaxSubmit(options);
            return false;
        });



    }
};

//help开始

Jeap.Help={
    init:function(){
        $("#HelpClose ").click(function(){
            $("#HelpCtn").hide();
        });
        $(".help_icon").click(function(e){
            var helpid = $(this).attr("helpid");
            var y =e.pageY-22;
            $("#HelpCtn").css("top",y).css("left",e.pageX+10).show();
            $("#HelpBody").html("正在加载...");
            $("#HelpBody").load(app_path+"/core/admin/help.do?ajax=yes&helpid="+helpid);
        });
    },
    close:function(){
        $("#HelpCtn").hide();
    }

};
//弹出对话框
Jeap.Dialog={

    defaults: {
        //    height: 400,
        modal:true
    },
    init:function(options){
        this.opts = $.extend({}, this.defaults, options);

        var self=this;

        if( $("#dlg_"+self.opts.id).size()==0){


            var html ='<div  class="dialog" style="display:none;"><div class="dialog_box">';
            html+='<div class="head">';
            html+='<div class="title">'+this.opts.title+'</div>';
            html+='<span class="closeBtn"></span>';
            html+='</div>';
            html+='<div class="body dialogContent"></div>';
            //html+='<img style="cursor: nw-resize; position: absolute; bottom: 1px; right: 1px;" src="images/resize-btn.gif" class="resizeBtn"></div>';
            html+='</div>';

            self.dialog=$(html);
            self.dialog.appendTo('body');
            self.dialog.attr("id","dlg_"+self.opts.id);
        }else{
            self.dialog =$("#dlg_"+self.opts.id);
        }
        self.dialog.css('width',self.opts.width);
        //	if(self.opts.height) self.dialog.css('height',self.opts.height);
        self.dialog.find(".dialogContent").empty().append($("#"+self.opts.id));
        self.dialog.jqDrag('.head')
            .jqm(this.opts)
            .jqmAddClose('.closeBtn');
    },
    open:function(id){
        if(id){
            $("#dlg_"+id).jqmShow();
        }
        else{
            this.dialog.jqmShow();
        }
    },
    close:function(id){
        if(id){
            $("#dlg_"+id).jqmHide();
            //$("#dlg_"+id).remove();
        }
        else{
            this.dialog.jqmHide();
        }
    }
};

//提示框
(function($){

    function create(){
        var loadding=$("#loading");
        if(loadding.size()==0)
            loadding = $("<div id=\"loading\" class=\"loading\" style='z-index:999999' />").appendTo($("body")).hide();
        return loadding;
    }

    $.Loading = $.Loading || {};
    $.Loading.show=function(text){
        var loading = create();

        if(this.text){
            loading.html(this.text);
        }else{
            if(text)
                loading.html(text);
        }

        $("<div style=\"height: 100%; width: 100%; position: fixed; left: 0pt; top: 0pt; z-index: 2999; opacity: 0.4;\" class=\"jqmOverlay\"></div>").appendTo($("body"));
        loading.show();
    };

    $.Loading.hide=function(){
        create().hide();
        $(".jqmOverlay").remove();
    };


})(jQuery);

(function($){
    $.fn.jqDrag=function(h){return i(this,h,'d');};
    $.fn.jqResize=function(h){return i(this,h,'r');};
    $.jqDnR={dnr:{},e:0,
        drag:function(v){
            if(M.k == 'd')E.css({left:M.X+v.pageX-M.pX,top:M.Y+v.pageY-M.pY});
            else E.css({width:Math.max(v.pageX-M.pX+M.W,0),height:Math.max(v.pageY-M.pY+M.H,0)});
            return false;},
        stop:function(){E.css('opacity',M.o);$().unbind('mousemove',J.drag).unbind('mouseup',J.stop);}
    };
    var J=$.jqDnR,M=J.dnr,E=J.e,
        i=function(e,h,k){return e.each(function(){h=(h)?$(h,e):e;
            h.bind('mousedown',{e:e,k:k},function(v){var d=v.data,p={};E=d.e;
                // attempt utilization of dimensions plugin to fix IE issues
                if(E.css('position') != 'relative'){try{E.position(p);}catch(e){}}
                M={X:p.left||f('left')||0,Y:p.top||f('top')||0,W:f('width')||E[0].scrollWidth||0,H:f('height')||E[0].scrollHeight||0,pX:v.pageX,pY:v.pageY,k:d.k,o:E.css('opacity')};
                E.css({opacity:0.8});$().mousemove($.jqDnR.drag).mouseup($.jqDnR.stop);
                return false;
            });
        });},
        f=function(k){return parseInt(E.css(k))||false;};
})(jQuery);

(function($) {
    $.fn.jqm=function(o){
        var p={
            overlay: 50,
            overlayClass: 'jqmOverlay',
            closeClass: 'jqmClose',
            trigger: '.jqModal',
            ajax: F,
            ajaxText: '',
            target: F,
            modal: F,
            toTop: F,
            onShow: F,
            onHide: F,
            onLoad: F
        };
        return this.each(function(){if(this._jqm)return H[this._jqm].c=$.extend({},H[this._jqm].c,o);s++;this._jqm=s;
            H[s]={c:$.extend(p,$.jqm.params,o),a:F,w:$(this).addClass('jqmID'+s),s:s};
            if(p.trigger)$(this).jqmAddTrigger(p.trigger);
        });};

    $.fn.jqmAddClose=function(e){return hs(this,e,'jqmHide');};
    $.fn.jqmAddTrigger=function(e){return hs(this,e,'jqmShow');};
    $.fn.jqmShow=function(t){return this.each(function(){t=t||window.event;$.jqm.open(this._jqm,t);});};
    $.fn.jqmHide=function(t){return this.each(function(){t=t||window.event;$.jqm.close(this._jqm,t)});};

    $.jqm = {
        hash:{},
        open:function(s,t){var h=H[s],c=h.c,cc='.'+c.closeClass,z=(parseInt(h.w.css('z-index'))),z=(z>0)?z:3000,o=$('<div></div>').css({height:'100%',width:'100%',position:'fixed',left:0,top:0,'z-index':z-1,opacity:c.overlay/100});if(h.a)return F;h.t=t;h.a=true;h.w.css('z-index',z);
            if(c.modal) {if(!A[0])L('bind');A.push(s);}
            else if(c.overlay > 0)h.w.jqmAddClose(o);
            else o=F;

            h.o=(o)?o.addClass(c.overlayClass).prependTo('body'):F;
            if(ie6){$('html,body').css({height:'100%',width:'100%'});if(o){o=o.css({position:'absolute'})[0];for(var y in {Top:1,Left:1})o.style.setExpression(y.toLowerCase(),"(_=(document.documentElement.scroll"+y+" || document.body.scroll"+y+"))+'px'");}}

            if(c.ajax) {var r=c.target||h.w,u=c.ajax,r=(typeof r == 'string')?$(r,h.w):$(r),u=(u.substr(0,1) == '@')?$(t).attr(u.substring(1)):u;
                r.html(c.ajaxText).load(u,function(){if(c.onLoad)c.onLoad.call(this,h);if(cc)h.w.jqmAddClose($(cc,h.w));e(h);});}
            else if(cc)h.w.jqmAddClose($(cc,h.w));

            if(c.toTop&&h.o)h.w.before('<span id="jqmP'+h.w[0]._jqm+'"></span>').insertAfter(h.o);
            (c.onShow)?c.onShow(h):h.w.show();e(h);return F;
        },
        close:function(s){var h=H[s];if(!h.a)return F;h.a=F;
            if(A[0]){A.pop();if(!A[0])L('unbind');}
            if(h.c.toTop&&h.o)$('#jqmP'+h.w[0]._jqm).after(h.w).remove();
            if(h.c.onHide)h.c.onHide(h);else{h.w.hide();if(h.o)h.o.remove();} return F;
        },
        params:{}};
    var s=0,H=$.jqm.hash,A=[],ie6=$.browser.msie&&($.browser.version == "6.0"),F=false,
        i=$('<iframe src="javascript:false;document.write(\'\');" class="jqm"></iframe>').css({opacity:0}),
        e=function(h){if(ie6)if(h.o)h.o.html('<p style="width:100%;height:100%"/>').prepend(i);else if(!$('iframe.jqm',h.w)[0])h.w.prepend(i); f(h);},
        f=function(h){try{$(':input:visible',h.w)[0].focus();}catch(_){}},
        L=function(t){$()[t]("keypress",m)[t]("keydown",m)[t]("mousedown",m);},
        m=function(e){var h=H[A[A.length-1]],r=(!$(e.target).parents('.jqmID'+h.s)[0]);if(r)f(h);return !r;},
        hs=function(w,t,c){return w.each(function(){var s=this._jqm;$(t).each(function() {
            if(!this[c]){this[c]=[];$(this).click(function(){for(var i in {jqmShow:1,jqmHide:1})for(var s in this[i])if(H[this[i][s]])H[this[i][s]].w[i](this);return F;});}this[c].push(s);});});};
})(jQuery);

$.fn.ajaxSubmit = function(options) {
    // fast fail if nothing selected (http://dev.jquery.com/ticket/2752)
    if (!this.length) {
        log('ajaxSubmit: skipping submit process - no element selected');
        return this;
    }

    if (typeof options == 'function')
        options = { success: options };

    var url = $.trim(this.attr('action'));
    if (url) {
        // clean url (don't include hash vaue)
        url = (url.match(/^([^#]+)/)||[])[1];
    }
    url = url || window.location.href || '';

    options = $.extend({
        url:  url,
        type: this.attr('method') || 'GET'
    }, options || {});

    // hook for manipulating the form data before it is extracted;
    // convenient for use with rich editors like tinyMCE or FCKEditor
    var veto = {};
    this.trigger('form-pre-serialize', [this, options, veto]);
    if (veto.veto) {
        log('ajaxSubmit: submit vetoed via form-pre-serialize trigger');
        return this;
    }

    // provide opportunity to alter form data before it is serialized
    if (options.beforeSerialize && options.beforeSerialize(this, options) === false) {
        log('ajaxSubmit: submit aborted via beforeSerialize callback');
        return this;
    }

    var a = this.formToArray(options.semantic);
    if (options.data) {
        options.extraData = options.data;
        for (var n in options.data) {
            if(options.data[n] instanceof Array) {
                for (var k in options.data[n])
                    a.push( { name: n, value: options.data[n][k] } );
            }
            else
                a.push( { name: n, value: options.data[n] } );
        }
    }

    // give pre-submit callback an opportunity to abort the submit
    if (options.beforeSubmit && options.beforeSubmit(a, this, options) === false) {
        log('ajaxSubmit: submit aborted via beforeSubmit callback');
        return this;
    }

    // fire vetoable 'validate' event
    this.trigger('form-submit-validate', [a, this, options, veto]);
    if (veto.veto) {
        log('ajaxSubmit: submit vetoed via form-submit-validate trigger');
        return this;
    }

    var q = $.param(a);

    if (options.type.toUpperCase() == 'GET') {
        options.url += (options.url.indexOf('?') >= 0 ? '&' : '?') + q;
        options.data = null;  // data is null for 'get'
    }
    else
        options.data = q; // data is the query string for 'post'

    var $form = this, callbacks = [];
    if (options.resetForm) callbacks.push(function() { $form.resetForm(); });
    if (options.clearForm) callbacks.push(function() { $form.clearForm(); });

    // perform a load on the target only if dataType is not provided
    if (!options.dataType && options.target) {
        var oldSuccess = options.success || function(){};
        callbacks.push(function(data) {
            $(options.target).html(data).each(oldSuccess, arguments);
        });
    }
    else if (options.success)
        callbacks.push(options.success);

    options.success = function(data, status) {
        for (var i=0, max=callbacks.length; i < max; i++)
            callbacks[i].apply(options, [data, status, $form]);
    };

    // are there files to upload?
    var files = $('input:file', this).fieldValue();
    var found = false;
    for (var j=0; j < files.length; j++)
        if (files[j])
            found = true;

    var multipart = false;
//	var mp = 'multipart/form-data';
//	multipart = ($form.attr('enctype') == mp || $form.attr('encoding') == mp);

    // options.iframe allows user to force iframe mode
    if (options.iframe || found || multipart) {
        // hack to fix Safari hang (thanks to Tim Molendijk for this)
        // see:  http://groups.google.com/group/jquery-dev/browse_thread/thread/36395b7ab510dd5d
        if (options.closeKeepAlive)
            $.get(options.closeKeepAlive, fileUpload);
        else
            fileUpload();
    }
    else
        $.ajax(options);

    // fire 'notify' event
    this.trigger('form-submit-notify', [this, options]);
    return this;


    // private function for handling file uploads (hat tip to YAHOO!)
    function fileUpload() {
        var form = $form[0];

        if ($(':input[name=submit]', form).length) {
            alert('Error: Form elements must not be named "submit".');
            return;
        }

        var opts = $.extend({}, $.ajaxSettings, options);
        var s = $.extend(true, {}, $.extend(true, {}, $.ajaxSettings), opts);

        var id = 'jqFormIO' + (new Date().getTime());
        var $io = $('<iframe id="' + id + '" name="' + id + '" src="about:blank" />');
        var io = $io[0];

        $io.css({ position: 'absolute', top: '-1000px', left: '-1000px' });

        var xhr = { // mock object
            aborted: 0,
            responseText: null,
            responseXML: null,
            status: 0,
            statusText: 'n/a',
            getAllResponseHeaders: function() {},
            getResponseHeader: function() {},
            setRequestHeader: function() {},
            abort: function() {
                this.aborted = 1;
                $io.attr('src','about:blank'); // abort op in progress
            }
        };

        var g = opts.global;
        // trigger ajax global events so that activity/block indicators work like normal
        if (g && ! $.active++) $.event.trigger("ajaxStart");
        if (g) $.event.trigger("ajaxSend", [xhr, opts]);

        if (s.beforeSend && s.beforeSend(xhr, s) === false) {
            s.global && $.active--;
            return;
        }
        if (xhr.aborted)
            return;

        var cbInvoked = 0;
        var timedOut = 0;

        // add submitting element to data if we know it
        var sub = form.clk;
        if (sub) {
            var n = sub.name;
            if (n && !sub.disabled) {
                options.extraData = options.extraData || {};
                options.extraData[n] = sub.value;
                if (sub.type == "image") {
                    options.extraData[name+'.x'] = form.clk_x;
                    options.extraData[name+'.y'] = form.clk_y;
                }
            }
        }

        // take a breath so that pending repaints get some cpu time before the upload starts
        setTimeout(function() {
            // make sure form attrs are set
            var t = $form.attr('target'), a = $form.attr('action');

            // update form attrs in IE friendly way
            form.setAttribute('target',id);
            if (form.getAttribute('method') != 'POST')
                form.setAttribute('method', 'POST');
            if (form.getAttribute('action') != opts.url)
                form.setAttribute('action', opts.url);

            // ie borks in some cases when setting encoding
            if (! options.skipEncodingOverride) {
                $form.attr({
                    encoding: 'multipart/form-data',
                    enctype:  'multipart/form-data'
                });
            }

            // support timout
            if (opts.timeout)
                setTimeout(function() { timedOut = true; cb(); }, opts.timeout);

            // add "extra" data to form if provided in options
            var extraInputs = [];
            try {
                if (options.extraData)
                    for (var n in options.extraData)
                        extraInputs.push(
                            $('<input type="hidden" name="'+n+'" value="'+options.extraData[n]+'" />')
                                .appendTo(form)[0]);

                // add iframe to doc and submit the form
                $io.appendTo('body');
                io.attachEvent ? io.attachEvent('onload', cb) : io.addEventListener('load', cb, false);
                form.submit();
            }
            finally {
                // reset attrs and remove "extra" input elements
                form.setAttribute('action',a);
                t ? form.setAttribute('target', t) : $form.removeAttr('target');
                $(extraInputs).remove();
            }
        }, 10);

        var domCheckCount = 50;

        function cb() {
            if (cbInvoked++) return;

            io.detachEvent ? io.detachEvent('onload', cb) : io.removeEventListener('load', cb, false);

            var ok = true;
            try {
                if (timedOut) throw 'timeout';
                // extract the server response from the iframe
                var data, doc;

                doc = io.contentWindow ? io.contentWindow.document : io.contentDocument ? io.contentDocument : io.document;

                var isXml = opts.dataType == 'xml' || doc.XMLDocument || $.isXMLDoc(doc);
                log('isXml='+isXml);
                if (!isXml && (doc.body == null || doc.body.innerHTML == '')) {
                    if (--domCheckCount) {
                        // in some browsers (Opera) the iframe DOM is not always traversable when
                        // the onload callback fires, so we loop a bit to accommodate
                        cbInvoked = 0;
                        setTimeout(cb, 100);
                        return;
                    }
                    log('Could not access iframe DOM after 50 tries.');
                    return;
                }

                xhr.responseText = doc.body ? doc.body.innerHTML : null;
                xhr.responseXML = doc.XMLDocument ? doc.XMLDocument : doc;
                xhr.getResponseHeader = function(header){
                    var headers = {'content-type': opts.dataType};
                    return headers[header];
                };

                if (opts.dataType == 'json' || opts.dataType == 'script') {
                    // see if user embedded response in textarea
                    var ta = doc.getElementsByTagName('textarea')[0];
                    if (ta)
                        xhr.responseText = ta.value;
                    else {
                        // account for browsers injecting pre around json response
                        var pre = doc.getElementsByTagName('pre')[0];
                        if (pre)
                            xhr.responseText = pre.innerHTML;
                    }
                }
                else if (opts.dataType == 'xml' && !xhr.responseXML && xhr.responseText != null) {
                    xhr.responseXML = toXml(xhr.responseText);
                }
                data = $.httpData(xhr, opts.dataType);
            }
            catch(e){
                ok = false;
                $.handleError(opts, xhr, 'error', e);
            }

            // ordering of these callbacks/triggers is odd, but that's how $.ajax does it
            if (ok) {
                opts.success(data, 'success');
                if (g) $.event.trigger("ajaxSuccess", [xhr, opts]);
            }
            if (g) $.event.trigger("ajaxComplete", [xhr, opts]);
            if (g && ! --$.active) $.event.trigger("ajaxStop");
            if (opts.complete) opts.complete(xhr, ok ? 'success' : 'error');

            // clean up
            setTimeout(function() {
                $io.remove();
                xhr.responseXML = null;
            }, 100);
        };

        function toXml(s, doc) {
            if (window.ActiveXObject) {
                doc = new ActiveXObject('Microsoft.XMLDOM');
                doc.async = 'false';
                doc.loadXML(s);
            }
            else
                doc = (new DOMParser()).parseFromString(s, 'text/xml');
            return (doc && doc.documentElement && doc.documentElement.tagName != 'parsererror') ? doc : null;
        };
    };
};

$.fn.formToArray = function(semantic) {
    var a = [];
    if (this.length == 0) return a;

    var form = this[0];
    var els = semantic ? form.getElementsByTagName('*') : form.elements;
    if (!els) return a;
    for(var i=0, max=els.length; i < max; i++) {
        var el = els[i];
        var n = el.name;
        if (!n) continue;

        if (semantic && form.clk && el.type == "image") {
            // handle image inputs on the fly when semantic == true
            if(!el.disabled && form.clk == el) {
                a.push({name: n, value: $(el).val()});
                a.push({name: n+'.x', value: form.clk_x}, {name: n+'.y', value: form.clk_y});
            }
            continue;
        }

        var v = $.fieldValue(el, true);
        if (v && v.constructor == Array) {
            for(var j=0, jmax=v.length; j < jmax; j++)
                a.push({name: n, value: v[j]});
        }
        else if (v !== null && typeof v != 'undefined')
            a.push({name: n, value: v});
    }

    if (!semantic && form.clk) {
        // input type=='image' are not found in elements array! handle it here
        var $input = $(form.clk), input = $input[0], n = input.name;
        if (n && !input.disabled && input.type == 'image') {
            a.push({name: n, value: $input.val()});
            a.push({name: n+'.x', value: form.clk_x}, {name: n+'.y', value: form.clk_y});
        }
    }
    return a;
};

/**
 * Returns the value of the field element.
 */
$.fieldValue = function(el, successful) {
    var n = el.name, t = el.type, tag = el.tagName.toLowerCase();
    if (typeof successful == 'undefined') successful = true;

    if (successful && (!n || el.disabled || t == 'reset' || t == 'button' ||
        (t == 'checkbox' || t == 'radio') && !el.checked ||
        (t == 'submit' || t == 'image') && el.form && el.form.clk != el ||
        tag == 'select' && el.selectedIndex == -1))
        return null;

    if (tag == 'select') {
        var index = el.selectedIndex;
        if (index < 0) return null;
        var a = [], ops = el.options;
        var one = (t == 'select-one');
        var max = (one ? index+1 : ops.length);
        for(var i=(one ? index : 0); i < max; i++) {
            var op = ops[i];
            if (op.selected) {
                var v = op.value;
                if (!v) // extra pain for IE...
                    v = (op.attributes && op.attributes['value'] && !(op.attributes['value'].specified)) ? op.text : op.value;
                if (one) return v;
                a.push(v);
            }
        }
        return a;
    }
    return el.value;
};
$.fn.fieldValue = function(successful) {
    for (var val=[], i=0, max=this.length; i < max; i++) {
        var el = this[i];
        var v = $.fieldValue(el, successful);
        if (v === null || typeof v == 'undefined' || (v.constructor == Array && !v.length))
            continue;
        v.constructor == Array ? $.merge(val, v) : val.push(v);
    }
    return val;
};