package ps2.restapidb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@GetMapping("/api/usuarios")
	Iterable<Usuario> getUsuarios() {
		return usuarioRepo.findAll();
	}
	
	@GetMapping("/api/usuarios/{id}")
	Optional<Usuario> getUsuario(@PathVariable long id) {
		return usuarioRepo.findById(id);
	}
	
	@PostMapping("/api/usuarios")
	Usuario createUsuario(@RequestBody Usuario f) {
		Usuario createUsuario = usuarioRepo.save(f);
		return createUsuario;
	}
	
	@PutMapping("/api/usuarios/{usuarioId}")
	Optional<Usuario> updateUsuario(@RequestBody Usuario usuarioReq, @PathVariable long usuarioId) {
		Optional<Usuario> opt = usuarioRepo.findById(usuarioId);
		if (opt.isPresent()) {
			if (usuarioReq.getId() == usuarioId) {
				usuarioRepo.save(usuarioReq);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do usuario com id " + usuarioId);
	}	
	
	@DeleteMapping("/api/usuario/{id}")
	void deleteUsuario(@PathVariable long id) {
		usuarioRepo.deleteById(id);
	}	
	
}