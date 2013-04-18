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
                        <h1>Zupr Trackr</h1>
                        <blockquote>
                            <p>Zupr Trackr will revolutionize the way you track your employees. <span class="muted small">(not)</span></p>
                        </blockquote>
                    </div>
                    <c:choose>
                        <c:when test="${empty activitiesByClientAndEmployee}">
                            <div class="alert">
                                <strong>Hmmm!</strong> Nothing yet.
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items='${activitiesByClientAndEmployee}' var='indexedActivity'>
                                <h2>Employer <em><c:out value='${indexedActivity.key.name}' /></em></h2>
                                <ul>
                                    <c:forEach items='${indexedActivity.value}' var='employeeActivities'>
                                        <li>
                                            <c:out value='${employeeActivities.key.completeName}' />
                                            <ul>
                                                <c:forEach items='${employeeActivities.value}' var="activities">
                                                    <li>client: <c:out value='${activities.key.name}' />
                                                        <ul>
                                                            <c:forEach items='${activities.value}' var="activity">
                                                                <fmt:formatDate value='${activity.day}' pattern='dd/MM/yyyy' var='date' />
                                                                <li><c:out value='${date}' /></li>
                                                                <li><c:out value='${activity.rate} EUR' /></li>
                                                                <li><a href="<c:url value='/activities/${activity.id}/delete' />">
                                                                    <i class="icon-trash"></i>Delete
                                                                </a></li>
                                                            </c:forEach>
                                                        </ul>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>