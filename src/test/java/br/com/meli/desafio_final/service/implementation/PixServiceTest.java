package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.repository.PixRepository;
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
class PixServiceTest {

    @InjectMocks
    private PixService pixService;

    @Mock
    private PixRepository pixRepository;

    @Test
    void validatePix() {
        BDDMockito.when(pixRepository.save(PaymentUtils.newPixtoSave()))
                .thenReturn(PaymentUtils.newPixtoSave());
        assertThat(pixService.validatePix(PaymentUtils.newPixtoSave()));
    }

    @Test
    void validatePix_Exception() {
        BDDMockito.when(pixRepository.save(PaymentUtils.newPixtoSave()))
                .thenReturn(PaymentUtils.newPixtoSave());
        try {
            assertThat(pixService.validatePix(PaymentUtils.newPix1toSave()));
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage().equals("Pix inválido."));
        }
    }

    @Test
    void save() {
        BDDMockito.when(pixRepository.save(PaymentUtils.newPixtoSave()))
                .thenReturn(PaymentUtils.newPixtoSave());
        pixService.save(PaymentUtils.newPixtoSave());
    }
}