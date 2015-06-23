<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<script type="text/javascript">
	$(function() {
		$("#accordion").accordion({heightStyle: "fill"});
	});
</script>

<head>
	<title>車險理賠系統</title>
</head>

<body>

	<div id="accordion" style="height:98%; width:100%">
		<h3>車體損失險</h3>
		<div>
			<p>
				車體損失險，顧名思義就是替您愛車的身體投保保險來保障，而車體損失險提供甲式、乙式及免自負額車對車碰撞損失險（簡稱丙式）供車主選擇，您可以決定為您愛車的身體投保到什麼程度。
			</p>
			<table border="1">
				<tr>
					<th>甲式</th>
					<th>乙式</th>
					<th>丙式</th>
				</tr>
				<tr>
					<td>碰撞、傾覆</td>
					<td>碰撞、傾覆</td>
					<td>與車輛發生碰撞、擦撞所致之毀損滅失</td>
				</tr>
				<tr>
					<td>火災</td>
					<td>火災</td>
					<td>確認事故之對方車輛</td>
				</tr>
				<tr>
					<td>閃電、雷擊</td>
					<td>閃電、雷擊</td>
					<td>
				</tr>
				<tr>
					<td>爆炸</td>
					<td>爆炸</td>
					<td>
				</tr>
				<tr>
					<td>拋擲物或墜落物</td>
					<td>拋擲物或墜落物</td>
					<td>
				</tr>
				<tr>
					<td>第三人非善意行為</td>
					<td>
					<td>
				</tr>
				<tr>
					<td>不屬本保險契約特別載名為不保事項之任何其他原因</td>
					<td>
					<td>
				</tr>
			</table>
			<p></p>
			<p>
				除了丙式出險時被保險人不用負擔自負額﹐甲式及乙式被保險人申請理賠時﹐均應負擔基本自負額：
			</p>
			<ul>
				<li>第一次新台幣三千元</li>
				<li>第二次新台幣五千元</li>
				<li>第三次以後新台幣七千元</li>
			</ul>
			<p>
				除了上列基本自負額外﹐亦可向保險公司約定較高自負額﹐以減輕保險費負擔。
			</p>
		</div>
		<h3>乘客責任險</h3>
		<div>
			<p>
				所謂「乘客責任險」係指被保險人使用或管理被保險汽車發生意外事故，致乘客受有體傷或死亡時，依法應負賠償責任而受賠償請求時，保險公司同意對被保險人負理賠之責。
			</p>
		</div>
		<h3>第三人責任險傷害責任</h3>
		<div>
			<p>
				用來保障被保險人因所有､使用或管理被保險汽車發生意外事故﹐致第三人體傷或死亡﹐依法應負賠償責任而受賠償請求時﹐保險公司就超過強制汽車責任保險金額以上的部分對被保險人負賠償之責。
			</p>
		</div>
		<h3>第三人責任險財損責任</h3>
		<div>
			<p>
				被保險人因所有、使用或管理被保險汽車發生意外事故，致第三人財物受有損害，依法應負賠償責任而受賠償請求時，保險公司對被保險人負賠償之責。
			</p>
		</div>
	</div>

</body>
</html>