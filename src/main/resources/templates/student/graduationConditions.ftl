<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		<!--<link rel="stylesheet" type="text/css" href="css/iconfont.css"/>-->
		<link rel="stylesheet" type="text/css" href="/static/student/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="/static/student/css/specificGW.css"/>
		<link rel="stylesheet" type="text/css" href="/static/student/css/iconfonts.css"/>
		<title>毕业预警页面</title>
<style type="text/css">
	@font-face {
  font-family: 'icomoon';
  src:  url('/static/student/fonts/icomoon.eot?q8ghp1');
  src:  url('/static/student/fonts/icomoon.eot?q8ghp1#iefix') format('embedded-opentype'),
    url('/static/student/fonts/icomoon.ttf?q8ghp1') format('truetype'),
    url('/static/student/fonts/icomoon.woff?q8ghp1') format('woff'),
    url('/static/student/fonts/icomoon.svg?q8ghp1#icomoon') format('svg');
  font-weight: normal;
  font-style: normal;
}

</style>
	</head>
	<body>
		<header>
			<nav class="common_title">
				<div class="logo">
					<img src="/static/student/img/logo01.png"/>
					<h1>毕业预警</h1>
					<div class="caidan">
					<ul>
						<li>
							<dl>
								<dt><a href="#">...</a></dt>
								<dd><a href="updatepwd.ftl">重置密码</a></dd>
								<dd><a href="#">退出登录</a></dd>
							</dl>
						</li>
					</ul>
				</div>
				</div>
				<!--<i class="iconfont icon-fanhui" onclick="javascript:history.back(-1)"></i>-->
			</nav>
		</header>
		<section class="gw">
			<div>
				<span>${studentCreditResult.name}同学，你的毕业资格和学位资格情况如下:</span>
			</div>
			<div>
				<table cellspacing="0px">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td>毕业资格和学位资格</td>
							<td>${studentCreditResult.name}同学</td>
						</tr>
						<tr>
						<td>必修课修满和及格(${studentCreditResult.requiredCoursesCredit}学分)</td>
						<td>${studentCreditResult.studentRequiredCredit}</td>
					</tr>
					<tr>
						<td>公共选修课：${studentCreditResult.needPublicCredit}学分</td>
						<td ><a href="grade.ftl" id="xuanxiu">${studentCreditResult.studentElectiveCredit}</a></td>
					</tr>
					<tr>
						<td>体育课：${studentCreditResult.needPeCredit}学分</td>
						<td ><a href="grade.ftl" id="tiyu">${studentCreditResult.studentPeCredit}</a></td>
					</tr>
					<tr>
						<td>取得毕业资格</td>
						<td><a href="grade.ftl" id="zige">${studentCreditResult.graduateOrNot}</a></td>
					</tr>
					<tr>
						<td>重修（补考）学分不超过30学分</td>
						<td>${studentCreditResult.studentFailCredit}</td>
					</tr>
					<tr>
						<td>留校查看处分</td>
						<td id="chufen" onclick="panDuan()">有处分</td>
					</tr>
					</tbody>			
				</table>
			</div>
			<div>
				<!--<div class="warn">
					<i ></i>
				</div>-->
				<font><i class="iconfont" id="icon"></i> <span id="tip">警告：您还没有获得毕业资格。</span></font>
				
				<!--<font><i class="iconfont" id="icon"></i> <span id="tip">提示：您已获得毕业资格。</span></font>-->
			</div>
			<div>
				<a href="grade.ftl" id="dianji">点击查询成绩详情</a>
			</div>
			<!--<div class="exit">
				<input type="button" value="点击退出" onclick="window.history.back(-1);"/>
			</div>			-->
			<!--<div class="per">
				<span>想对自己说：</span>
				<textarea  rows="5" cols="35" placeholder="加油吧！同学。"></textarea>
			</div>-->
		</section>
		<script type="text/javascript" src="/static/student/js/graduationConditions.js">
		</script>
			
	</body>
</html>
