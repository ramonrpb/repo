package br.com.ramon.barros.repo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class GitUtil {
	
	static Logger log = Logger.getLogger(GitUtil.class.getName());

	public static void gitClone(Path directory, String originUrl) throws IOException, InterruptedException {
		runCommand(directory.getParent(), "git", "clone", originUrl, directory.getFileName().toString());
	}
	
	public static void runCommand(Path directory, String... command) throws IOException, InterruptedException {
		if (!Files.exists(directory)) {
			Files.createDirectory(directory);
		}
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(command);
		pb.directory(directory.toFile());
		
		Process p = pb.start();
		int exit = p.waitFor();
		if (exit != 0) {
			throw new AssertionError(String.format("Git command returned %d", exit));
		}
	}
	
}
