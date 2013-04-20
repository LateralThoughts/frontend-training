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
                        <!--<ul data-ng-repeat="element in elements">
                            TODO: iterate over nested properties of element
                        </ul>-->
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