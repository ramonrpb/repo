package br.com.ramon.barros.repo.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import br.com.ramon.barros.repo.dto.FileDTO;
import br.com.ramon.barros.repo.exceptions.RepositoryException;
import br.com.ramon.barros.repo.utils.GitUtil;

@Service
public class GitService {

	public List<FileDTO> findFiles(String url) {
		String[] values = url.split("/");
		String name = values[values.length - 1].replace(".git", "");
		
		try {
			File file = cloneRepository(url, name);
			HashMap<String, List<FileDTO>> mapFiles = new HashMap<>();
			searchListFiles(file, mapFiles);
			List<FileDTO> listFiles = new ArrayList<>();
			for(String key : mapFiles.keySet()) {
				listFiles.addAll(mapFiles.get(key));
			}
			File tempFile = new File(file.getAbsolutePath().replace(name, ""));
			deleteDirectoryStream(tempFile.toPath());
			
			return listFiles;
		} catch (IOException | InterruptedException | AssertionError e) {
			throw new RepositoryException("Repository not found! Please verify is url is correct!", e.getCause());
		}
	}

	private void deleteDirectoryStream(Path path) throws IOException {
		  Files.walk(path)
		    .sorted(Comparator.reverseOrder())
		    .map(Path::toFile)
		    .forEach(File::delete);
	}
	
	private File cloneRepository(String url, String name) throws IOException, InterruptedException {
		Path dir = Files.createTempDirectory("tmp");
		String directoryPath = dir.toString()+"\\" + name;
		Path directory = Paths.get(directoryPath);
		
		System.out.println(directoryPath);
		
		File file = new File(directoryPath);
		if (!file.exists()) {
			GitUtil.gitClone(directory, url);
		}
		return file;
	}

	private HashMap<String, List<FileDTO>> searchListFiles(File file, HashMap<String, List<FileDTO>> mapFiles) {
		List<FileDTO> listFileDTO;
		for (File f : Arrays.asList(file.listFiles())) {
			Integer quantityLines = 0;
			if(f.isDirectory()) {
				searchListFiles(f, mapFiles);
			}else {
				quantityLines = readQuantityLinesFile(f);
				String extensao = FilenameUtils.getExtension(f.getName());
				listFileDTO = mapFiles.get(extensao);
				groupFiles(listFileDTO, mapFiles, f, quantityLines, extensao);
			}
		}
		return mapFiles;
	}

	private void groupFiles(List<FileDTO> listFileDTO, HashMap<String, List<FileDTO>> mapFiles, File f,
			Integer quantityLines, String extensao) {
		if(listFileDTO == null) {
			listFileDTO = new ArrayList<>();
			listFileDTO.add(new FileDTO(f.getName(), quantityLines, f.length(), extensao));
			mapFiles.put(extensao, listFileDTO);
		}else {
			listFileDTO.add(new FileDTO(f.getName(), quantityLines, f.length(), extensao));
			mapFiles.put(extensao, listFileDTO);
		}
	}

	private Integer readQuantityLinesFile(File file) {
		try {
			InputStream inputStream = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			Integer count = 0;
			while ((reader.readLine()) != null) {
				count++;
			}

			inputStream.close();
			return count;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
