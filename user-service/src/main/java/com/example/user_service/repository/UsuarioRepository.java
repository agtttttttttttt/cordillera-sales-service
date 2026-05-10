package com.example.user_service.repository;

import com.example.user_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByRolId(Long rolId);
    List<Usuario> findByEstado(String estado);
}
