package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String biography;
    @Convert(converter = AuthorFullnameConverter.class)
    private String AuthorFullname;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }

    public Author() {

    }
}
