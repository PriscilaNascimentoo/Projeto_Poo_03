<%-- 
    Document   : index
    Created on : 23/06/2020, 22:24:03
    Author     : prisc
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Questao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int pergunta = 0;
    int numero = 0;
    int resultado = -1;
    
    ArrayList<Questao>questoesList = new ArrayList<>();
    questoesList = Questao.listaQuestoes();
    
    if (request.getParameter("salva_quiz") != null) {
        String usuario = request.getParameter("usuario");
        resultado = 0;

        int qntQuestoes = questoesList.size();

        for (int i = 0; i < qntQuestoes; i++) {
            String respostaCerta = questoesList.get(i).getAnswer();
            String respostaUsuario = request.getParameter("pergunta_" + pergunta);

            if (respostaUsuario != null) {
                if (respostaUsuario.equals(respostaCerta)) {
                    resultado++;
                }
            }

            pergunta++;

            // inserir no banco de dados, resultado
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv = "Tipo de conteúdo" content = "text / html; charset = UTF-8">
        <%@include file = "WEB-INF/jspf/header.jspf"%>
        <title>Quiz - Teste seus conhecimentos</title>
    </head>
    <body>
        <div class="container">

            <%@include file="WEB-INF/jspf/menu.jspf" %>
            <h2 class="text-center">Teste seus conhecimentos</h2>

            <hr/>
            
            <% if (resultado> -1) {%>
            <h2> Resultado: <%=resultado%> </h2>
            <%}%>

            <form method = "post">

                <input type = "hidden" name= "usuario" value= "<%=session.getAttribute("user.name")%>" />

                <% for(Questao q: questoesList) {%>
                <p><%=q.getEnunciated()%></p>
                <input name= "pergunta_<%= pergunta%>" type= "radio" value=<%=numero=q.geraNumeroRandom()%>>
                <label for="<%=numero%>"> <%=numero%></label> <br>

                <input name= "pergunta_<%=pergunta%>" type="radio" value=<%=numero=q.geraNumeroRandom()%>>
                <label for="<%=numero%>"> <%=numero%></label> <br>

                <input name="pergunta_<%=pergunta%>" type= "radio" value= <%=numero=q.geraNumeroRandom()%>>
                <label for="<%=numero%>"> <%=numero%> </label> <br>

                <input name= "pergunta_<%=pergunta%>" type= "radio" value= <%=numero=q.geraNumeroRandom()%>>
                <label for="<%=numero%>"> <%=numero%> </label> <br>

                <input name="pergunta_<%= pergunta%>" type="radio" value=<%=q.getAnswer()%>>
                <label for="<%=q.getAnswer()%>"> <%=q.getAnswer()%> </label> <br> <br>

                <br>  
                <hr />
                <%pergunta = pergunta + 1;
                    }%>

                <button class= "btn btn-primary" name= "salva_quiz"> Enviar </button>

            </form>
        </div>


        <a href="home.jsp">Voltar</a>
    </body>
</html>