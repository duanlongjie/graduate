<!DOCTYPE html>
<html>
	<head>
		<!--<link rel="stylesheet" type="text/css" href="css/base.css"/>-->
		<link rel="stylesheet" type="text/css" href="/static/student/css/specificAP.css"/>
        <link rel="stylesheet" type="text/css" href="/static/student/css/specificl.css"/>
		<!--<link rel="stylesheet" type="text/css" href="css/iconfont.css"/>-->
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		<meta charset="utf-8" />
		<title>学业成绩</title>
	</head>
	<body>
		<header>
			<nav class="common_title">
				<div class="logo">
					<img src="/static/student/img/logo01.png"/>
				<div class="caidan">
					<ul>
						<li>
							<dl>
								<dt><a href="#">...</a></dt>
								<dd><a href="graduationWarning.html">返回</a></dd>
								<dd><a href="#">退出</a></dd>
							</dl>
						</li>
					</ul>
				</div>
				</div>
				<!--<i class="iconfont icon-fanhui" onclick="javascript:history.back(-1)"></i>-->
			</nav>
		</header>
		<section class="ap">
			<div >
				<span>${student.studentName}同学成绩表如下：</span>
			</div>
		<!--设置学年，学期下拉选择框-->
				<!--<div>
					<span>学年：</span>
					<select >
						<option ></option> 
						<option >2001-2002</option>
						<option >2002-2003</option>
						<option >2003-2004</option>
						<option >2004-2005</option>
						<option >2005-2006</option>
						<option >2006-2007</option>
						<option >2007-2008</option>
						<option >2008-2009</option>
						<option >2009-2010</option>
						<option >2010-2011</option>
						<option >2011-2012</option>
						<option >2012-2013</option>
					</select>
					<span>学期：</span>
					<select >
						<option ></option>
						<option >1</option>
						<option >2</option>
						<option >3</option>
					</select>
				</div>-->
			<div class="ap1">
				<table cellspacing="0px" id="chenji">
					<tr class="special">
						<td colspan="4" >成绩详情</td>
					</tr>
					
					<tr>
						<td>课程名</td>
						<td>课程性质</td>
						<td>学分</td>
						<td>成绩</td>
					</tr>
					<#list gradeList as gl>
					<tr>
						<td>${gl.course.courseName}</td>
						<td>${gl.course.courseType}</td>
						<td>${gl.course.courseCredit}</td>
						<td >${gl.score}</td>
					</tr>
					</#list>
					<#--<tr>-->
						<#--<td>军训</td>-->
						<#--<td>基础课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >80</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>计算机基础</td>-->
						<#--<td>基础课</td>-->
						<#--<td>2.0</td>-->
						<#--<td >69</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>高等数学A1</td>-->
						<#--<td>必修课</td>-->
						<#--<td>5.0</td>-->
						<#--<td >67</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>C语言程序设计</td>-->
						<#--<td>必修课</td>-->
						<#--<td>5.0</td>-->
						<#--<td >71</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>珠宝鉴赏</td>-->
						<#--<td>选修课</td>-->
						<#--<td>2.0</td>-->
						<#--<td >89</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>人体工程</td>-->
						<#--<td>体育课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >72</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>思想道德修养与法律基础</td>-->
						<#--<td>实践课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >良</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>军事理论与国防教育</td>-->
						<#--<td>基础课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >51</td>-->
					<#--</tr>					-->
				<#--</table>-->
				<#--<table cellspacing="0px">-->
					<#--<tr class="special">-->
						<#--<td colspan="4" >2016-2017年 第二学期</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>课程名</td>-->
						<#--<td>课程性质</td>-->
						<#--<td>学分</td>-->
						<#--<td>成绩</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>大学英语1</td>-->
						<#--<td>基础课</td>-->
						<#--<td>4.0</td>-->
						<#--<td >55</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>军训</td>-->
						<#--<td>基础课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >80</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>计算机基础</td>-->
						<#--<td>基础课</td>-->
						<#--<td>2.0</td>-->
						<#--<td >69</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>高等数学A1</td>-->
						<#--<td>必修课</td>-->
						<#--<td>5.0</td>-->
						<#--<td >67</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>C语言程序设计</td>-->
						<#--<td>必修课</td>-->
						<#--<td>5.0</td>-->
						<#--<td >71</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>珠宝鉴赏</td>-->
						<#--<td>选修课</td>-->
						<#--<td>2.0</td>-->
						<#--<td >89</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>人体工程</td>-->
						<#--<td>体育课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >72</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>思想道德修养与法律基础</td>-->
						<#--<td>实践课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >良</td>-->
					<#--</tr>-->
					<#--<tr>-->
						<#--<td>军事理论与国防教育</td>-->
						<#--<td>基础课</td>-->
						<#--<td>1.0</td>-->
						<#--<td >51</td>-->
					<#--</tr>-->

				</table>
			</div>
			<!--<div class="exit">
				<input type="button" value="点击退出" onclick="window.history.back(-1);"/>
			</div>-->
			<!--<div class="person">
				<span>学习计划：</span>
				<textarea  rows="5" cols="37" placeholder="多想不如多做。"></textarea>
			</div>-->
		</section>
	</body>
</html>
