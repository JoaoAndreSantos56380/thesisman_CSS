package pt.ul.fc.css.example.demo.services.Storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileSystemStorageService  {

	private final Path rootLocation = Path.of("upload-dir");

	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				System.out.println("Failed to store empty file.");
			}
			Path destinationFile = this.rootLocation.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				System.out.println(
						"Cannot store file outside current directory.");
			}
			Files.createFile(destinationFile);
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished saving file!");
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				System.out.println(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			System.out.println("Could not read file: " + filename);
		}
		return null;
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {

			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}