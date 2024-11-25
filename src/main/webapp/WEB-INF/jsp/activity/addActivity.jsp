<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/up.jsp" %>

<div class="panel">
    <div class="panel-heading">
        <span class="panel-title">${sessionScope.society.society_name} 协会举办活动</span>
    </div>
    <div class="panel-body">
        <form action="/studentSociety/activity/addActivity" method="post">
            活动名：<input type="text" class="form-control" name="name" placeholder="请输入活动名称" required><br>
            活动简介：<input type="text" class="form-control" name="intro" placeholder="请输入活动简介" required><br>
            开始时间：<input type="datetime-local" class="form-control" name="start" required/>
            结束时间：<input type="datetime-local" class="form-control" name="end" required/>
            <input type="hidden" name="assId" value="${requestScope.society.societyId}"/>
            <input type="submit" class="btn btn-success" value="添加"/>
        </form>
    </div>
    <script>
        // 根据服务端返回的消息显示提示
        const result = "${requestScope.result}";
        if (result === "1") {
            alert("活动添加成功！");
        } else if (result === "0") {
            alert("活动添加失败，请检查输入！");
        }
    </script>
</div>

<%@ include file="../common/down.jsp" %>
