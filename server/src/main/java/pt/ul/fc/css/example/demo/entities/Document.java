package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class Document {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private String filename;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DocumentType type;

  // Constructors, getters and setters
  public Document() {}

  public Document(String filename, DocumentType type) {
    this.filename = filename;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public DocumentType getType() {
    return type;
  }

  public void setType(DocumentType type) {
    this.type = type;
  }
}
