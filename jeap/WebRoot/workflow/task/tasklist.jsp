<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="/jeap/workflow/workflow.js"></script>
<table cellspacing="0" cellpadding="0" border="0" width="100%">
    <tbody>
    <c:if test="${fn:length(result) eq 0}">
        <tr>
            <td>无待办任务！</td>
        </tr>
    </c:if>
    <c:if test="${fn:length(result) gt 0}">

        <c:forEach items="${result}" var="entry">
            <tr>

                <td>
                    ${entry['pdname']}->PID:${entry['pid']}->${entry['name']}V:${entry['pdversion']}
                    <a class='trace' href='#' pid='" + ${entry['pid']}+ "' title='点击查看流程图' onclick="graphTrace();">跟踪</a>
                        <c:if test="${entry['status'] eq 'claim'}">
                            未签收
                        </c:if>

                </td>
            </tr>
        </c:forEach>

    </c:if>
    </tbody>
</table>

<body>

</body>