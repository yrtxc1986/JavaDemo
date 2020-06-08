<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
 
<html lang="en">
   <head>
       <title>JSP</title>
   </head>
   <body>
    
      <h2>JSP Page</h2>
      <p>WEB-INF/jsp/testJsp.jsp</p>
      <p><c:out value="${Message}" /></p>
   </body>
</html>