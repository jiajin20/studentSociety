
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%--<%@ include file="common/up.jsp" %>--%>

<%--<div class="panel">--%>
<%--    <button type="button" class="btn btn-default" onclick="showForm()">--%>
<%--        <i class="fa fa-plus-square"></i>--%>
<%--        申请协会--%>
<%--    </button>--%>
<%--    <style>--%>
<%--        .newsociety{--%>
<%--            display: none;--%>
<%--            position: fixed;--%>
<%--            width:600px;--%>
<%--            height:400px;--%>
<%--            top:calc((100% - 400px) / 2);--%>
<%--            left:calc((100% - 600px) / 2);--%>
<%--        }--%>
<%--    </style>--%>
<%--    <div class="panel newSociety">--%>
<%--        <div class="panel-heading">--%>
<%--            <h3 class="panel-title">新建协会申请</h3>--%>
<%--        </div>--%>
<%--        <div class="panel-body">--%>
<%--            <form action="society/addSociety" method="post">--%>
<%--                协会名：<input type="text" required class="form-control" name="name" placeholder="协会名"> <br>--%>
<%--                协会宗旨：<textarea required class="form-control" placeholder="协会宗旨" name="intro" rows="4"></textarea><br>--%>
<%--                <input type="submit" class="btn btn-primary btn-block" value="申请" onsubmit="submitForm()"/>--%>
<%--                <button type="button" class="btn btn-warning btn-block" onclick="shutDownForm()">取消</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="panel-body">--%>
<%--        <table class="table">--%>
<%--            <thead>--%>
<%--                <tr>--%>
<%--                    <th>序号</th>--%>
<%--                    <th>协会名</th>--%>
<%--                    <th>创始人</th>--%>
<%--                    <th>简介</th>--%>
<%--                    <th>创建时间</th>--%>
<%--                    <th>操作</th>--%>
<%--                </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <c:forEach items="${societies}" var="item" varStatus="status">--%>
<%--                <tr data-id="${item.societyId}">--%>
<%--                    <td>${status.count}</td>--%>
<%--                    <td name="name" onclick="changeValue(this)">${item. society_name}</td>--%>
<%--                    <td>${item.creator.studentName}</td>--%>
<%--                    <td name="intro" onclick="changeValue(this)">${item. society_intro}</td>--%>
<%--                    <td>--%>
<%--                        <fmt:formatDate type="date" value="${item. society_create_date}" pattern="yyyy-MM-dd"/>--%>
<%--                    </td>--%>
<%--                    <td><button class='btn ${item.society_status == 2 ? "btn-primary" : "btn-warning"}' data-status="${item. society_status}" onclick="changeStatus(this)">--%>
<%--                            ${item.society_status == 1 ? "停招" : "正常"}--%>
<%--                    </button></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>

<%--        <script>--%>
<%--            let element;--%>
<%--            function changeValue(ele){--%>
<%--                element = ele;--%>
<%--                let str = ele.innerHTML;--%>
<%--                if(/^<.*>$/.test(str)){--%>
<%--                    return false--%>
<%--                }--%>
<%--                let name = ele.getAttribute("name");--%>
<%--                const html = "<input name='"+name+"' value='"+str+"' onblur='show(this)'/>";--%>
<%--                ele.innerHTML = html;--%>
<%--                ele.firstElementChild.focus();--%>

<%--            }--%>

<%--            function show(){--%>
<%--                document.querySelector(".panel>.notice").style.display = "block";--%>
<%--                document.querySelector(".panel>.notice").firstElementChild.nextElementSibling.firstElementChild.onclick = submit;--%>
<%--                document.querySelector(".panel>.notice").firstElementChild.nextElementSibling.lastElementChild.onclick = shutdown;--%>
<%--            }--%>
<%--            function submit(){--%>
<%--                let parent = element.parentNode;--%>
<%--                let id = parent.dataset.id;--%>
<%--                let data = {id:id};--%>
<%--                data[element.getAttribute("name")] = element.firstElementChild.value;--%>
<%--                axios.post("/society/updateSociety",axios.transformRequest(data))--%>
<%--                    .then((map)=>{--%>
<%--                        element.innerHTML = element.firstElementChild.value;--%>
<%--                        if(map.data.message == 1){--%>
<%--                            alert("修改成功")--%>
<%--                        }else{--%>
<%--                            alert(map.data.message)--%>
<%--                        }--%>
<%--                    })--%>
<%--                    .catch((e)=>{--%>
<%--                        alert("异常信息"+e)--%>
<%--                    }).finally(() =>{--%>
<%--                        document.querySelector(".panel>.notice").style.display = "none";--%>
<%--                    })--%>
<%--            }--%>

<%--            function shutdown(){--%>
<%--                element.innerHTML = element.firstElementChild.value--%>
<%--                document.querySelector(".panel>.notice").style.display = "none";--%>
<%--            }--%>

<%--            function showForm(){--%>
<%--                document.querySelector(".newsociety").style.display = "block";--%>
<%--            }--%>
<%--            function submitForm(){--%>
<%--                let form = document.querySelector(".newSociety form");--%>
<%--                if(form.checkValidity()){--%>
<%--                    shutDownForm()--%>
<%--                }else{--%>
<%--                    alert("不能为空")--%>
<%--                }--%>
<%--                return form.checkValidity()--%>
<%--            }--%>
<%--            function shutDownForm(){--%>
<%--                document.querySelector(".newSociety").style.display = "none";--%>
<%--            }--%>

<%--            function changeStatus(ele){--%>
<%--                let id = ele.parentNode.parentNode.dataset.id;--%>
<%--                let status = ele.dataset.status--%>
<%--                let newStatus = status == 1 ? 2 : 1;--%>
<%--                axios.post("/society/changeStatus",axios.transformRequest({id:id,status:status}))--%>
<%--                    .then((number)=>{--%>
<%--                        if(number == 1){--%>
<%--                            alert("修改成功")--%>
<%--                        }--%>
<%--                    }).catch((e)=>{--%>
<%--                        alert(e)--%>
<%--                })--%>
<%--                if(newStatus == 2){--%>
<%--                    ele.className = "btn btn-primary";--%>
<%--                    ele.innerHTML = "正常"--%>

<%--                }else{--%>
<%--                    ele.className = "btn btn-warning";--%>
<%--                    ele.innerHTML = "停招"--%>
<%--                }--%>
<%--                ele.dataset.status = newStatus;--%>

<%--            }--%>
<%--        </script>--%>
<%--    </div>--%>
<%--    <style>--%>
<%--        .panel>.notice{--%>
<%--            display: none;--%>
<%--            position: fixed;--%>
<%--            height:300px;--%>
<%--            width:400px;--%>
<%--            top:calc((100% - 300px) / 2);--%>
<%--            left:calc((100% - 400px) / 2);--%>
<%--            border-radius: 10px;--%>
<%--        }--%>
<%--        .notice>h3{--%>
<%--            padding: 60px 0px;--%>
<%--            text-align: center;--%>
<%--        }--%>
<%--    </style>--%>
<%--    <div class="notice btn-primary">--%>
<%--        <h3>提交</h3>--%>
<%--        <div class="row">--%>
<%--            <button class="btn btn-success col-sm-offset-2 col-sm-4" onclick="submit()">Yes</button>--%>
<%--            <button class="btn btn-warning col-sm-4" onclick="shutdown()">no</button>--%>
<%--        </div>--%>

<%--    </div>--%>

</div>
<%@include file="common/down.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/up.jsp" %>

<div class="panel">
    <button type="button" class="btn btn-default" onclick="showForm()">
        <i class="fa fa-plus-square"></i>
        申请协会
    </button>
    <style>
        .newSociety {
            display: none;
            position: fixed;
            width: 600px;
            height: 400px;
            top: calc((100% - 400px) / 2);
            left: calc((100% - 600px) / 2);
        }
    </style>
    <div class="panel newSociety">
        <div class="panel-heading">
            <h3 class="panel-title">新建协会申请</h3>
        </div>
        <div class="panel-body">
            <form action="/studentSociety/society/addSociety" method="post" onsubmit="return submitForm();">
                协会名：<input type="text" required class="form-control" name="name" placeholder="协会名"> <br>
                协会宗旨：<textarea required class="form-control" placeholder="协会宗旨" name="intro" rows="4"></textarea><br>
                <input type="submit" class="btn btn-primary btn-block" value="申请" />
                <button type="button" class="btn btn-warning btn-block" onclick="shutDownForm()">取消</button>
            </form>
        </div>
    </div>

    <div class="panel-body">
        <table class="table">
            <thead>
            <tr>
                <th>序号</th>
                <th>协会名</th>
                <th>创始人</th>
                <th>简介</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${societies}" var="item" varStatus="status">
                <tr data-id="${item.societyId}">
                    <td>${status.count}</td>
                    <td data-name="name" onclick="changeValue(this)">${item.society_name}</td>
                    <td>${item.creator.studentName}</td>
                    <td data-name="intro" onclick="changeValue(this)">${item.society_intro}</td>
                    <td>
                        <fmt:formatDate type="date" value="${item.society_create_date}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <button class='btn ${item.society_status == 2 ? "btn-primary" : "btn-warning"}' data-status="${item.society_status}" onclick="changeStatus(this)">
                                ${item.society_status == 1 ? "停招" : "正常"}
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <script>
            let element;
            function changeValue(ele) {
                element = ele;
                let str = ele.innerHTML;
                if (/^<.*>$/.test(str)) {
                    return false;
                }
                let name = ele.getAttribute("data-name");
                const html = "<input name='" + name + "' value='" + str + "' onblur='show(this)'/>";
                ele.innerHTML = html;
                ele.firstElementChild.focus();
            }

            function show() {
                document.querySelector(".panel>.notice").style.display = "block";
                document.querySelector(".panel>.notice").firstElementChild.nextElementSibling.firstElementChild.onclick = submit;
                document.querySelector(".panel>.notice").firstElementChild.nextElementSibling.lastElementChild.onclick = shutdown;
            }

            function submit() {
                let parent = element.parentNode;
                let id = parent.dataset.id;
                let data = { id: id };
                data[element.getAttribute("data-name")] = element.firstElementChild.value;
                axios.post("/society/updateSociety", data)
                    .then((map) => {
                        element.innerHTML = element.firstElementChild.value;
                        if (map.data.message == 1) {
                            alert("修改成功");
                        } else {
                            alert(map.data.message);
                        }
                    })
                    .catch((e) => {
                        alert("异常信息: " + e.response.data);
                    }).finally(() => {
                    document.querySelector(".panel>.notice").style.display = "none";
                });
            }

            function shutdown() {
                element.innerHTML = element.firstElementChild.value;
                document.querySelector(".panel>.notice").style.display = "none";
            }

            function showForm(){
                document.querySelector(".newsociety").style.display = "block";
            }
            function submitForm(){
                let form = document.querySelector(".newSociety form");
                if(form.checkValidity()){
                    shutDownForm()
                }else{
                    alert("不能为空")
                }
                return form.checkValidity()
            }
            function shutDownForm(){
                document.querySelector(".newSociety").style.display = "none";
            }


            function changeStatus(ele) {
                let id = ele.parentNode.parentNode.dataset.id;
                let status = ele.dataset.status;
                let newStatus = status == 1 ? 2 : 1;
                axios.post("/society/changeStatus", { id: id, status: newStatus })
                    .then(response => {
                        if (response.data.message == 1) {
                            alert("修改成功");
                        }
                    })
                    .catch(e => {
                        alert("修改失败: " + e.response.data);
                    });

                if (newStatus == 2) {
                    ele.className = "btn btn-primary";
                    ele.innerHTML = "正常";
                } else {
                    ele.className = "btn btn-warning";
                    ele.innerHTML = "停招";
                }
                ele.dataset.status = newStatus;
            }
        </script>
    </div>
    <style>
        .panel>.notice {
            display: none;
            position: fixed;
            height: 300px;
            width: 400px;
            top: calc((100% - 300px) / 2);
            left: calc((100% - 400px) / 2);
            border-radius: 10px;
        }
        .notice>h3 {
            padding: 60px 0px;
            text-align: center;
        }
    </style>
    <div class="notice btn-primary">
        <h3>提交</h3>
        <div class="row">
            <button class="btn btn-success col-sm-offset-2 col-sm-4" onclick="submit()">Yes</button>
            <button class="btn btn-warning col-sm-4" onclick="shutdown()">No</button>
        </div>
    </div>
</div>
<%@ include file="common/down.jsp" %>
