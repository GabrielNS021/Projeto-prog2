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
public class DisciplinasController {

    @Autowired
    private DisciplinasRepo disciplinasRepo;

    @GetMapping("/api/disciplinas")
    Iterable<Disciplinas> getDisciplinas() {
        return disciplinasRepo.findAll();
    }

    @GetMapping("/api/disciplinas/{id}")
    Optional<Disciplinas> getDisciplinas(@PathVariable long id) {
        return disciplinasRepo.findById(id);
    }

    @PostMapping("/api/disciplinas")
    Disciplinas createDisciplinas(@RequestBody Disciplinas f) {
        Disciplinas crF = disciplinasRepo.save(f);
        return crF;
    }

    @PutMapping("/api/discplinas/{disciplinasId}")
	Optional<Disciplinas> updateDisciplina(@RequestBody Disciplinas disciplinasReq, @PathVariable long disciplinasId) {
		Optional<Disciplinas> opt = disciplinasRepo.findById(disciplinasId);
		if (opt.isPresent()) {
			if (disciplinasReq.getId() == disciplinasId) {
				disciplinasRepo.save(disciplinasReq);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da disciplinas com id " + disciplinasId);
	}	

    @DeleteMapping("/api/disciplinas/{id}")
    void deleteDisciplinas(@PathVariable long id) {
        disciplinasRepo.deleteById(id);
    }
}
