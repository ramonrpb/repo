package br.com.ramon.barros.repo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
		List<File> list = null;
		try {
			Path dir = Files.createTempDirectory("tmp");
			log.info(">>>>>>>>>>>>>>>>>>>>>> Temp Dir: "+ dir);
			log.info(">>>>>>>>>>>>>>>>>>>>>> Temp Dir: "+ dir.getParent().toString());
			File f = new File(dir.getParent().toString());
			list = Arrays.asList(f.listFiles());
	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        return ResponseEntity.ok().body(list);
	}
}
