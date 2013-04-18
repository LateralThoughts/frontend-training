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
                autofocus.focus('#activitiesForm');
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
                <h1>Activity</h1>
                <blockquote>
                    <p>If you're not part of the solution, there's good money to be made in prolonging the problem.</p>
                    <small>Anonymous</small>
                </blockquote>
            </div>
            <form:form cssClass="form-horizontal well" modelAttribute="activity" id="activitiesForm">
                <legend>Activity</legend>
                <div class="control-group">
                    <form:label path="employee" cssClass="control-label">Employee</form:label>
                    <div class="controls">
                        <form:select path="employee" cssClass="pull-left" id="employees">
                            <form:option value="-1" label="select an employee..." />
                            <form:options items="${employees}" itemLabel="completeName" itemValue="id" />
                        </form:select>
                        <form:errors path="employee" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <form:label path="client" cssClass="control-label">Client</form:label>
                    <div class="controls">
                        <form:select path="client" cssClass="pull-left" id="clients">
                            <form:option value="-1" label="select a client..." />
                            <form:options items="${companies}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="client" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <form:label path="day" cssClass="control-label">Day</form:label>
                    <div class="controls">
                        <form:input path="day" type="date" cssClass="pull-left" placeholder="Day" />
                        <form:errors path="day" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <form:label path="rate" cssClass="control-label">Daily rate (EUR)</form:label>
                    <div class="controls">
                        <form:input path="rate" cssClass="pull-left" placeholder="Daily rate" />
                        <form:errors path="rate" cssClass="form-invalid alert alert-error pull-left" />
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