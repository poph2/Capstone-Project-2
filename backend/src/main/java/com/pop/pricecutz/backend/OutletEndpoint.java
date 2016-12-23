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
        name = "outletApi",
        version = "v1",
        resource = "outlet",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class OutletEndpoint {

    private static final Logger logger = Logger.getLogger(OutletEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Outlet.class);
    }

    /**
     * Returns the {@link Outlet} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Outlet} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "outlet/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Outlet get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Outlet with ID: " + id);
        Outlet outlet = ofy().load().type(Outlet.class).id(id).now();
        if (outlet == null) {
            throw new NotFoundException("Could not find Outlet with ID: " + id);
        }
        return outlet;
    }

    /**
     * Inserts a new {@code Outlet}.
     */
    @ApiMethod(
            name = "insert",
            path = "outlet",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Outlet insert(Outlet outlet) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that outlet.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(outlet).now();
        logger.info("Created Outlet with ID: " + outlet.getId());

        return ofy().load().entity(outlet).now();
    }

    /**
     * Updates an existing {@code Outlet}.
     *
     * @param id     the ID of the entity to be updated
     * @param outlet the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Outlet}
     */
    @ApiMethod(
            name = "update",
            path = "outlet/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Outlet update(@Named("id") Long id, Outlet outlet) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(outlet).now();
        logger.info("Updated Outlet: " + outlet);
        return ofy().load().entity(outlet).now();
    }

    /**
     * Deletes the specified {@code Outlet}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Outlet}
     */
    @ApiMethod(
            name = "remove",
            path = "outlet/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Outlet.class).id(id).now();
        logger.info("Deleted Outlet with ID: " + id);
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
            path = "outlet",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Outlet> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Outlet> query = ofy().load().type(Outlet.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Outlet> queryIterator = query.iterator();
        List<Outlet> outletList = new ArrayList<Outlet>(limit);
        while (queryIterator.hasNext()) {
            outletList.add(queryIterator.next());
        }
        return CollectionResponse.<Outlet>builder().setItems(outletList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Outlet.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Outlet with ID: " + id);
        }
    }
}