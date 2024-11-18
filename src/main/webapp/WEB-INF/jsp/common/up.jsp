<%--
  Created by IntelliJ IDEA.
  User: wolfishplk
  Date: 2023/7/14
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Home</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="/assets/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="/assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="/assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/assets/img/favicon.png">
</head>
<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="index.html"><img src="/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
            </div>
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" style="position: relative" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/assets/img/user.png" class="img-circle" alt="Avatar">
                            <span>${sessionScope.student}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/student/logout"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li><a href="mainIndex" class="active"><i class="lnr lnr-home"></i> <span>协会管理</span></a></li>
                    <li>
                        <a href="#memberSubPages" data-toggle="collapse" class="collapsed">
                            <i class="lnr lnr-user"></i>
                            <span>成员管理</span>
                        </a>
                        <div id="memberSubPages" class="collapse ">
                            <ul class="nav">
                                <li><a href="member/members?status=1" class="">成员列表</a></li>
                                <li><a href="member/addPage" class="">添加成员</a></li>

                                    <li><a href="member/members?status=3" class="">退出申请</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#activitySubPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-map"></i> <span>活动管理</span></a>
                        <div id="activitySubPages" class="collapse ">
                            <ul class="nav">
                                <li><a href="activity/addPage" class="">活动申请</a></li>
                                <li><a href="activity/activityList" class="">活动列表</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->
    <div class="main">