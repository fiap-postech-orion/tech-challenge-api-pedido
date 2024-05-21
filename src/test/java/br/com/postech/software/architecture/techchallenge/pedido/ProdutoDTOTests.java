package br.com.postech.software.architecture.techchallenge.pedido;

import br.com.postech.software.architecture.techchallenge.pedido.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ProdutoImagesDTO;
import br.com.postech.software.architecture.techchallenge.pedido.enums.CategoriaEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProdutoDTOTests {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testProdutoValido() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Produto Teste");
		produto.setCategoria(CategoriaEnum.LANCHE);
		produto.setValor(new BigDecimal("100.00"));
		produto.setDescricao("Descrição do produto teste.");

		List<ProdutoImagesDTO> imagens = new ArrayList<>();
		ProdutoImagesDTO imagem = new ProdutoImagesDTO();
		imagem.setPath("http://example.com/image1.jpg");
		imagens.add(imagem);

		produto.setImagens(imagens);

		Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
		assertTrue(violations.isEmpty(), "Esperado que não haja violações de restrição");
	}

	@Test
	public void testNomeNulo() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setCategoria(CategoriaEnum.LANCHE);
		produto.setValor(new BigDecimal("100.00"));
		produto.setDescricao("Descrição do produto teste.");

		List<ProdutoImagesDTO> imagens = new ArrayList<>();
		ProdutoImagesDTO imagem = new ProdutoImagesDTO();
		imagem.setPath("http://example.com/image1.jpg");
		imagens.add(imagem);

		produto.setImagens(imagens);

		Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
		assertEquals(1, violations.size(), "Esperado uma violação de restrição");
		assertEquals("não deve ser nulo", violations.iterator().next().getMessage());
	}

	@Test
	public void testCategoriaNula() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Produto Teste");
		produto.setValor(new BigDecimal("100.00"));
		produto.setDescricao("Descrição do produto teste.");

		List<ProdutoImagesDTO> imagens = new ArrayList<>();
		ProdutoImagesDTO imagem = new ProdutoImagesDTO();
		imagem.setPath("http://example.com/image1.jpg");
		imagens.add(imagem);

		produto.setImagens(imagens);

		Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
		assertEquals(1, violations.size(), "Esperado uma violação de restrição");
		assertEquals("não deve ser nulo", violations.iterator().next().getMessage());
	}

	@Test
	public void testValorInvalido() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Produto Teste");
		produto.setCategoria(CategoriaEnum.ACOMPANHAMENTO);
		produto.setValor(new BigDecimal("0"));
		produto.setDescricao("Descrição do produto teste.");

		List<ProdutoImagesDTO> imagens = new ArrayList<>();
		ProdutoImagesDTO imagem = new ProdutoImagesDTO();
		imagem.setPath("http://example.com/image1.jpg");
		imagens.add(imagem);

		produto.setImagens(imagens);

		Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
		assertEquals(1, violations.size(), "Esperado uma violação de restrição");
		assertEquals("deve ser maior que ou igual à 1", violations.iterator().next().getMessage());
	}

	@Test
	public void testDescricaoNula() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Produto Teste");
		produto.setCategoria(CategoriaEnum.BEBIDA);
		produto.setValor(new BigDecimal("100.00"));

		List<ProdutoImagesDTO> imagens = new ArrayList<>();
		ProdutoImagesDTO imagem = new ProdutoImagesDTO();
		imagem.setPath("http://example.com/image1.jpg");
		imagens.add(imagem);

		produto.setImagens(imagens);

		Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
		assertEquals(1, violations.size(), "Esperado uma violação de restrição");
		assertEquals("não deve ser nulo", violations.iterator().next().getMessage());
	}

	@Test
	public void testImagensVazia() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Produto Teste");
		produto.setCategoria(CategoriaEnum.SOBREMESA);
		produto.setValor(new BigDecimal("100.00"));
		produto.setDescricao("Descrição do produto teste.");

		produto.setImagens(new ArrayList<>());

		Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produto);
		assertEquals(1, violations.size(), "Esperado uma violação de restrição");
		assertEquals("não deve estar vazio", violations.iterator().next().getMessage());
	}
}
