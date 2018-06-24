<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1" />
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
					<div class="caidan">
					<ul>
						<li>
							<dl>
								<dt><a href="#">...</a></dt>
								<dd><a href="/student/logout">退出登录</a></dd>
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
                        <td>专业必修课：<span id="requiredCoursesCredit">${studentCreditResult.requiredCoursesCredit}</span>学分</td>
						<td><span id="studentRequiredCredit">${studentCreditResult.studentRequiredCredit}</span></td>
					</tr>
					<tr>
                        <td>公共选修课：<span id="needPublicCredit">${studentCreditResult.needPublicCredit}</span>学分</td>
						<td ><span id="studentElectiveCredit">${studentCreditResult.studentElectiveCredit}</span></td>
					</tr>
					<tr>
                        <td>体育课：<span id="needPeCredit">${studentCreditResult.needPeCredit}</span>学分</td>
						<td ><span id="studentPeCredit">${studentCreditResult.studentPeCredit}</span></td>
					</tr>
					<tr>
						<td>取得毕业资格</td>
						<td><span id="graduateOrNot">${studentCreditResult.graduateOrNot}</span></td>
					</tr>
					<tr>
						<td>重修（补考）学分不超过30学分</td>
                        <td><span id="studentFailCredit">${studentCreditResult.studentFailCredit}</span></td>
					</tr>
					<tr>
						<td>相关处分</td>
                        <td><span id="chufen">有处分</span></td>
					</tr>
					</tbody>			
				</table>
			</div>
			<div>
				<font><i class="iconfont" id="icon"></i> <span id="tip">警告：您还没有获得毕业资格</span></font>
			</div>
			<div>
				<a href="/grade" id="dianji">点击查询成绩详情</a>
			</div>

		</section>
        <script type="text/javascript" src="/static/student/js/jquery-2.1.3.min.js"></script>
        <script type="text/javascript" src="/static/student/js/graduationConditions.js"></script>
			
	</body>
</html>
