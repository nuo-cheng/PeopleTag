<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
    <h3>
        <c:out value="${action}" /> comment
    </h3>

    <form method="POST" action="${destination}" >
            <label for="content">content</label>
            <input type="text" name="content" id="content"/>
        <button type="submit" class="btn btn-success">submit</button>
    </form>
</div>