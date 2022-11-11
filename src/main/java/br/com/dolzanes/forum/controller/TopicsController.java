package br.com.dolzanes.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dolzanes.forum.dto.TopicoDTO;
import br.com.dolzanes.forum.model.Curso;
import br.com.dolzanes.forum.model.Topico;

@RestController
public class TopicsController {

	@RequestMapping("/topics")
	public List<TopicoDTO> listAll(){
		Topico topico = new Topico("Duvidas", "Duvida com Spring", new Curso("Java", "Programação"));
		return TopicoDTO.converter(Arrays.asList(topico));
	}
}
