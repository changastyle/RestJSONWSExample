<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%
    
    ArrayList<model.Juego> arr = controller.Controller.findAllJuegosDisponibles();
    String json = new Gson().toJson(arr);
    
    out.println(json);
%>
