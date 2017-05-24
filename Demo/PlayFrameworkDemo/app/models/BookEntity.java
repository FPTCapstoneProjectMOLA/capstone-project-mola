package models;

import javax.persistence.*;

/**
 * Created by NGOCHIEU on 2017-05-24.
 */
@Entity
@Table(name = "Book", schema = "mola", catalog = "")
public class BookEntity {
    private int id;
    private String name;
    private AuthorEntity authorByAuthor;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Author", referencedColumnName = "ID")
    public AuthorEntity getAuthorByAuthor() {
        return authorByAuthor;
    }

    public void setAuthorByAuthor(AuthorEntity authorByAuthor) {
        this.authorByAuthor = authorByAuthor;
    }
}
