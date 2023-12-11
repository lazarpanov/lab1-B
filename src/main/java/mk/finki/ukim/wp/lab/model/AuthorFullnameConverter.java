package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname, String> {
    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(AuthorFullname authorFullname) {
        if (authorFullname == null) return null;

        StringBuilder sb = new StringBuilder();

        if (authorFullname.getSurname() != null && !authorFullname.getSurname().isEmpty()) {
            sb.append(authorFullname.getSurname());
            sb.append(SEPARATOR);
        }

        if (authorFullname.getName() != null && !authorFullname.getName().isEmpty()) {
            sb.append(authorFullname.getName());
            sb.append(SEPARATOR);
        }

        return sb.toString();
    }

    @Override
    public AuthorFullname convertToEntityAttribute(String dbAuthorFullname) {
        if (dbAuthorFullname == null || dbAuthorFullname.isEmpty()) {
            return null;
        }

        String[] pieces = dbAuthorFullname.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        AuthorFullname authorFullname = new AuthorFullname();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbAuthorFullname.contains(SEPARATOR)) {
            authorFullname.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                authorFullname.setName(pieces[1]);
            }
        } else {
            authorFullname.setName(firstPiece);
        }

        return authorFullname;
    }
}
