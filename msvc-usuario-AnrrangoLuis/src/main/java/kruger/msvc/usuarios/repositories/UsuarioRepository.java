package kruger.msvc.usuarios.repositories;

import org.springframework.data.repository.CrudRepository;

import kruger.msvc.usuarios.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
}
