package br.com.dolzanes.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dolzanes.forum.dto.TopicoDTO;
import br.com.dolzanes.forum.model.Topico;
import br.com.dolzanes.forum.repository.TopicoRepository;

@RestController
public class TopicsController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@RequestMapping("/topics")
	public List<TopicoDTO> lista(String nomeCurso){
		
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDTO.converter(topicos);
		}		
	}
}
