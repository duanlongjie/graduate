        var code;
        function createCode() {
            code = "";
            var codeLength = 6; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) 
            {
                var charNum = Math.floor(Math.random() * 52);
                code += codeChars[charNum];
            }
            if (checkCode) 
            {
                checkCode.className = "code";
                checkCode.innerHTML = code;
            }
        }
        function validateCode() 
        {
        	var a=document.getElementById("zhanghao").value;
        	var b=document.getElementById("mima").value;
            var inputCode = document.getElementById("inputCode").value;
            if (a.length<=0||b.length<=0) {
            	alert("请输入账号或密码！");
            }
            else if (inputCode.length <= 0) 
            {
                alert("请输入验证码！");
            }
            else if (inputCode.toUpperCase() != code.toUpperCase()) 
            {
                alert("验证码输入有误！");
                createCode();
                a=this.a;
                b=this.b;
            }
            else {
            	    alert("验证码正确！");	
            		window.open('graduationWarning.html');
            		window.history.back(-1);		
            }
                
                  
        }
        
        
        
        
//      window.onload=function(){
//	document.getElementById("inputCode").style.color=getColor();
//	
//}
//      function getColor(){
//	
//		var str="#";
//		var arr=["1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"];
//		for(var i=0;i<6;i++){
//			var num=parseInt(Math.random()*15);
//			str+=arr[num];
//		}
//		return str;
//
//}