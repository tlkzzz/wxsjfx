// 代码整理：懒人之家 lanrenzhijia.com
//延时加载
function LazyLoad ()
{
	this.initialize.apply(this, arguments)	
}
LazyLoad.prototype = 
{
	initialize: function (obj)
	{
		var _this = this;
		this.lazy = typeof obj === "string" ? document.getElementById(obj) : obj;
		this._load = function () {return _this.load.apply(_this, arguments)};		
		this.load();
		this.addEvent(window, "scroll", this._load);
		this.addEvent(window, "resize", this._load);
	},
	pageX: function (element)
	{
		return element.offsetLeft + (element.offsetParent ? arguments.callee(element.offsetParent) : 0)
	},
	pageY: function (element)
	{
		return element.offsetTop + (element.offsetParent ? arguments.callee(element.offsetParent) : 0)	
	},
	addEvent: function (element, type, handler)
	{
		return element.addEventListener ? element.addEventListener(type, handler, false) : element.attachEvent("on" + type, handler)
	},
	load: function ()
	{
		var aTextArea = this.lazy.getElementsByTagName("textarea");		
		var iScrollTop = (document.documentElement.scrollTop || document.body.scrollTop);
		var iClientHeight = document.documentElement.clientHeight + iScrollTop;
		var i = 0;
		var aParent = [];
		if (!aTextArea[0]) return;	
		for (i = 0; i < aTextArea.length; i++)
		{
			var oParent = aTextArea[i].parentElement || aTextArea[i].parentNode;
			var iTop = this.pageY(oParent);
			var iBottom = iTop + oParent.offsetHeight;			
			if ((iTop > iScrollTop && iTop < iClientHeight) || (iBottom > iScrollTop && iBottom < iClientHeight))
			aParent.push(oParent)
		}
		for (i = 0; i < aParent.length; i++)
		aParent[i].innerHTML = aParent[i].getElementsByTagName("textarea")[0].value;
	}
};
//应用
/*window.onload = function ()
{
	var oLazy = document.getElementById("box");
	new LazyLoad(oLazy);
};*/

