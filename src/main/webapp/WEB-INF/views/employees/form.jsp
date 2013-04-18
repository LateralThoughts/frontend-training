<%@ include file="../fragments/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/views/fragments/commonMeta.jsp" />
    <c:import url="/WEB-INF/views/fragments/commonStyle.jsp" />
    <c:import url="/WEB-INF/views/fragments/commonScript.jsp" />
    <script type="text/javascript">
        require(['jquery', 'modules/autofocus'], function($, autofocus) {
            $(document).ready(function() {
                autofocus.focus('#employeesForm');
            });
        });
    </script>
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
            </div>
            <form:form cssClass="form-horizontal well" modelAttribute="employee" id="employeesForm">
                <legend>Employee</legend>
                <div class="control-group">
                    <form:label path="firstName" cssClass="control-label">First name</form:label>
                    <div class="controls">
                        <form:input path="firstName" cssClass="pull-left" placeholder="First name" />
                        <form:errors path="firstName" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <form:label path="lastName" cssClass="control-label">Last name</form:label>
                    <div class="controls">
                        <form:input path="lastName" cssClass="pull-left" placeholder="Last name" />
                        <form:errors path="lastName" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <form:label path="employer" cssClass="control-label">Employer</form:label>
                    <div class="controls">
                        <form:select path="employer" cssClass="pull-left">
                            <form:option value="select an employer..." />
                            <form:options items="${companies}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="employer" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input class="btn" type="submit" value="Store" />
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>