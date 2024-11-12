<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/up.jsp" %>

    <div class="panel">
        <div class="panel-heading">
            <span class="panel-title">协会举办活动</span>
        </div>
        <div class="panel-body">
            <form action="activity/addActivity" method="post">
                活动名：<input type="text" class="form-control" name="name" placeholder="请输入活动名称" required><br>
                活动简介：<input type="text" class="form-control" name="intro" placeholder="请输入活动简介" required><br>
                开始时间：<input type="datetime-local" class="form-control" name="start" required/>
                结束时间：<input type="datetime-local" class="form-control" name="end" required/>
                <input type="hidden" name="assId" value=""/>
                <input type="submit" class="btn btn-success" value="添加"/>
            </form>
        </div>
        <script>
            let msg = "";
            if (msg == 1){
                alert("添加成功")
            }
        </script>
    </div>

<%@include file="../common/down.jsp" %>
