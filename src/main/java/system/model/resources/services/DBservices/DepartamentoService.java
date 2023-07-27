package system.model.resources.services.DBservices;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import system.model.entity.Departamento;
import system.model.repositorys.DepartamentoRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public List<Departamento> findAll() {

        List<Departamento> listaDepartamento = departamentoRepository.findAll();
        if (!listaDepartamento.isEmpty()) {
            return listaDepartamento;
        }

        return null;
    }

    public Page<Departamento> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return this.departamentoRepository.findAll(pageable);
    }

    public Departamento findById(Integer id) {

        return departamentoRepository.findById(id).orElse(null);
    }

    public Departamento save(@RequestBody @Valid Departamento departamento) {

        if (departamento != null) {
            return departamentoRepository.save(departamento);
        }

        return null;
    }

    public Departamento update(Integer id, @RequestBody @Valid Departamento departamento) {

        Departamento alterado = findById(id);

        if (alterado != null) {
            alterado.setNome(departamento.getNome());
            alterado.setEmail(departamento.getEmail());
            alterado.setTelefone(departamento.getTelefone());
            alterado.setResponsavel(departamento.getResponsavel());
            return departamentoRepository.save(alterado);
        }

        return null;
    }

    public String delete(Integer id) {

        Departamento departamento = findById(id);
        if (departamento != null) {
            departamentoRepository.delete(departamento);
            return "Nome: " + departamento.getNome() + ", email: " + departamento.getEmail() + ", excluido com sucesso.";

        }
        return null;
    }
}
