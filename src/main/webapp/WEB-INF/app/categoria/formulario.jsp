<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categoría</title>
        <%@include file="/public/header.jsp" %>
    </head>
    <body>
        <%@include file="/public/topbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <%@include file="/public/menu.jsp" %>

                <!-- CONTENIDO -->
                <section class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <h1 class="page-header">Nuevo Categoría</h1>

                    <form method="post" action="<%=request.getContextPath()%>/categoria/save" class="form-horizontal">

                        <input type="hidden" value="${categoria.id}" name="id">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Nombre</label>

                            <div class="col-sm-10">
                                <input type="text" name="nombre" class="form-control" value="${categoria.nombre}" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Guardar</button>
                                <a class="btn btn-link" href="<%=request.getContextPath()%>/categoria"> Cancelar</a>
                            </div>
                        </div>
                    </form>

                </section>
                <!-- CONTENIDO -->
            </div>
        </div>
    </body>
    <%@include file="/public/footer.jsp" %>
</html>
