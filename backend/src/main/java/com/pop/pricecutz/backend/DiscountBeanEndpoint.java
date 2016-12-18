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
        name = "discountBeanApi",
        version = "v1",
        resource = "discountBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class DiscountBeanEndpoint {

    private static final Logger logger = Logger.getLogger(DiscountBeanEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(DiscountBean.class);
    }

    /**
     * Returns the {@link DiscountBean} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code DiscountBean} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "discountBean/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public DiscountBean get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting DiscountBean with ID: " + id);
        DiscountBean discountBean = ofy().load().type(DiscountBean.class).id(id).now();
        if (discountBean == null) {
            throw new NotFoundException("Could not find DiscountBean with ID: " + id);
        }
        return discountBean;
    }

    /**
     * Inserts a new {@code DiscountBean}.
     */
    @ApiMethod(
            name = "insert",
            path = "discountBean",
            httpMethod = ApiMethod.HttpMethod.POST)
    public DiscountBean insert(DiscountBean discountBean) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that discountBean.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(discountBean).now();
        logger.info("Created DiscountBean with ID: " + discountBean.getId());

        return ofy().load().entity(discountBean).now();
    }

    /**
     * Updates an existing {@code DiscountBean}.
     *
     * @param id           the ID of the entity to be updated
     * @param discountBean the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code DiscountBean}
     */
    @ApiMethod(
            name = "update",
            path = "discountBean/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public DiscountBean update(@Named("id") Long id, DiscountBean discountBean) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(discountBean).now();
        logger.info("Updated DiscountBean: " + discountBean);
        return ofy().load().entity(discountBean).now();
    }

    /**
     * Deletes the specified {@code DiscountBean}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code DiscountBean}
     */
    @ApiMethod(
            name = "remove",
            path = "discountBean/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(DiscountBean.class).id(id).now();
        logger.info("Deleted DiscountBean with ID: " + id);
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
            path = "discountBean",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<DiscountBean> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<DiscountBean> query = ofy().load().type(DiscountBean.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<DiscountBean> queryIterator = query.iterator();
        List<DiscountBean> discountBeanList = new ArrayList<DiscountBean>(limit);
        while (queryIterator.hasNext()) {
            discountBeanList.add(queryIterator.next());
        }
        return CollectionResponse.<DiscountBean>builder().setItems(discountBeanList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(DiscountBean.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find DiscountBean with ID: " + id);
        }
    }
}