package de.qaware.cloudcomputing;

import de.qaware.cloudcomputing.tle.TleMember;
import de.qaware.cloudcomputing.tle.TleSearchResult;
import de.qaware.cloudcomputing.tle.TleService;
import io.micrometer.core.annotation.Counted;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@JBossLog
@Path("/tle")
public class TleResource {

    @Inject
    @RestClient
    TleService tleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TleSearchResult search(@QueryParam("searchString") String searchString) {
        log.infov("Performing search for {0}", searchString);
        return tleService.search(searchString);
    }

    @GET
    @Path("/{satelliteId}")
    public TleMember getRecord(@PathParam("satelliteId") int satelliteId) {
        return tleService.getRecord(satelliteId);
    }
}
