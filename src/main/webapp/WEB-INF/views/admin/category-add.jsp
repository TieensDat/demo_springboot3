<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<form action="${pageContext.request.contextPath }/admin/category/insert" method="post" enctype="multipart/form-data">
	<label for="categoryname">Category Name:</label><br>
	 <input type="text"id="categoryname" name="categoryname" ><br> 
	 <label for="images">Images:</label><br> 
	 
	 		<img id="imagess" height="150" width="200" src =""/>
	 <input type="file" onchange = "chooseFile(this)" id="images" name="images"><br>
	  <!-- <label for="status">Status:</label><br> 
	 <input type="text" id="status"name="status"><br> -->
	 <p>Status:</p>
	 <input type="radio" id="ston" name="status" value="1" checked>
	 <lable for="html">Đang hoạt động</lable><br>
	 <input type="radio" id="stoff" name="status" value="0">
	 <label for="css">Khoá</label><br>
	<br> <input type="submit" value="Add Category">
</form>
