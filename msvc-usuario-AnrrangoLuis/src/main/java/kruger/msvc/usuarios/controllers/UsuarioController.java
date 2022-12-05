package kruger.msvc.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kruger.msvc.usuarios.models.entity.Usuario;
import kruger.msvc.usuarios.services.UsuarioService;

@RestController
@RequestMapping("/kruger/")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public List<Usuario> listar() {
		return service.listar();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = service.porId(id);
		if (usuarioOptional.isPresent()) {
			return ResponseEntity.ok(usuarioOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Long id) {
		Optional<Usuario> o = service.porId(id);
		if (o.isPresent()) {
			Usuario usuarioDb = o.get();
			usuarioDb.setNombre(usuario.getNombre());
			usuarioDb.setEmail(usuario.getEmail());
			usuarioDb.setPassword(usuario.getPassword());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));

		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<?> eliminar(@PathVariable Long id) {
		Optional<Usuario> o = service.porId(id);
		if(o.isPresent()) {
			service.eliminar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}
