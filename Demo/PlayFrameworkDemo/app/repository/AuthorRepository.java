package repository;

import JPARepository.AuthorJPARepository;
import com.google.inject.ImplementedBy;
import models.AuthorEntity;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Created by NGOCHIEU on 2017-05-23.
 */
@ImplementedBy(AuthorJPARepository.class)
public interface AuthorRepository {
    CompletionStage<AuthorEntity> add(AuthorEntity author);
    CompletionStage<Stream<AuthorEntity>> list();
    CompletionStage<AuthorEntity> update(AuthorEntity author);
    CompletionStage<Boolean> delete(AuthorEntity author);
    List<AuthorEntity> getList();
}
