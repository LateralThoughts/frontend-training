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
                autofocus.focus('#companiesForm');
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
                <h1>Companies <small>clients &amp; employers</small></h1>
                <blockquote>
                    <p>The lack of money is the root of all evil.</p>
                    <small>Mark Twain</small>
                </blockquote>
            </div>
            <form:form cssClass="form-horizontal well" modelAttribute="company" id="companiesForm">
                <legend>Company</legend>
                <div class="control-group">
                    <form:label path="name" cssClass="control-label">Name</form:label>
                    <div class="controls">
                        <form:input path="name" cssClass="pull-left" placeholder="Company name" />
                        <form:errors path="name" cssClass="form-invalid alert alert-error pull-left" />
                    </div>
                </div>
                <div class="control-group">
                    <form:label path="address" cssClass="control-label">Address</form:label>
                    <div class="controls">
                        <form:textarea path="address" cssClass="pull-left" placeholder="Company address" rows="3" />
                        <form:errors path="address" cssClass="form-invalid alert alert-error pull-left" />
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