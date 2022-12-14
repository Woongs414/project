<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<style>
#tr_btn_modify {
	display: none;
}
#tr_file_upload{
	display: none;
}
#preview{
	width: 200px;
	height: 200px;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function backToList(obj){
		obj.action="${contextPath}/user/u_board.do";
		obj.submit();
	}
	
	function ff(obj){
		document.getElementById("i_title").disabled=false;
		document.getElementById("i_content").disabled=false;
		document.getElementById("i_imageFileName").disabled=false;
		document.getElementById("tr_btn_modify").style.display="block";
		document.getElementById("tr_file_upload").style.display="block";
		document.getElementById("tr_btn").style.display="none";
	}
	
	function fn_modify_article(obj){
		obj.action="${contextPath}/user/modBoard.do";
		obj.submit();
	}
	
	function fn_remove_article(url,list_num){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var list_numInput = document.createElement("input");
		articleNoInput.setAttribute("type", "hidden");
		articleNoInput.setAttribute("name", "list_num");
		articleNoInput.setAttribute("value", list_Num);
		
		form.appendChild(list_numInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	function fn_reply_form(url, parent_num){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var parent_numInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "parent_num");
		parentNoInput.setAttribute("value", parent_num);
		
		form.appendChild(parent_numInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</head>
<body>
	<form name="frmBoard" method="post" action="${contextpath}"
		enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">
				글번호
				</td>
				<td>
				<input type="text" value="${board.list_num}" disabled />
				<input type="hidden" name="list_num" value="${board.list_num}" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">
				작성자 아이디
				</td>
				<td>
				<input type="text" value="${board.user_id}" name="user_id" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">
				제목
				</td>
				<td>
				<input type="text" value="${board.u_title}" name="u_title" id="i_title" disabled >
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">
				내용
				</td>
				<td>
				<textarea rows="20" cols="60" name="u_content" id="i_content" disabled>${board.u_content}</textarea>
				</td>
			</tr>
<%-- 
			<c:if
				test="${not empty article.imageFileName && article.imageFileName !='null'}">
				<tr>
					<td width="150" align="center" bgcolor="#ff9933" rowspan="2">
						이미지</td>
					<td><input type="hidden" name="orignalFileName"
						value="${article.imageFileName}" /> <img
						src="${contextPath}/download.do?articleNo=${article.articleNo}&imageFileName=${article.imageFileName}"
						id="preview" /><br></td>
				</tr>
				<tr>
					<td><input type="file" name="imageFileName"
						id="i_imageFileName" disabled onchange="readURL(this)" /></td>
				</tr>
			</c:if>
--%>
		<c:choose>
			<c:when test="${not empty board.image_fileName && board.image_fileName !='null'}">
				<tr>
					<td width="150" align="center" bgcolor="#ff9933" rowspan="2">
					이미지
					</td>
					<td>
					<input type="hidden" name="orignalFileName" value="${board.image_fileName}" />
					<img src="${contextPath}/download.do?list_num=${board.list_num}&image_fileName=${board.image_fileName}" id="preview" /><br>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="file" name="image_fileName" id="i_imageFileName" disabled onchange="readURL(this);" />
					</td>
				</tr>	
			</c:when>
			<c:otherwise>
				<tr id="tr_file_upload">
					<td width="150" align="center" bgcolor="#ff9933">이미지</td>
					<td>
						<input type="hidden" name="orignalFileName" value="${board.image_fileName}" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<img id="preview" /><br>
						<input type="file" name="image_fileName" id="i_imageFileName" disabled onchange="readURL(this);" />
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">
				등록일자
				</td>
				<td>
					<input type="text" value="<fmt:formatDate value="${board.mod_date}"/>" disabled />
				</td>
			</tr>
			<tr id="tr_btn_modify" align="center">
				<td colspan="2">
				<input type="button" value="수정반영하기" onClick="fn_modify_article(frmBoard)" >
				<input type="button" value="취소" onClick="backToList(frmBoard)" >
			</tr>
			<tr id="tr_btn">
				<td colspan="2" align="center">
				<c:if test="${member.id==article.id}">
					<input type="button" value="수정하기" onClick="ff(this.form)" />
					<input type="button" value="삭제하기" onClick="fn_remove_article('${contextPath}/user/removeBoard', ${board.list_num})" />
				</c:if>
					<input type="button" value="리스트로 돌아가기" onClick="backToList(this.form)" />
					<input type="button" value="답글쓰기" onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${board.list_num})" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>