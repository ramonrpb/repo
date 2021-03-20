package br.com.ramon.barros.repo.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class GitController {

	static Logger log = Logger.getLogger(GitUtil.class.getName());
	
	@Autowired
	private GitService gitService;
	
	@PutMapping
	public ResponseEntity<HashMap<String, List<FileDTO>>> findFiles(@RequestBody RepositoryDTO dto) {
		HashMap<String, List<FileDTO>> mapFiles = gitService.cloneRepository(dto.getUrl());
		return ResponseEntity.ok().body(mapFiles);
	}
	
	@GetMapping
	public ResponseEntity<List<File>> findDirectory(){
		String directory = "\\app\\tmp";
		File f = new File(directory);
		List<File> list	=Arrays.asList(f.listFiles());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Directory: " + directory);
        return ResponseEntity.ok().body(list);
	}
}
