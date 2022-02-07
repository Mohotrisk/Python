package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "Manu Rosa", "manug@email.com.br", "654321", "  "));
		usuarioRepository.save(new Usuario(0L, "Andre Rosa", "andremart@email.com.br", "765432"," "));
		usuarioRepository.save(new Usuario(0L, "Rafa Rosa", "rafarosa@email.com.br", "876543",""));
		usuarioRepository.save(new Usuario(0L, "Paul Alien", "paulalien@email.com.br", "098765",""));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("moises@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("moises@email.com.br"));
		
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Rosa");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Manu Rosa"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Andre Rosa"));	
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Rafa Rosa"));
		
	}

}
