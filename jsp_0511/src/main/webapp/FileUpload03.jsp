<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-05-11
  Time: 오후 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>FileUpload - apache</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
          crossorigin="anonymous"></script>
</head>
<body>
  <div class="container my-4">
    <div class="row">
      <div class="col-sm-4 mx-auto">
        <form action="FileUpload03Process.jsp" method="post" enctype="multipart/form-data">
          <label for="file" class="form-label">파일 : </label>
          <input type="file" id="file" name="file" class="form-control">
          <button type="submit" class="btn btn-dark">업로드</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
