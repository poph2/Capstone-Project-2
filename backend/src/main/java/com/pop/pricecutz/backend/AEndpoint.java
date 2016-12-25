package com.pop.pricecutz.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

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
public class AEndpoint {

    private static final Logger logger = Logger.getLogger(AEndpoint.class.getName());

//    /**
//     * This method gets the <code>A</code> object associated with the specified <code>id</code>.
//     *
//     * @param id The id of the object to be returned.
//     * @return The <code>A</code> associated with <code>id</code>.
//     */
//    @ApiMethod(name = "getA")
//    public A getA(@Named("id") Long id) {
//        // TODO: Implement this function
//        logger.info("Calling getA method");
//        return null;
//    }

    /**
     * This inserts a new <code>A</code> object.
     *
     * @param a The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertA")
    public A insertA(A a) {
        // TODO: Implement this function
        logger.info("Calling insertA method");
        return a;
    }

    @ApiMethod(name = "getInit")
    public A getInit(@Named("index") Integer index) {
        A a = new A();

        InitData.initData();

        return a;
    }
}