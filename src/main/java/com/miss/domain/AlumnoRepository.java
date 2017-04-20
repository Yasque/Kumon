package com.miss.domain;

import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> { //crud extiende de crud repository
	Alumno findByNivel(String nivel);
}
