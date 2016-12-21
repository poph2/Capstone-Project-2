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
        name = "companyBeanApi",
        version = "v1",
        resource = "companyBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class CompanyBeanEndpoint {

    private static final Logger logger = Logger.getLogger(CompanyBeanEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(CompanyBean.class);
    }

    /**
     * Returns the {@link CompanyBean} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code CompanyBean} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "companyBean/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CompanyBean get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting CompanyBean with ID: " + id);
        CompanyBean companyBean = ofy().load().type(CompanyBean.class).id(id).now();
        if (companyBean == null) {
            throw new NotFoundException("Could not find CompanyBean with ID: " + id);
        }
        return companyBean;
    }

    /**
     * Inserts a new {@code CompanyBean}.
     */
    @ApiMethod(
            name = "insert",
            path = "companyBean",
            httpMethod = ApiMethod.HttpMethod.POST)
    public CompanyBean insert(CompanyBean companyBean) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that companyBean.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(companyBean).now();
        logger.info("Created CompanyBean with ID: " + companyBean.getId());

        return ofy().load().entity(companyBean).now();
    }

    /**
     * Updates an existing {@code CompanyBean}.
     *
     * @param id          the ID of the entity to be updated
     * @param companyBean the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code CompanyBean}
     */
    @ApiMethod(
            name = "update",
            path = "companyBean/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public CompanyBean update(@Named("id") Long id, CompanyBean companyBean) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(companyBean).now();
        logger.info("Updated CompanyBean: " + companyBean);
        return ofy().load().entity(companyBean).now();
    }

    /**
     * Deletes the specified {@code CompanyBean}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code CompanyBean}
     */
    @ApiMethod(
            name = "remove",
            path = "companyBean/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(CompanyBean.class).id(id).now();
        logger.info("Deleted CompanyBean with ID: " + id);
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
            path = "companyBean",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<CompanyBean> list(@Nullable @Named("cursor") String cursor, @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<CompanyBean> query = ofy().load().type(CompanyBean.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<CompanyBean> queryIterator = query.iterator();
        List<CompanyBean> companyBeanList = new ArrayList<CompanyBean>(limit);
        while (queryIterator.hasNext()) {
            companyBeanList.add(queryIterator.next());
        }
        return CollectionResponse.<CompanyBean>builder().setItems(companyBeanList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(CompanyBean.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find CompanyBean with ID: " + id);
        }
    }
}