<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/up.jsp" %>
<div class="panel">
    <div class="panel-heading">
        <span class="panel-title">${society.society_name}协会添加成员</span>
    </div>
    <div class="panel-body">
        <form action="student/queryStudent" method="post">
            姓名：<input type="text" class="form-control" name="name" placeholder="请输入姓名"><br>
            学号：<input type="number" class="form-control" name="number" placeholder="请输入学号"><br>
            <input type="submit" class="btn btn-success" value="搜索"/>
        </form>

        <table class="table" data-assid="">
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
            <c:if test="${requestScope.students != null}">
                <table class="table" data-assid="${requestScope.society.societyId}">

                </table>
            </c:if>
            <c:forEach items="${requestScope.students}" var="item">

                <tr data-id="${item.studentId}">
                    <td>${stu.studentNumber}</td>
                    <td>${stu.studentName}</td>
                    <td>${stu.gender}</td>
                    <td>
                        <fmt:formatDate type="date" value="${stu.birthday}"
                                        pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${stu.gradated ==1 ? "在校" : "毕业"}
                    </td>
                    <td>
                            ${stu.gradated ==1 ? "在校" : "毕业"}
                    </td>
                    <td>
                        <button class='btn btn-primary' onclick="addStudent(this)">
                            招收
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>

<script>
    function addStudent(ele){
        let stuId = ele.parentNode.parentNode.dataset.id;
        let assId = ele.parentNode.parentNode.parentNode.parentNode.dataset.assid;
        let data = {
            stuId : stuId,
            assId : assId
        }
        axios.post("/member/addMember",axios.transformRequest(data))
            .then((number)=>{
                if(number.data == 1){
                    alert("添加成功");
                    ele.parentNode.parentNode.parentNode.removeChild(ele.parentNode.parentNode);
                }else{
                    alert(number.data.message);
                }

            }).catch((e)=>{
            alert(e)
        })
    }
</script>

<%@include file="../common/down.jsp" %>
