package com.tecsup.castell.controller.categoria;

import com.tecsup.castell.dao.CategoriaDAO;
import com.tecsup.castell.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    CategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> all() {
        return categoriaDAO.all();
    }

    @Override
    public Categoria find(Long id) {
        return categoriaDAO.find(id);
    }

    @Override
    public void save(Categoria categoria) {
        if (categoria.getId() == null) {
            categoriaDAO.save(categoria);
        } else {
            categoriaDAO.update(categoria);
        }
    }

    @Override
    public void delete(Long id) {
        Categoria cat = this.find(id);
        categoriaDAO.delete(cat);
    }

}
