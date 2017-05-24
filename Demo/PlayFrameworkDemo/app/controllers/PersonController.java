package controllers;

import JPARepository.AuthorJPARepository;
import models.AuthorEntity;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import repository.AuthorRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final FormFactory formFactory;
    private final AuthorRepository authorRepository;
    private final HttpExecutionContext ec;

    @Inject
    public PersonController(FormFactory formFactory, AuthorRepository authorRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.authorRepository = authorRepository;
        this.ec = ec;
    }

    @Transactional
    public Result index() {
        return ok(views.html.index.render(authorRepository.getList()));
    }

    public CompletionStage<Result> addPerson() {
        AuthorEntity person = formFactory.form(AuthorEntity.class).bindFromRequest().get();
        return authorRepository.add(person).thenApplyAsync(p -> {
            return redirect(routes.PersonController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getPersons() {
        return authorRepository.list().thenApplyAsync(personStream -> {
            return ok(toJson(personStream.collect(Collectors.toList())));
        }, ec.current());
    }


}
