<%--
  Created by IntelliJ IDEA.
  User: wolfishplk
  Date: 2023/7/18
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../common/up.jsp" %>
<div class="panel">
    <div class="panel-heading">
        <span class="panel-title">${society.society_name}协会成员列表</span>
        <button type="button" class="btn btn-danger"
                style="padding:4px 10px;background-color: crimson;"
                onclick="clearGraduated()">
            <i class="fa fa-trash-o"></i>清除毕业生
        </button>
    </div>
    <script>
        function clearGraduated(){
            let assId = "${requestScope.society.societyId}";
            data = {assId:assId};
            axios.post("/member/deleteGraduated",data)
                .then(data =>{
                    if(!isNaN(data.data)){
                        alert("删除成功");
                        location.reload();
                    }else{
                        alert("没有毕业生");
                    }
                })
                .catch(e => {
                    alert(e)
                });
        }

        function deleteMember(ele,status){
            let memberId = ele.parentNode.parentNode.dataset.id;
            axios.post("/member/deleteMember",{
                id:memberId,
                status:status
            }).then(data =>{
                if(data.data == 1){
                    alert("删除成功")
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
                <th>是否毕业</th>
                <th>退出</th>
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
                        ${member.memberInfo.graduated == 1 ? "未毕业" : "已毕业"}
                    </td>
                    <td>
                        <c:if test="${sessionScope.position.memberPosition == 1 || sessionScope.position.memberPosition == 2}">
                            <button type="button" class="btn btn-danger" onclick="deleteMember(this,2)">
                                强制退出
                            </button>
                        </c:if>
                        <c:if test="${member.memberInfo.studentNumber == student}">
                            <button type="button" class="btn btn-danger" onclick="deleteMember(this,3)">
                                退出
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="../common/down.jsp" %>
