package br.com.ramon.barros.repo.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramon.barros.repo.dto.FileDTO;
import br.com.ramon.barros.repo.dto.RepositoryDTO;
import br.com.ramon.barros.repo.services.GitService;

@RestController
@RequestMapping(value = "/git")
public class GitController {

	@Autowired
	private GitService gitService;
	
	@PutMapping
	public ResponseEntity<HashMap<String, List<FileDTO>>> findFiles(@RequestBody RepositoryDTO dto) {
		HashMap<String, List<FileDTO>> mapFiles = gitService.cloneRepository(dto.getUrl());
		return ResponseEntity.ok().body(mapFiles);
	}
}
