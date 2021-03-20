package br.com.ramon.barros.repo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Logger;

public class GitUtil {
	
	static Logger log = Logger.getLogger(GitUtil.class.getName());

	public static void gitClone(Path directory, String originUrl) throws IOException, InterruptedException {
		log.info("###### gitClone - directory: "+ directory);
		log.info("###### gitClone - originUrl: "+ originUrl);
		
		log.warning("###### gitClone - directory: "+ directory);
		log.warning("###### gitClone - originUrl: "+ originUrl);
		
		runCommand(directory.getParent(), "git", "clone", originUrl, directory.getFileName().toString());
	}
	
	public static void runCommand(Path directory, String... command) throws IOException, InterruptedException {
//		Objects.requireNonNull(directory, "directory");
		if (!Files.exists(directory)) {
			Files.createDirectory(directory);
		}
		ProcessBuilder pb = new ProcessBuilder()
				.command(command)
				.directory(directory.toFile());
		Process p = pb.start();
		Reader commandReader = new Reader(p.getErrorStream(), command[1]);
		Reader outputReader = new Reader(p.getInputStream(), "OUTPUT");
		outputReader.start();
		commandReader.start();
		int exit = p.waitFor();
		commandReader.join();
		outputReader.join();
		if (exit != 0) {
			throw new AssertionError(String.format("runCommand returned %d", exit));
		}
	}
	
	private static class Reader extends Thread {

		private final InputStream is;
		private final String type;

		private Reader(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		@Override
		public void run() {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(type + "-" + line);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
