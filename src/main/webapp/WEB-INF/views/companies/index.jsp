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
                <h1>Companies <small>clients &amp; employers</small></h1>
                <blockquote>
                    <p>The lack of money is the root of all evil.</p>
                    <small>Mark Twain</small>
                </blockquote>
                <p>
                    <a href="<c:url value='/companies/new' />" class="btn btn-primary btn-large">
                        Add a new one
                    </a>
                </p>
            </div>
            <c:choose>
                <c:when test="${empty companies}">
                <div class="alert">
                    <strong>Hmmm!</strong> No companies yet.
                </div>
                </c:when>
                <c:otherwise>
                    <ul class="unstyled">
                        <c:forEach var="company" items="${companies}">
                            <li>
                                <strong><c:out value='${company.name}' /> (<a href="<c:url value='/companies/${company.id}/delete' />">
                                    <i class="icon-trash"></i>Delete
                                </a>)</strong>
                            </li>
                            <li>
                                <address>
                                    <c:out value='${company.address}' />
                                </address>
                            </li>
                        </c:forEach>
                    </ul>
                    <c:if test="${lastPage > 1}">
                        <div class="btn-toolbar">
                            <div class="btn-group">
                                <c:forEach var="page" begin="1" end="${lastPage}">
                                    <a href="<c:url value='/companies?page=${page}' />" class="btn">${page}</a>
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