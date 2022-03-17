package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Color;
import mx.edu.utez.adoptaMe.repository.ColorRepository;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> listAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color save() {
        return null;
    }
    
    @Override
    public Color edit() {
        return null;
    }

    @Override
    public Color update() {
        return null;
    }
    
    @Override
    public void delete() {

    }


}
