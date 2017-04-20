package com.miss.domain;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByMail(String mail);
}
