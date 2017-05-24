package repository;

import models.AuthorEntity;
import models.BookEntity;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Created by NGOCHIEU on 2017-05-24.
 */
public interface BookRepository {
    CompletionStage<Stream<BookEntity>> getBookByAuthor(AuthorEntity author);
}
