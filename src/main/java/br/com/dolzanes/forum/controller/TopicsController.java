package br.com.dolzanes.forum.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dolzanes.forum.controller.form.TopicoForm;
import br.com.dolzanes.forum.dto.TopicoDTO;
import br.com.dolzanes.forum.model.Topico;
import br.com.dolzanes.forum.repository.CursoRepository;
import br.com.dolzanes.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;

	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso){
		
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDTO.converter(topicos);
		}		
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
}
