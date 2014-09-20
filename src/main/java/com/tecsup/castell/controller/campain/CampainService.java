package com.tecsup.castell.controller.campain;

import com.tecsup.castell.controller.categoria.*;
import com.tecsup.castell.model.Campain;
import com.tecsup.castell.model.Categoria;
import java.util.List;

public interface CampainService {

     List<Campain> all();
    
    Campain find(Long id);
    
    void save(Campain campain);
    
    void delete(Long id);
   
}
