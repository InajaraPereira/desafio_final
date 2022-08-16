package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.repository.CredicardRepository;
import br.com.meli.desafio_final.util.PaymentUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CredicardServiceTest {

    @InjectMocks
    private CredicardService credicardService;

    @Mock
    private CredicardRepository credicardRepository;

    @Test
    void validateCredicard() {
        BDDMockito.when(credicardRepository.save(PaymentUtils.newCredicard1ToSave()))
                .thenReturn(PaymentUtils.newCredicard1ToSave());
        assertThat(credicardService.validateCredicard(PaymentUtils.newCredicard1ToSave()));
    }

    @Test
    void validateCredicard_Exception01() {
        BDDMockito.when(credicardRepository.save(PaymentUtils.newCredicard1ToSave()))
                .thenReturn(PaymentUtils.newCredicard2ToSave());
        try {
            credicardService.validateCredicard(PaymentUtils.newCredicard2ToSave());
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage().equals("Cartão de crédito inválido."));
        }
    }

    @Test
    void validateCredicard_Exception02() {
        BDDMockito.when(credicardRepository.save(PaymentUtils.newCredicard1ToSave()))
                .thenReturn(PaymentUtils.newCredicard2ToSave());
        try {
            credicardService.validateCredicard(PaymentUtils.newCredicard3ToSave());
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage().equals("CVV inválido."));
        }
    }

    @Test
    void save() {
        BDDMockito.when(credicardRepository.save(PaymentUtils.newCredicard1ToSave()))
                .thenReturn(PaymentUtils.newCredicard1ToSave());
        credicardService.save(PaymentUtils.newCredicard1ToSave());
    }
}