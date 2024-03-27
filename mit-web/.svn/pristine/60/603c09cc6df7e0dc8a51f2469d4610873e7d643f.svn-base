window.log = function f() {
	log.history = log.history || [];
	log.history.push(arguments);
	if (this.console) {
		var na;
		arguments.callee = arguments.callee.caller;
		na = [].slice.call(arguments);
		if (typeof console.log === "object") log.apply.call(console.log, console, na);
		else console.log.apply(console, na)
	}
};
(function (a) {
	function b() {}
	for (var c = "assert,count,debug,dir,dirxml,error,exception,group,groupCollapsed,groupEnd,info,log,markTimeline,profile,profileEnd,time,timeEnd,trace,warn".split(","), d; !! (d = c.pop());) a[d] = a[d] || b
})(function () {
	try {
		console.log();
		return window.console
	} catch (a) {
		return window.console = {}
	}
}());
(function ($) {
	$.fn.extend({
		resizeListener: function () {
			var getLayout = function (aWidth) {
					var theLayout = "UNK";
					if (aWidth <= 480) theLayout = "MOB";
					else if (aWidth > 480 && aWidth <= 768) theLayout = "720";
					else if (aWidth > 768 && aWidth <= 959) theLayout = "960";
					else if (aWidth > 960 && aWidth <= 1199) theLayout = "1000";
					else if (aWidth > 1199) theLayout = "1200";
					return theLayout
				};
			var notifyLayout = function (aWidth) {
					var pl = $("body").attr("layout");
					var cl = getLayout($(window).width());
					$(".width").html($(window).width() + " (" + cl + ")");
					if (pl != cl) {
						$("body").attr("layout", cl);
						$("body").trigger("layoutChange")
					}
				};
			return this.each(function () {
				notifyLayout($(window).width());
				$(window).resize(function () {
					notifyLayout($(window).width())
				})
			})
		}
	})
})(jQuery);
(function ($) {
	$.fn.animateNumber = function (to, duration) {
		var $ele = $(this),
			num = parseInt($ele.html()),
			up = to > num,
			num_interval = Math.abs(num - to) / 90;
		var loop = function () {
				num = up ? Math.ceil(num + num_interval) : Math.floor(num - num_interval);
				if (up && num > to || !up && num < to) {
					num = to;
					clearInterval(animation)
				}
				$ele.html(ktc.utils.Formatter.addCommas(num))
			};
		var animation = setInterval(loop, (duration > 2E3 ? duration : 2E3) / 400)
	}
})(jQuery);