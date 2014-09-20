package com.tecsup.castell.controller.categoria;

import com.tecsup.castell.model.Categoria;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("categoria")
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("categorias", service.all());
        return "categoria/index";
    }

    @RequestMapping("create")
    public String create(Model model) {

        model.addAttribute("categoria", new Categoria());
        return "categoria/formulario";
    }

    @RequestMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        Categoria categoria = service.find(id);
        if (categoria==null){
            return "redirect:/categoria";
        }
        model.addAttribute("categoria", categoria);

        return "categoria/formulario";
    }

    @RequestMapping("save")
    public String save(@ModelAttribute Categoria categoria, Model model, BindingResult result) {

        service.save(categoria);
        return "redirect:/categoria";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        service.delete(id);
        return "redirect:/categoria";
    }

}
