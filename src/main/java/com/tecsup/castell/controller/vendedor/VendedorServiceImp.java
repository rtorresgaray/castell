package com.tecsup.castell.controller.vendedor;

import com.tecsup.castell.dao.PersonaDAO;
import com.tecsup.castell.dao.UsuarioDAO;
import com.tecsup.castell.dao.VendedorDAO;
import com.tecsup.castell.helper.EstadoEnum;
import com.tecsup.castell.helper.RolEnum;
import com.tecsup.castell.mail.MailerService;
import com.tecsup.castell.model.Persona;
import com.tecsup.castell.model.Usuario;
import com.tecsup.castell.model.Vendedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Obligatorio porque no va a poder identificar que es un service
@Service
// indica por default es de escritura y permite hacer commit y rollback
@Transactional
public class VendedorServiceImp implements VendedorService {

    @Autowired
    VendedorDAO vendedorDAO;

    @Autowired
    PersonaDAO personaDAO;

    @Autowired
    UsuarioDAO usuarioDAO;
    
    @Autowired
    MailerService mailer;

    @Override
    public List<Vendedor> allVendedor() {
        return vendedorDAO.all();
    }

    @Override
    public Persona findVendedor(Long id) {
        return vendedorDAO.find(id).getPersona();
    }

    @Override
    public void save(Persona persona) {
        if (persona.getId() == null) {
            personaDAO.save(persona);

            Vendedor vendedor = new Vendedor();
            vendedor.setPersona(persona);
            // es recomedable usar estados en string y mayusculas
            // Usar clase numeradas no archivos de parametros
            vendedor.setEstado(EstadoEnum.ACTIVO.toString());
            vendedorDAO.save(vendedor);

            Usuario usuario = new Usuario();
            usuario.setPersona(persona);
            usuario.setEstado(EstadoEnum.ACTIVO.toString());
            usuario.setUsername(persona.getEmail());
            usuario.setPassword(persona.getEmail());
            usuario.setRol(RolEnum.VENTA.toString());
            usuarioDAO.save(usuario);
            
            mailer.sendMail(persona.getEmail(), "CASTELL CRM - USUARIO", "<h1>PRUEBA</h1>");
            
        } else {
            personaDAO.update(persona);

            Usuario usuario = persona.getUsuario();
            usuario.setUsername(persona.getEmail());
            usuarioDAO.update(usuario);
        }
    }

    @Override
    public void delete(Long id) {
        Vendedor vendedor = vendedorDAO.find(id);
        Persona persona = vendedor.getPersona();
        Usuario usuario = persona.getUsuario();

        usuarioDAO.delete(usuario);
        personaDAO.delete(persona);
        vendedorDAO.delete(vendedor);
    }

    @Override
    public void changeEstado(Long id) {
        Vendedor vendedor = vendedorDAO.find(id);
        Usuario usuario = vendedor.getPersona().getUsuario();

        if (vendedor.getEstado().equals(EstadoEnum.ACTIVO.toString())) {

            vendedor.setEstado(EstadoEnum.INACTIVO.toString());
            usuario.setEstado(EstadoEnum.INACTIVO.toString());

        } else {
            
            vendedor.setEstado(EstadoEnum.ACTIVO.toString());
            usuario.setEstado(EstadoEnum.ACTIVO.toString());
        }
        
        vendedorDAO.update(vendedor);
        usuarioDAO.update(usuario);

    }

}
