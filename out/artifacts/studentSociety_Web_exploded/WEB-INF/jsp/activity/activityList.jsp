<%--
  Created by IntelliJ IDEA.
  User: wolfishplk
  Date: 2023/7/18
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/up.jsp" %>
<div class="panel">
    <div class="panel-heading">
        <span class="panel-title">协会活动列表</span>
    </div>
    <script>
        let element;
        function changeValue(ele){
            element = ele;
            let str = ele.innerHTML;
            if(/^<.*>$/.test(str)){
                return false
            }
            let name = ele.getAttribute("name");
            const html = "<input name='"+name+"' value='"+str+"' onblur='show(this)'/>";
            ele.innerHTML = html;
            ele.firstElementChild.focus();

        }

        function show(){
            document.querySelector(".panel>.notice").style.display = "block";
            document.querySelector(".panel>.notice").firstElementChild.nextElementSibling.firstElementChild.onclick = submit;
            document.querySelector(".panel>.notice").firstElementChild.nextElementSibling.lastElementChild.onclick = shutdown;
        }
        function submit(){
            let parent = element.parentNode;
            let id = parent.dataset.id;
            let data = {id:id};
            data[element.getAttribute("name")] = element.firstElementChild.value;
            axios.post("/activity/updateActivity",axios.transformRequest(data))
                .then((map)=>{
                    element.innerHTML = element.firstElementChild.value;
                    if(map.data == 1){
                        alert("修改成功")
                    }else{
                        alert(map.data.message)
                    }
                })
                .catch((e)=>{
                    alert("异常信息"+e)
                }).finally(() =>{
                document.querySelector(".panel>.notice").style.display = "none";
            })
        }

        function shutdown(){
            element.innerHTML = element.firstElementChild.value
            document.querySelector(".panel>.notice").style.display = "none";
        }
    </script>
    <div class="notice btn-primary">
        <h3>提交</h3>
        <div class="row">
            <button class="btn btn-success col-sm-offset-2 col-sm-4" onclick="submit()">Yes</button>
            <button class="btn btn-warning col-sm-4" onclick="shutdown()">no</button>
        </div>
    </div>
    <style>
        .panel>.notice{
            display: none;
            position: fixed;
            height:300px;
            width:400px;
            top:calc((100% - 300px) / 2);
            left:calc((100% - 400px) / 2);
            border-radius: 10px;
        }
        .notice>h3{
            padding: 60px 0px;
            text-align: center;
        }
    </style>
    <div class="panel-body">
        <table class="table">
            <thead>
                <th>序号</th>
                <th>活动名称</th>
                <th>活动简介</th>
                <th>开始时间</th>
                <th>结束时间</th>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<%@include file="../common/down.jsp" %>
