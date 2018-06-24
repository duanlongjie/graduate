
//学分标准：
var requiredCoursesCredit=$("#requiredCoursesCredit");
var needPublicCredit=$("#needPublicCredit");
var needPeCredit=$("#needPeCredit");

//该生学分：
var studentElectiveCredit=$("#studentElectiveCredit");
var studentPeCredit=$("#studentPeCredit");
var graduateOrNot=$("#graduateOrNot");
var studentRequiredCredit=$("#studentRequiredCredit");
var studentFailCredit=$("#studentFailCredit");
var q=$("#chufen");
var y=document.getElementById("tip");
var x=document.getElementById("icon");
window.onload=function(){
    if (parseInt(studentRequiredCredit.text())< parseInt(requiredCoursesCredit.text())){
        studentRequiredCredit[0].style.color="red";
    }
	if (parseInt(studentElectiveCredit.text())< parseInt(needPublicCredit.text())){
        studentElectiveCredit[0].style.color="red";
	}
	if (parseInt(studentPeCredit.text())<parseInt(needPeCredit.text())){
        studentPeCredit[0].style.color="red";
	}
	if (graduateOrNot.text()=="否"){
        graduateOrNot[0].style.color="red";
        y.innerHTML="警告：您还没有获得毕业资格";
        x.style.color="red";
    }else if(graduateOrNot.text()=="是"){
        y.innerHTML="提示：您已获得毕业资格";
        x.style.color="green";
    }
    if (parseInt(studentFailCredit.text())>=30){
        studentFailCredit[0].style.color="red";
    }
    if (q.text()=="无处分"){
	} else {
		q[0].style.color="red";
	}
	// if (y.innerHTML=="提示：您已获得毕业资格") {
	// 	x.style.color="green";
	// }
	// if (y.innerHTML=="警告：您还没有获得毕业资格"){
	// 	x.style.color="red";
	// }
}


