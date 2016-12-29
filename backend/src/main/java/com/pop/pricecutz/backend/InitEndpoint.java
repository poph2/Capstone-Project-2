package com.pop.pricecutz.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "aApi",
        version = "v1",
        resource = "a",
        namespace = @ApiNamespace(
                ownerDomain = "backend.pricecutz.pop.com",
                ownerName = "backend.pricecutz.pop.com",
                packagePath = ""
        )
)
public class InitEndpoint {

    private static final Logger logger = Logger.getLogger(InitEndpoint.class.getName());


    /**
     * Returns the {@link Init} with the corresponding ID.
     *
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Init} with the provided ID.
     */
    @ApiMethod(
            name = "start",
            path = "init",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Init start() {
        Init a = new Init();

        a.initData();

        return a;
    }
}