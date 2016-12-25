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
        name = "discountApi",
        version = "v1",
        resource = "discount",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class DiscountEndpoint {

    private static final Logger logger = Logger.getLogger(DiscountEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 1000;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Discount.class);
    }

    /**
     * Returns the {@link Discount} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Discount} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "discount/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Discount get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Discount with ID: " + id);
        Discount discount = ofy().load().type(Discount.class).id(id).now();
        if (discount == null) {
            throw new NotFoundException("Could not find Discount with ID: " + id);
        }
        return discount;
    }

    /**
     * Inserts a new {@code Discount}.
     */
    @ApiMethod(
            name = "insert",
            path = "discount",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Discount insert(Discount discount) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that discount.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(discount).now();
        logger.info("Created Discount with ID: " + discount.getId());

        return ofy().load().entity(discount).now();
    }

    /**
     * Updates an existing {@code Discount}.
     *
     * @param id       the ID of the entity to be updated
     * @param discount the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Discount}
     */
    @ApiMethod(
            name = "update",
            path = "discount/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Discount update(@Named("id") Long id, Discount discount) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(discount).now();
        logger.info("Updated Discount: " + discount);
        return ofy().load().entity(discount).now();
    }

    /**
     * Deletes the specified {@code Discount}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Discount}
     */
    @ApiMethod(
            name = "remove",
            path = "discount/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Discount.class).id(id).now();
        logger.info("Deleted Discount with ID: " + id);
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
            path = "discount",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Discount> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Discount> query = ofy().load().type(Discount.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Discount> queryIterator = query.iterator();
        List<Discount> discountList = new ArrayList<>(limit);
        while (queryIterator.hasNext()) {
            discountList.add(queryIterator.next());
        }
        return CollectionResponse.<Discount>builder().setItems(discountList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Discount.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Discount with ID: " + id);
        }
    }
}