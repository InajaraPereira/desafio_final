package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.entity.Agent;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.entity.Seller;
import br.com.meli.desafio_final.model.service.implementation.ValidationService;
import br.com.meli.desafio_final.model.repository.AgentRepository;
import br.com.meli.desafio_final.model.repository.ProductRepository;
import br.com.meli.desafio_final.model.repository.SectionRepository;
import br.com.meli.desafio_final.model.repository.SellerRepository;
import br.com.meli.desafio_final.util.AgentUtils;
import br.com.meli.desafio_final.util.ProductUtils;
import br.com.meli.desafio_final.util.SectionUtils;
import br.com.meli.desafio_final.util.SellerUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ValidationServiceTeste {

    @InjectMocks
    private ValidationService validationService;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    AgentRepository agentRepository;

    @Test
    public void testValidateSection() {
        Section section = SectionUtils.newSectionFresh();
        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(section));
        validationService.validateSection(section);

        verify(sectionRepository, only()).findById(1L);
    }

    @Test
    public void testValidateSectionThrowsException() {
        Section section = SectionUtils.newSectionFresh();
        Exception testException = null;
        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateSection(section);
        } catch (Exception exception) {
            testException = exception;
        }

        assertThat(testException.getMessage()).isEqualTo("Section não encontrada");
    }

    @Test
    public void testValidateSeller() {
        Seller seller = SellerUtils.newSeller1ToSave();
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(seller));
        validationService.validateSeller(seller);

        verify(sellerRepository, only()).findById(1L);
    }

    @Test
    public void testValidateSellerThrowsException() {
        Seller seller = SellerUtils.newSeller1ToSave();
        Exception testException = null;
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateSeller(seller);
        } catch (Exception exception) {
            testException = exception;
        }

        assertThat(testException.getMessage()).isEqualTo("Seller não encontrada");
    }

    @Test
    public void testValidateProduct() {
        Product product = ProductUtils.newProduct1ToSave();
        BDDMockito.when(productRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(product));
        validationService.validateProduct(product);

        verify(productRepository, only()).findById(1L);
    }

    @Test
    public void testValidateProductThrowsException() {
        Product product = ProductUtils.newProduct1ToSave();
        Exception testException = null;
        BDDMockito.when(productRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateProduct(product);
        } catch (Exception exception) {
            testException = exception;
        }

        assertThat(testException.getMessage()).isEqualTo("Product não encontrada");
    }

    @Test
    public void testValidateAgent() {
        Agent agent = AgentUtils.newAgent();
        BDDMockito.when(agentRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(agent));
        validationService.validateAgent(agent.getId());

        verify(agentRepository, only()).findById(1L);
    }

    @Test
    public void testValidateAgentThrowsException() {
        Agent agent = AgentUtils.newAgent();
        Exception testException = null;
        BDDMockito.when(agentRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateAgent(agent.getId());
        } catch (Exception exception) {
            testException = exception;
        }

        assertThat(testException.getMessage()).isEqualTo("Representante não encontrado");
    }

}
