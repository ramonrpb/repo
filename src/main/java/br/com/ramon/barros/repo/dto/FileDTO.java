package br.com.ramon.barros.repo.dto;

import java.io.Serializable;

public class FileDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer quantityLines;
	private Long size;
	private String extension;
	
	public FileDTO() {}
	
	public FileDTO(String name, Integer quantityLines, Long size, String extension) {
		super();
		this.name = name;
		this.quantityLines = quantityLines;
		this.size = size;
		this.extension = extension;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getQuantityLines() {
		return quantityLines;
	}
	public void setQuantityLines(Integer quantityLines) {
		this.quantityLines = quantityLines;
	}
	
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((quantityLines == null) ? 0 : quantityLines.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDTO other = (FileDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantityLines == null) {
			if (other.quantityLines != null)
				return false;
		} else if (!quantityLines.equals(other.quantityLines))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

}
