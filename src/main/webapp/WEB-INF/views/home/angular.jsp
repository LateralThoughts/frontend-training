<%@ include file="../fragments/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <c:import url="/WEB-INF/views/fragments/commonMeta.jsp" />
        <c:import url="/WEB-INF/views/fragments/commonStyle.jsp" />
        <c:import url="/WEB-INF/views/fragments/commonScript.jsp" />
    </head>
    <body data-ng-controller="ZuprTrackrMain">
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
                    <div>

                        <form class="form-horizontal well">
                            <div class="control-group">
                                <label class="control-label">Filter on any property... </label>
                                <div class="controls">
                                    <!-- TODO: add data-ng-model="query" here -->
                                    <input class="input-medium search-query" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">...then filter on first name</label>
                                <div class="controls">
                                    <!-- TODO: add data-ng-model="query2" here -->
                                    <input class="input-medium search-query" />
                                </div>
                            </div>
                        </form>

                        <div data-ng-cloak>
                            <!-- TODO:
                                1. chain (aka |) elements to `filter` with `query` as argument
                                2. do the same with your custom filter `filterFirstName` with `query2`
                            -->
                            <ul data-ng-repeat="element in elements">
                                <li>{{element.employee.firstName}} {{element.employee.lastName}},
                                    employed by {{element.employee.employer.name}}
                                    <ul>
                                        <li>{{element.rate|currency:"EUR"}}</li>
                                        <li>{{element.day}}, {{element.client.name}}</li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            require(['jquery', 'modules/angularjs/angularHelper'], function($, helper) {
                $(document).ready(function() {
                    helper.bootstrap(['zuprTrackr']);
                });
            });
        </script>
    </body>
</html>