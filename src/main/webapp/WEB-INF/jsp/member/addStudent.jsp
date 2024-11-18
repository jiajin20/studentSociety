<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../common/up.jsp" %>

<div class="panel">
    <div class="panel-heading">
        <span class="panel-title">${society.society_name}协会添加成员</span>
    </div>
    <div class="panel-body">
        <form action="student/queryStudent" method="post">
            <input type="hidden" name="societyId" value="${requestScope.society.societyId}"> <!-- 假设这里传递协会ID -->
            姓名：<input type="text" class="form-control" name="name" placeholder="请输入姓名"><br>
            学号：<input type="number" class="form-control" name="number" placeholder="请输入学号"><br>
            <input type="submit" class="btn btn-success" value="搜索"/>
        </form>

        <table class="table" data-assid="${requestScope.society.societyId}">
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>生日</th>
                <th>毕业与否</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty requestScope.students}">
                <c:forEach items="${requestScope.students}" var="student">
                    <tr data-stuid="${student.studentId}">
                        <td>${student.studentNumber}</td>
                        <td>${student.studentName}</td>
                        <td>${student.gender}</td>
                        <td>
                            <fmt:formatDate type="date" value="${student.birthday}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                                ${student.graduated == 1 ? "在校" : "毕业"}
                        </td>
                        <td>
                            <button class="btn btn-primary" onclick="addStudent(this)">招收</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

    </div>
</div>



<script>
    function addStudent(ele) {
        let stuId = ele.closest('tr').dataset.stuid; // 从最近的 tr 中获取 data-stuid
        let assId = ele.closest('table').dataset.assid; // 从 table 中获取 data-assid

        if (!stuId || !assId) {
            alert("无法获取学生 ID 或协会 ID，请检查页面结构！");
            return;
        }

        let data = {
            stuId: stuId,
            assId: assId
        };

        console.log("stuId:", stuId);
        console.log("assId:", assId);

        axios.post("/member/addMember", axios.transformRequest(data))
            .then((number) => {
                if (number.data == 1) {
                    alert("添加成功");
                    ele.closest('tr').remove(); // 删除当前行
                } else {
                    alert(number.data.message);
                }
            })
            .catch((e) => {
                alert("请求失败：" + e);
            });
    }

</script>

<%@ include file="../common/down.jsp" %>