package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.dto.BatchesByProductDto;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.model.service.implementation.AdsenseService;
import br.com.meli.desafio_final.model.service.implementation.BatchService;
import br.com.meli.desafio_final.model.service.implementation.ProductService;
import br.com.meli.desafio_final.model.service.implementation.SectionService;
import br.com.meli.desafio_final.model.repository.ProductRepository;
import br.com.meli.desafio_final.util.AdsenseUtilsDto;
import br.com.meli.desafio_final.util.BatchDtoUtils;
import br.com.meli.desafio_final.util.ProductUtils;
import br.com.meli.desafio_final.util.SectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTeste {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SectionService sectionService;

    @Mock
    private AdsenseService adsenseService;

    @Mock
    private BatchService batchService;

    @Test
    public void testGetAllProducts() {
        BDDMockito.when(productRepository.findAll())
                .thenReturn(ProductUtils.productList());
        List<Product> productListResponse = productService.findAllProducts();

        assertThat(productListResponse.size()).isEqualTo(4);
        assertThat(productListResponse).isNotNull();
    }

    @Test
    public void testIfGetAllProductsThrowsException() {
        Exception exceptionResponse = null;
        BDDMockito.when(productRepository.findAll())
                .thenReturn(new ArrayList<>());
        try {
            productService.findAllProducts();
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Lista de produtos não encontrada");
    }

    @Test
    public void testGetProductByCategory() {
        BDDMockito.when(productRepository.findByCategory(Category.FRESH))
                .thenReturn(ProductUtils.productListFresh());
        List<Product> productListResponse = productService.findByCategory(Category.FRESH);

        assertThat(productListResponse.size()).isEqualTo(2);
        assertThat(productListResponse.get(0).getCategory()).isEqualTo(Category.FRESH);
        assertThat(productListResponse.get(1).getCategory()).isEqualTo(Category.FRESH);
    }

    @Test
    public void testIfGetProductByCategoryThrowsException() {
        Exception exceptionResponse = null;
        BDDMockito.when(productRepository.findByCategory(Category.FRESH))
                .thenReturn(new ArrayList<>());
        try {
            productService.findByCategory(Category.FRESH);
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Nenhum produto com essa categoria foi encontrado");
    }

    @Test
    public void testFindById() {
        Product product = ProductUtils.newProduct1ToSave();
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductUtils.newProduct1ToSave()));
        Product productResponse = productService.findById(1L);

        assertThat(productResponse).isNotNull();
        Assertions.assertEquals(productResponse.getId(), product.getId());
    }

    @Test
    public void testFindByIdThrowsException() {
        Exception exceptionResponse = null;
        BDDMockito.when(productRepository.findById(999L))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            productService.findById(999L);
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Produto inexistente");
    }

    @Test
    public void testFindBatchByProduct() {
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductUtils.newProduct3ToSave()));
        BDDMockito.when(sectionService.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(List.of(SectionUtils.newSectionFrozen()));
        BDDMockito.when(adsenseService.findByProductId(ArgumentMatchers.anyLong()))
                .thenReturn(AdsenseUtilsDto.generateAdsenseIdDtoList());
        BDDMockito.when(batchService.returnBatchStock(AdsenseUtilsDto.generateAdsenseIdDtoList(), null))
                .thenReturn(BatchDtoUtils.generateBatchDtoList());
        BatchesByProductDto batchesByProductDto = ProductUtils.bachesByProduct();
        BatchesByProductDto response = productService.findBatchByProduct(3L, null);

        assertThat(response).isNotNull();
        Assertions.assertEquals(response.getSectionId(), batchesByProductDto.getSectionId());
        Assertions.assertEquals(response.getWarehouseId(), batchesByProductDto.getWarehouseId());
        Assertions.assertEquals(response.getProductId(), batchesByProductDto.getProductId());
    }
}
