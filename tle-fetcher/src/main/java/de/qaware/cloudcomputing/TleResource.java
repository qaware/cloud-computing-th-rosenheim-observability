package de.qaware.cloudcomputing;

import de.qaware.cloudcomputing.tle.TleMember;
import de.qaware.cloudcomputing.tle.TlePropagationResult;
import de.qaware.cloudcomputing.tle.TleSearchParameters;
import de.qaware.cloudcomputing.tle.TleSearchResult;
import de.qaware.cloudcomputing.tle.TleClient;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@JBossLog
@Path("/tle")
public class TleResource {

    @Inject
    @RestClient
    TleClient tleClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<TleSearchResult> search(@BeanParam @Valid TleSearchParameters searchParameters) {
        log.tracev("Processing request GET /tle with search parameters {0}", searchParameters);

        Uni<TleSearchResult> searchResult = tleClient.search(searchParameters);

//        log.debugv("Retrieved search result {0} with {1} items", searchResult.getId(), searchResult.getTotalItems());

        return searchResult;
    }

    @GET
    @Path("/{satelliteId}")
    public Uni<TleMember> getRecord(@PathParam("satelliteId") int satelliteId) {
        log.tracev("Processing request GET /tle/{satelliteId} with parameter {0}", satelliteId);

        Uni<TleMember> record = tleClient.getRecord(satelliteId);

//        log.debugv("Retrieved TLE record for satellite {0} (ID {1})", record.getName(), satelliteId);

        return record;
    }

    @GET
    @Path("/{satelliteId}/propagate")
    public Uni<TlePropagationResult> propagate(@PathParam("satelliteId") int satelliteId) {
        log.tracev("Processing request GET /tle/{satelliteId}/propagate with parameter {0}", satelliteId);

        Uni<TlePropagationResult> record = tleClient.propagate(satelliteId);

//        log.debugv("Retrieved TLE record for satellite {0} (ID {1})", record.getName(), satelliteId);

        return record;
    }
}
