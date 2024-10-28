<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
 <h2>${category.isEdit ? 'Edit Category' :'Add New Category' }</h2>
 <form action="/admin/categories/save" method="post">
 	<label>Category name</label>
 	<input type = "text" name = "name" value="${category.name }"><br>
  	<label>Images</label>
 	<input type = "text" name = "images" value="${category.images }"><br>
  	<label>Status</label>
  	<input type = "text" name = "status" value="${category.status }"><br>
  	
   	<c:if test="${category.isEdit }">
   		<input type="submit" value="Update">
   	</c:if> 
    <c:if test="${!category.isEdit }">
   		<input type="submit" value="Insert">
   	</c:if> 

 </form>