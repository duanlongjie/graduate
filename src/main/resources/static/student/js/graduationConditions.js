
var a=document.getElementById("xuanxiu");
var b=document.getElementById("tiyu");
var c=document.getElementById("zige");
var q=document.getElementById("chufen");
var y=document.getElementById("tip");
var x=document.getElementById("icon");
window.onload=function(){
	
	if (a.innerHTML<16){
		a.style.color="red";
	}
	if (b.innerHTML<4){
		b.style.color="red";
	}
	if (c.innerHTML=="否"){
		c.style.color="red";
	}	
	if (q.innerHTML=="无处分"){
			
	} else {
		q.style.color="red";	
	}
	if (y.innerHTML=="提示：您已获得毕业资格。") {
		x.style.color="green";
	}
	if (y.innerHTML=="警告：您还没有获得毕业资格。"){
		x.style.color="red";
	}
	}
//function liuXiao(){
//	top.location="staySchool.html"
//}


function panDuan(){
	
	if (q.innerHTML=="无处分"){
		alert("恭喜您，没有留校处罚！")
	} else {
		
		top.location="staySchool.html";
	}
}


