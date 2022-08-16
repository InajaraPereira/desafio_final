package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.Credicard;
import br.com.meli.desafio_final.repository.CredicardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.regex.Pattern;

@Service
public class CredicardService {

    @Autowired
    private CredicardRepository credicardRepository;

    public boolean validateCredicard(Credicard credicard) {
        if (credicard.getValidate().isAfter(LocalDate.now())) {
            if (credicard.getCredicardNumber().length() >= 13 && credicard.getCredicardNumber().length() <=16) {
                if (isValidCVVNumber(credicard.getSecurityNumber())) {
                    credicard.setSecurityNumber(encondedCarNumber(credicard.getSecurityNumber()));
                    return true;
                }
            }
        }
        throw new BadRequest("Cartão de crédito inválido.");
    }

    private String encondedCarNumber(String securityNumber) {
        return new String(Base64.getEncoder().encode(securityNumber.getBytes()));
    }

    private boolean isValidCVVNumber(String securityNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{3,4}$");
        if (pattern.matcher(securityNumber).matches()) {
            return true;
        }
        throw new BadRequest("CVV inválido.");
    }

    public Credicard save(Credicard credicard){
        return credicardRepository.save(credicard);
    }
}
