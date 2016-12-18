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
        name = "outletBeanApi",
        version = "v1",
        resource = "outletBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class OutletBeanEndpoint {

    private static final Logger logger = Logger.getLogger(OutletBeanEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(OutletBean.class);
    }

    /**
     * Returns the {@link OutletBean} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code OutletBean} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "outletBean/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public OutletBean get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting OutletBean with ID: " + id);
        OutletBean outletBean = ofy().load().type(OutletBean.class).id(id).now();
        if (outletBean == null) {
            throw new NotFoundException("Could not find OutletBean with ID: " + id);
        }
        return outletBean;
    }

    /**
     * Inserts a new {@code OutletBean}.
     */
    @ApiMethod(
            name = "insert",
            path = "outletBean",
            httpMethod = ApiMethod.HttpMethod.POST)
    public OutletBean insert(OutletBean outletBean) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that outletBean.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(outletBean).now();
        logger.info("Created OutletBean with ID: " + outletBean.getId());

        return ofy().load().entity(outletBean).now();
    }

    /**
     * Updates an existing {@code OutletBean}.
     *
     * @param id         the ID of the entity to be updated
     * @param outletBean the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code OutletBean}
     */
    @ApiMethod(
            name = "update",
            path = "outletBean/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public OutletBean update(@Named("id") Long id, OutletBean outletBean) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(outletBean).now();
        logger.info("Updated OutletBean: " + outletBean);
        return ofy().load().entity(outletBean).now();
    }

    /**
     * Deletes the specified {@code OutletBean}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code OutletBean}
     */
    @ApiMethod(
            name = "remove",
            path = "outletBean/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(OutletBean.class).id(id).now();
        logger.info("Deleted OutletBean with ID: " + id);
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
            path = "outletBean",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<OutletBean> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<OutletBean> query = ofy().load().type(OutletBean.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<OutletBean> queryIterator = query.iterator();
        List<OutletBean> outletBeanList = new ArrayList<OutletBean>(limit);
        while (queryIterator.hasNext()) {
            outletBeanList.add(queryIterator.next());
        }
        return CollectionResponse.<OutletBean>builder().setItems(outletBeanList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(OutletBean.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find OutletBean with ID: " + id);
        }
    }
}