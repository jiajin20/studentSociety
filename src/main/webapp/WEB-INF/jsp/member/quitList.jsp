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
        <span class="panel-title">${requestScope.society.society_name}协会退出申请列表</span>
    </div>
    <script>
        function deleteMember(ele, status) {
            let memberId = ele.parentNode.parentNode.dataset.id;
            axios.post("/member/deleteMember", {
                id: memberId,
                status: status
            }).then(data => {
                if (data.data == 1 && status == 2) {
                    alert("删除成功")
                    ele.parentNode.parentNode.parentNode.removeChild(ele.parentNode.parentNode);
                } else if (status == 1) {
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

            <c:forEach items="${members}" var="member">
                <tr data-id="${member.memberId}">
                    <td>${member.memberInfo.studentNumber}</td>
                    <td>${member.memberInfo.studentName}</td>
                    <td>${member.memberInfo.gender}</td>
                    <td>
                        <fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger"
                                onclick="deleteMember(this,2)">同意
                        </button>
                        <button type="button" class="btn btn-warning"
                                onclick="deleteMember(this,1)">拒绝
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="../common/down.jsp" %>