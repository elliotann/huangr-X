(function($){
var myflow = $.myflow;

$.extend(true,myflow.config.rect,{
	attr : {
	r : 8,
	fill : '#F6F7FF',
	stroke : '#03689A',
	"stroke-width" : 2
},margin:0
});

$.extend(true,myflow.config.props.props,{
    key : {name:'key', label:'流程标识', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	name : {name:'name', label:'名称', value:'新建流程', editor:function(){return new myflow.editors.inputEditor();}},
	desc : {name:'desc', label:'文档描述', value:'', editor:function(){return new myflow.editors.inputEditor();}}
});


$.extend(true,myflow.config.tools.states,{
	start : {
				showType: 'image',
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'开始'},
				img : {src : 'img/48/start_event_empty.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
                    key: {name:'key', label : 'ID', value:'',"width":130, editor: function(){return new myflow.editors.inputEditor();}},
                    name: {name:'name',label: '名称', value:'',"width":130, editor: function(){return new myflow.editors.textEditor();}, value:'开始'},
                    desc: {name:'desc', label : '描述',"width":130, value:'', editor: function(){return new myflow.editors.inputEditor();}},
                    initiator: {name:'initiator', label : 'initiator',"width":130, value:'', editor: function(){return new myflow.editors.inputEditor();}}
				}},
			end : {showType: 'image',type : 'end',
				name : {text:'<<end>>'},
				text : {text:'结束'},
				img : {src : 'img/48/end_event_terminate.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
                    key: {name:'key', label : 'ID', value:'',"width":130, editor: function(){return new myflow.editors.inputEditor();}},
                    name: {name:'name',label: '名称', value:'',"width":130, editor: function(){return new myflow.editors.textEditor();}, value:'结束'},
                    desc: {name:'desc', label : '描述',"width":130, value:'', editor: function(){return new myflow.editors.inputEditor();}}
				}},
			'end-cancel' : {showType: 'image',type : 'end-cancel',
				name : {text:'<<end-cancel>>'},
				text : {text:'取消'},
				img : {src : 'img/48/end_event_cancel.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'取消'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			'end-error' : {showType: 'image',type : 'end-error',
				name : {text:'<<end-error>>'},
				text : {text:'错误'},
				img : {src : 'img/48/end_event_error.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'错误'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			state : {showType: 'text',type : 'state',
				name : {text:'<<state>>'},
				text : {text:'状态'},
				img : {src : 'img/48/task_empty.png',width : 48, height:48},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'状态'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			fork : {showType: 'image',type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'分支'},
				img : {src : 'img/48/gateway_parallel.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'分支'},
					temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('/jeap/workflow/js/myflow/select.json');}}
				}},
			join : {showType: 'image',type : 'join',
				name : {text:'<<join>>'},
				text : {text:'合并'},
				img : {src : 'img/48/gateway_parallel.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'合并'},
					temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('/jeap/workflow/js/myflow/select.json');}}
				}},
			task : {showType: 'text',type : 'task',
				name : {text:'<<task>>'},
				text : {text:'任务'},
				img : {src : 'img/48/task_empty.png',width :48, height:48},
				props : {
                    key: {name:'key', label : 'ID', value:'',"width":130, editor: function(){return new myflow.editors.inputEditor();}},
                    name: {name:'name',label: '名称', value:'',"width":130, editor: function(){return new myflow.editors.textEditor();}, value:'开始'},
                    desc: {name:'desc', label : '描述',"width":130, value:'', editor: function(){return new myflow.editors.inputEditor();}},
					assignee: {name:'assignee', label: '用户', value:'', editor: function(){return new myflow.editors.selectEditor('/jeap/workflow/js/myflow/select.json');}}

				}},
			decision : {showType: 'image',type : 'decision',
                name : {text:'<<decision>>'},
                text : {text:'决定'},
                img : {src : 'img/48/gateway_parallel.png',width :48, height:48},
                attr : {width:50 ,heigth:50 },
                props : {
                    text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'决定'},
                    expr: {name:'expr', label : '表达式', value:'', editor: function(){return new myflow.editors.inputEditor();}},
                    desc: {name:'desc', label : '描述', value:'', editor: function(){return new myflow.editors.inputEditor();}}
                }}
});
})(jQuery);