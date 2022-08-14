package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.entity.Buyer;
import br.com.meli.desafio_final.model.service.implementation.BuyerService;
import br.com.meli.desafio_final.model.repository.BuyerRepository;
import br.com.meli.desafio_final.util.BuyerUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BuyerServiceTest {

    @InjectMocks
    private BuyerService buyerService;

    @Mock
    private BuyerRepository buyerRepository;

    @Test
    public void testFindBuyerById() {
        Buyer buyer = BuyerUtils.newBuyer1ToSave();
        BDDMockito.when(buyerRepository.findById(buyer.getId()))
                .thenReturn(Optional.of(buyer));
        Buyer buyerByIdResponse = buyerService.findById(buyer.getId());

        Assertions.assertThat(buyerByIdResponse).isNotNull();
    }

    @Test
    public void testIfFindBatchByIdThrowsException() {
        Buyer buyer = BuyerUtils.newBuyer1ToSave();
        Exception exceptionResponse = null;
        BDDMockito.when(buyerRepository.findById(buyer.getId()))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            buyerService.findById(buyer.getId());
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Comprador não cadastrado.");
    }
}
