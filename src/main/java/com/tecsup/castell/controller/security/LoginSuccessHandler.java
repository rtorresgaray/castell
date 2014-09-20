package com.tecsup.castell.controller.security;

import com.tecsup.castell.dao.UsuarioDAO;
import com.tecsup.castell.helper.AppConstant;
import com.tecsup.castell.model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {

        Usuario autenticado = usuarioDAO.findByLogin(a.getName());
        
        request.getSession().setAttribute(AppConstant.USUARIO_AUTENTICADO, autenticado.getPersona());
        
        setDefaultTargetUrl("/login");

        super.onAuthenticationSuccess(request, response, a);
    }

}
