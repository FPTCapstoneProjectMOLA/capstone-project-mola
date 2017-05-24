package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by NGOCHIEU on 2017-05-23.
 */
@Entity
@Table(name = "Author", schema = "mola", catalog = "")
public class AuthorEntity {
    private int id;
    private String authorName;
    private Collection<BookEntity> booksById;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AuthorName")
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEntity that = (AuthorEntity) o;

        if (id != that.id) return false;
        if (authorName != null ? !authorName.equals(that.authorName) : that.authorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "authorByAuthor")
    public Collection<BookEntity> getBooksById() {
        return booksById;
    }

    public void setBooksById(Collection<BookEntity> booksById) {
        this.booksById = booksById;
    }
}
