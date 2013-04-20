<%@ include file="taglib.jsp"%>
<div class="navbar">
    <div class="navbar-inner">
        <a class="brand" href="<c:url value='/' />">Zupr Trackr</a>
        <ul class="nav nav-pills">
            <li><a href="<c:url value='/?framework=angular' />">AngularJS</a></li>
            <li><a href="<c:url value='/companies' />">Company administration</a></li>
            <li><a href="<c:url value='/employees' />">Employee administration</a></li>
            <li><a href="<c:url value='/activities/new' />">Activity administration</a></li>
        </ul>
    </div>
</div>