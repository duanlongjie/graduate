! function(t) {
	function e(i) {
		if(a[i]) return a[i].exports;
		var n = a[i] = {
			i: i,
			l: !1,
			exports: {}
		};
		return t[i].call(n.exports, n, n.exports, e), n.l = !0, n.exports
	}
	var a = {};
	e.m = t, e.c = a, e.d = function(t, a, i) {
		e.o(t, a) || Object.defineProperty(t, a, {
			configurable: !1,
			enumerable: !0,
			get: i
		})
	}, e.n = function(t) {
		var a = t && t.__esModule ? function() {
			return t.default
		} : function() {
			return t
		};
		return e.d(a, "a", a), a
	}, e.o = function(t, e) {
		return Object.prototype.hasOwnProperty.call(t, e)
	}, e.p = "", e(e.s = 0) 
}([function(t, e) {
	! function(t, e, a, i) {
		var n = function(e, a) {
			this.ele = e, this.defaults = {
				currentPage: 1,
				totalPage: 10,
				isShow: !0,
				count: 5,
				homePageText : "首页",
				endPageText : "尾页",
				prevPageText : "上一页",
				nextPageText : "下一页",
				callback: function() {}
			}, this.opts = t.extend({}, this.defaults, a), this.current = this.opts.currentPage, this.total = this.opts.totalPage, this.init()
		};
		n.prototype = {
			init: function() {
				this.render(), this.eventBind()
			},
			render: function() {
				var t = this.opts,
					e = this.current,
					a = this.total,
					i = this.getPagesTpl(),
					n = this.ele.empty();
				this.isRender = !0, this.homePage = '<a href="student_list?pager=0" class="ui-pagination-page-item" data-current="1">' + t.homePageText + "</a>", this.prevPage = '<a href="student_list?pager='+(e-2)+'" class="ui-pagination-page-item" data-current="' + (e - 1) + '">' + t.prevPageText + "</a>", this.nextPage = '<a href="student_list?pager='+e+'" class="ui-pagination-page-item" data-current="' + (e + 1) + '">' + t.nextPageText + "</a>", this.endPage = '<a href="student_list?pager='+(a-1)+'" class="ui-pagination-page-item" data-current="' + a + '">' + t.endPageText + "</a>", this.checkPage(), this.isRender && n.html("<div class='ui-pagination-container'>" + this.homePage + this.prevPage + i + this.nextPage + this.endPage + "</div>")
			},
			checkPage: function() {
				var t = this.opts,
					e = this.total,
					a = this.current;
				t.isShow || (this.homePage = this.endPage = ""), 1 === a && (this.homePage = this.prevPage = ""), a === e && (this.endPage = this.nextPage = ""), 1 === e && (this.homePage = this.prevPage = this.endPage = this.nextPage = ""), e <= 1 && (this.isRender = !1)
			},
			getPagesTpl: function() {
				var t = this.opts,
					e = this.total,
					a = this.current,
					i = "",
					n = t.count;
				if(e <= n)
					for(var r = 1; r <= e; r++) i += r === a ? '<a href="student_list?pager='+(r-1)+'" class="ui-pagination-page-item active" data-current="' + r + '">' + r + "</a>" : '<a href="student_list?pager='+(r-1)+'" class="ui-pagination-page-item" data-current="' + r + '">' + r + "</a>";
				else {
					var s = n / 2;
					if(a <= s)
						for(var r = 1; r <= n; r++) i += r === a ? '<a href="student_list?pager='+(r-1)+'" class="ui-pagination-page-item active" data-current="' + r + '">' + r + "</a>" : '<a href="student_list?pager='+(r-1)+'" class="ui-pagination-page-item" data-current="' + r + '">' + r + "</a>";
					else {
						var o = Math.floor(s), 
							c = a + o,
							h = a - o,
							u = n % 2 == 0;
						c > e && (u ? (h -= c - e - 1, c = e + 1) : (h -= c - e, c = e)), u || c++;
						for(var r = h; r < c; r++) i += r === a ? '<a href="student_list?pager='+r+'" class="ui-pagination-page-item active" data-current="' + r + '">' + r + "</a>" : '<a href="student_list?pager='+(r-1)+'" class="ui-pagination-page-item" data-current="' + r + '">' + r + "</a>"
					}
				}
				return i
			},
			setPage: function(t, e) {
				return t === this.current && e === this.total ? this.ele : (this.current = t, this.total = e, this.render(), this.ele)
			},
			getPage: function() {
				return {
					current: this.current,
					total: this.total
				}
			},
			eventBind: function() {
				var e = this,
					a = this.opts.callback;
				this.ele.off("click").on("click", ".ui-pagination-page-item", function() {
					var i = t(this).data("current");
					e.current != i && (e.current = i, e.render(), a && "function" == typeof a && a(i))
				})
			}
		}, t.fn.pagination = function(t, e, a) {
			if("object" == typeof t) {
				var i = new n(this, t);
				this.data("pagination", i)
			}
			return "string" == typeof t ? this.data("pagination")[t](e, a) : this
		}
	}(jQuery, window, document)
}]);