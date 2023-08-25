package com.distribuida.client;

import com.distribuida.dto.SingerDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/singers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface SingerRestClient {

    @GET
    List<SingerDto> findAll();

    @GET
    @Path("/{id}")
    SingerDto getById(@PathParam("id") Integer id);

    @POST
    SingerDto create(SingerDto singer);

    @PUT
    @Path("/{id}")
    SingerDto update(@PathParam("id") Integer id, SingerDto singer);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Integer id);

}
