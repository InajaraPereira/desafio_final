package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.Pix;
import br.com.meli.desafio_final.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PixService {

    @Autowired
    private PixRepository pixRepository;

    public boolean validatePix(Pix pix) {
        if (pix.getNumber().length() >= 11) {
            return true;
        }
        throw new BadRequest("Pix inválido.");
    }

    public Pix save(Pix pix){
        return pixRepository.save(pix);
    }
}
