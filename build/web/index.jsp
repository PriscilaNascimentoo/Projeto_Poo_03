<%-- 
    Document   : index
    Created on : 23/06/2020, 22:24:03
    Author     : prisc
--%>
<%@page import="model.Usuarios"%>
<%@page import="database.Boot_Table"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Exception requestException = null;
    Boot_Table.exceptionMessage = "";

    if (request.getParameter("login") != null) {
        String login = request.getParameter("user.login");
        String password = request.getParameter("user.password");

        try {
            Usuarios user = Usuarios.buscaUsuario(login, password);
            session.setAttribute("user.name", user.getName());
            session.setAttribute("user.login", user.getLogin());

            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            Boot_Table.exceptionMessage = e.getMessage();
        }
    }

    if (request.getParameter("cadastro") != null) {
        String name = request.getParameter("cadastro.name");
        String login = request.getParameter("cadastro.login");
        String password = request.getParameter("cadastro.password");

        try {
            Usuarios.addUsuario(name, login, password);
            response.sendRedirect("home.jsp");

            session.setAttribute("user.name", name);
            session.setAttribute("user.login", login);
        } catch (Exception e) {
            Boot_Table.exceptionMessage = e.getMessage();
        }
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
   <%@include file="WEB-INF/jpsf/menu.jspf" %>
        <div class="col">
            <form method="post">

                <div class="form-group">
                    <label>Nome Completo</label>
                    <input type="text" class="form-control" name="cadastro.name" >
                </div>

                <div class="form-group">
                    <label>Usuário</label>
                    <input type="text" class="form-control" name="cadastro.login" >
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="cadastro.password" >
                </div>

                <button type="submit" name="cadastro" class="btn btn-primary">Cadastrar</button>
            </form>
        </div>


    </body>
</html>
