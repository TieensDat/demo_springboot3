<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <form action="/admin/categories/save" method="post">
 	<input type="hidden" value="${category.isEdit }" name="isEdit">
 	<input type="hidden" value="${category.id }" name="id">
 	
 	<label>Category name</label>
 	<input type = "text" name = "${category.name }"><br>
  	<label>Images</label>
 	<input type = "text" name = "${category.images }"><br>
  	<label>Status</label>
  	<input type = "text" name = "${category.status }"><br>
  	
   	<c:if test="${category.isEdit }">
   		<input type="submit" value="Update">
   	</c:if> 
   <%-- 	<c:if test="${category.isEdit }">
   		<input type="submit" value="Insert">
   	</c:if> 
 --%>
 </form>