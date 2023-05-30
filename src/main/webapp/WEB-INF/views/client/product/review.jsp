<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Reach2 Store</title>
<%@ include file="/common/public/info/linkinfo.jsp"%>

</head>
<body>
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>
	<section>
		<div class="container py-5">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">              
                        <h5>Viết bình luận cho ${product.name}</h5>
                        <form action="${pageContext.request.contextPath}/add-review" method="POST">                         
                            <input type="hidden" name="prod_id" value=" ${product.id} ">
                             <input type="hidden" name="user_id" value=" ${userSession.id} ">
                            <textarea class="form-control" name="user_review" placeholder="Viết bình luận về sản phẩm" id="" cols="30" rows="10"></textarea>
                            <button type="submit" class="btn btn-primary mt-3">Đăng bình luận</button>
                        </form>                 
                </div>
            </div>
        </div>
    </div>
</div>
		
	</section>
</body>
</html>