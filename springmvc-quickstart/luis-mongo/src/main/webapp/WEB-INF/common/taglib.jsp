<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="css" value="${pageContext.request.contextPath}/static/css" />
<c:set var="img" value="${pageContext.request.contextPath}/static/img" />
<c:set var="js" value="${pageContext.request.contextPath}/static/js" />
<c:set var="adminlte"
	value="${pageContext.request.contextPath}/static/adminlte" />
<c:set var="webinf" value="/WEB-INF" />
<script type="text/javascript" charset="utf-8">
		window.ctx = '${ctx}';
</script>
<script type="text/javascript" charset="utf-8" src="${js }/common.js">
</script>

