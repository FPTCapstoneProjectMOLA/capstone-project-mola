package JPARepository;

import models.AuthorEntity;
import play.db.jpa.JPAApi;
import repository.AuthorRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by NGOCHIEU on 2017-05-23.
 */
public class AuthorJPARepository implements AuthorRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public AuthorJPARepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }




    @Override
    public CompletionStage<AuthorEntity> add(AuthorEntity author) {

        return supplyAsync(() -> wrap(em -> insert(em, author)), executionContext);

    }

    @Override
    public CompletionStage<Stream<AuthorEntity>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    @Override
    public CompletionStage<AuthorEntity> update(AuthorEntity author) {
        return supplyAsync(() -> wrap(em -> update(em, author)), executionContext);
    }

    @Override
    public CompletionStage<Boolean> delete(AuthorEntity author) {
        return supplyAsync(() -> wrap(em -> delete(em, author)), executionContext);
    }

    @Override
    public List<AuthorEntity> getList() {
        EntityManager em = this.jpaApi.em();
        return em.createQuery("SELECT a FROM AuthorEntity a").getResultList();
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private AuthorEntity insert(EntityManager em, AuthorEntity author){
        em.persist(author);
        return author;
    }

    private Stream<AuthorEntity> list(EntityManager em){
        List<AuthorEntity> authors = em.createQuery("SELECT a FROM AuthorEntity a").getResultList();
        return authors.stream();
    }

    private AuthorEntity update(EntityManager em, AuthorEntity author){
        return em.merge(author);
    }
    private boolean delete(EntityManager em, AuthorEntity author){
        em.remove(author);
        return true;
    }


}
