package com.example.user_service.service;

import com.example.user_service.model.Usuario;
import com.example.user_service.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public Usuario findByRut(String rut) {
        return repository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con rut: " + rut));
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    public List<Usuario> findByRolId(Long rolId) {
        return repository.findByRolId(rolId);
    }

    public List<Usuario> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario existing = findById(id);
        existing.setRut(usuario.getRut());
        existing.setNombre(usuario.getNombre());
        existing.setEmail(usuario.getEmail());
        existing.setPassword(usuario.getPassword());
        existing.setTelefono(usuario.getTelefono());
        existing.setRol(usuario.getRol());
        existing.setSucursalId(usuario.getSucursalId());
        existing.setEstado(usuario.getEstado());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Usuario login(String rut, String password) {
        Usuario usuario = findByRut(rut);
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return usuario;
    }
}
