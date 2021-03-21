package br.com.ramon.barros.repo.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramon.barros.repo.dto.FileDTO;
import br.com.ramon.barros.repo.dto.RepositoryDTO;
import br.com.ramon.barros.repo.services.GitService;
import br.com.ramon.barros.repo.utils.GitUtil;

@RestController
@RequestMapping(value = "/git")
@CrossOrigin
public class GitController {

	static Logger log = Logger.getLogger(GitUtil.class.getName());
	
	@Autowired
	private GitService gitService;
	
	@PutMapping
	public ResponseEntity<List<FileDTO>> findFiles(@RequestBody RepositoryDTO dto) {
		List<FileDTO> lista = gitService.findFiles(dto.getUrl());
		return ResponseEntity.ok().body(lista);
	}
	
}
