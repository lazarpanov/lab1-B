package mk.finki.ukim.wp.lab.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class AuthorFullname implements Serializable {
    private String name;
    private String surname;
}
