<%--
  Created by IntelliJ IDEA.
  User: wolfishplk
  Date: 2023/7/19
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/up.jsp" %>
<div class="panel">
    <div class="panel-heading">
        <span class="panel-title">协会退出申请列表</span>
    </div>
    <script>
        function deleteMember(ele,status){
            let memberId = ele.parentNode.parentNode.dataset.id;
            axios.post("/member/deleteMember",{
                id:memberId,
                status:status
            }).then(data =>{
                if(data.data == 1 && status == 2){
                    alert("删除成功")
                    ele.parentNode.parentNode.parentNode.removeChild(ele.parentNode.parentNode);
                }else if (status == 1){
                    alert("拒绝退出")
                    ele.parentNode.parentNode.parentNode.removeChild(ele.parentNode.parentNode);
                }
            }).catch(e => {
                alert(e)
            });

        }
    </script>
    <div class="panel-body">
        <table class="table">
            <thead>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>加入时间</th>
            <th>是否同意</th>
            </thead>
            <tbody>
            
            </tbody>
        </table>
    </div>
</div>
<%@include file="../common/down.jsp" %>