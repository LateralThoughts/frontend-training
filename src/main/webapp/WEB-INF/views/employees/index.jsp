<%@ include file="../fragments/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/views/fragments/commonMeta.jsp" />
    <c:import url="/WEB-INF/views/fragments/commonStyle.jsp" />
</head>
<body>
<div class="container-fluid">
    <c:import url="/WEB-INF/views/fragments/nav.jsp" />
    <div class="row-fluid">
        <div class="offset1 span10">
            <div class="hero-unit">
                <h1>Employees</h1>
                <blockquote>
                    <p>If a man loves the labour of his trade, apart from any question of success or fame, the gods have called him.</p>
                    <small>Robert Louis Stevenson</small>
                </blockquote>
                <p>
                    <a href="<c:url value='/employees/new' />" class="btn btn-primary btn-large">
                        Add a new one
                    </a>
                </p>
            </div>
            <c:choose>
                <c:when test="${empty employees}">
                <div class="alert">
                    <strong>Hmmm!</strong> No employees yet.
                </div>
                </c:when>
                <c:otherwise>
                    <ul class="unstyled">
                        <c:forEach var="employee" items="${employees}">
                            <li>
                                <strong><c:out value='${employee.firstName} ${fn:toUpperCase(employee.lastName)}' /> (<a href="<c:url value='/employees/${employee.id}/delete' />">
                                    <i class="icon-trash"></i>Delete
                                </a>)</strong>
                            </li>
                            <li>
                                Employed by <strong><c:out value='${employee.employer.name}' /></strong>
                            </li>
                        </c:forEach>
                    </ul>
                    <c:if test="${lastPage > 1}">
                        <div class="btn-toolbar">
                            <div class="btn-group">
                                <c:forEach var="page" begin="1" end="${lastPage}">
                                    <a href="<c:url value='/employees?page=${page}' />" class="btn">${page}</a>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>