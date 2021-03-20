package br.com.ramon.barros.repo.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import br.com.ramon.barros.repo.dto.FileDTO;
import br.com.ramon.barros.repo.utils.GitUtil;

@Service
public class GitService {

	public HashMap<String, List<FileDTO>> cloneRepository(String url) {
		String[] values = url.split("/");
		String name = values[values.length - 1].replace(".git", "");
		String directoryPath = ".\\tmp\\temp\\" + name;
		Path directory = Paths.get(directoryPath);
		try {
			File file = new File(directoryPath);
			if (!file.exists()) {
				GitUtil.gitClone(directory, url);
			}
			HashMap<String, List<FileDTO>> mapFiles = new HashMap<>();
			return searchListFiles(file, mapFiles);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
