//var p=document.getElementById("chenji")	;


	var tb=document.getElementById("chenji");

window.onload=function (){
	for(var i=0;i<tb.rows.length;i++){
		for(var j=0;j<tb.rows[i].cells.length;j++){
			var zhi=tb.rows[i].cells[j].innerHTML;
			if (zhi<60&&j==3){
				tb.rows[i].cells[j].style.color="red";
			}
		}
	}
	console.log(zhi);
	
}
