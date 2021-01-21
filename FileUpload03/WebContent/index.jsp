<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- application/x-www-form-urlencoded: 提交上去的数据是一份经过url编码的form表单诗句，一般它适用于提交简单的字符串
	multipart/form-data : 上传上去的数据是多份数据，适用于包含简单字符串  &  文件数据 -->
	<form method="post" action="${pageContext.request.contextPath }/upload" enctype="multipart/form-data">
		用户名：<input type="text" name="username"/><br>
		密码：<input type="text" name="password"/><br>
		文件：<input type="file" name="pimage"  onchange="preImg(this.id,'previewid');"/><br>
		<input type="button" value="关闭" name="close" onclick="clsoseWin()" />
				<input type="submit" value="提交"/>
	</form>

</body>
</html>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<script>
  function clsoseWin(fileid, previewid)  { 
	  window.open("","_self");
	  window.close();
  }

 
 function preImg(fileid, previewid)  {    
     //异步校验图片格式
    var form = document.getElementById(fileid);
// 用表单来初始化
 var formData = new FormData(form);
  $.ajax({
     type: "POST",
      url: "${pageContext.request.contextPath }/upload2",
      data: formData,
      dataType: "json",
      processData: false,
      async: false,
      cache: false,
      success: function(data) {
          if (data.result == 0) {
              alert("请上传图片JPG或GIF格式的图片！！");
              errorCount++;
          } else if (data.result == 1) {
              alert("请上传图片格式等于" + imgWidth + "×" + imgHeight + " 格式的图片！！");
          } else if (data.result == 2) {
              imgSize = data.imageSize;
              alert("图片大于" + data.imageSize + "K！");
          } 
      }
  })
 }
 
 
 </script>

