package com.pop.pricecutz.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "fBAccountApi",
        version = "v1",
        resource = "fBAccount",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class FBAccountEndpoint {

    private static final Logger logger = Logger.getLogger(FBAccountEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(FBAccount.class);
    }

    /**
     * Returns the {@link FBAccount} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code FBAccount} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "fBAccount/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public FBAccount get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting FBAccount with ID: " + id);
        FBAccount fBAccount = ofy().load().type(FBAccount.class).id(id).now();
        if (fBAccount == null) {
            throw new NotFoundException("Could not find FBAccount with ID: " + id);
        }
        return fBAccount;
    }

    /**
     * Inserts a new {@code FBAccount}.
     */
    @ApiMethod(
            name = "insert",
            path = "fBAccount",
            httpMethod = ApiMethod.HttpMethod.POST)
    public FBAccount insert(FBAccount fBAccount) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that fBAccount.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(fBAccount).now();
        logger.info("Created FBAccount with ID: " + fBAccount.getId());

        return ofy().load().entity(fBAccount).now();
    }

    /**
     * Updates an existing {@code FBAccount}.
     *
     * @param id        the ID of the entity to be updated
     * @param fBAccount the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code FBAccount}
     */
    @ApiMethod(
            name = "update",
            path = "fBAccount/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public FBAccount update(@Named("id") Long id, FBAccount fBAccount) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(fBAccount).now();
        logger.info("Updated FBAccount: " + fBAccount);
        return ofy().load().entity(fBAccount).now();
    }

    /**
     * Deletes the specified {@code FBAccount}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code FBAccount}
     */
    @ApiMethod(
            name = "remove",
            path = "fBAccount/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(FBAccount.class).id(id).now();
        logger.info("Deleted FBAccount with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "fBAccount",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<FBAccount> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<FBAccount> query = ofy().load().type(FBAccount.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<FBAccount> queryIterator = query.iterator();
        List<FBAccount> fBAccountList = new ArrayList<FBAccount>(limit);
        while (queryIterator.hasNext()) {
            fBAccountList.add(queryIterator.next());
        }
        return CollectionResponse.<FBAccount>builder().setItems(fBAccountList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(FBAccount.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find FBAccount with ID: " + id);
        }
    }
}