package com.distribuida.rest;

import com.distribuida.client.SingerRestClient;
import com.distribuida.db.Album;
import com.distribuida.dto.AlbumDto;
import com.distribuida.dto.SingerDto;
import com.distribuida.repo.AlbumRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/albums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class AlbumRest {

    @Inject
    AlbumRepo rep;

    @Inject
    @RestClient
    SingerRestClient clientSingers;

    private AlbumDto albumToDto(Album obj){
        AlbumDto dto = new AlbumDto();
        dto.setId(obj.getId());
        dto.setReleaseDate(obj.getReleaseDate());
        dto.setTitle(obj.getTitle());
        dto.setVersion(obj.getVersion());
        dto.setSingerId(obj.getSingerId());
        SingerDto tmp = clientSingers.getById(dto.getSingerId());
        String aname = String.format("%s, %s", tmp.getFirstName(), tmp.getLastName());
        dto.setSingerFullName(aname);
        return dto;
    }


    @GET
    @Timeout(4000)
    @Retry(maxRetries = 4)
    public List<AlbumDto> findAll(){
        return rep.findAll()
                .stream()
                .map(this::albumToDto).collect(Collectors.toList());
    }

    @GET
    @Timeout(4000)
    @Retry(maxRetries = 4)
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var obj = rep.findById(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(albumToDto(obj)).build();
    }

    @POST
    @Timeout(4000)
    @Retry(maxRetries = 4)
    public Response create(Album p) {
        rep.create(p);
        return Response.status(Response.Status.CREATED.getStatusCode(), "album created").build();
    }

    @PUT
    @Timeout(4000)
    @Retry(maxRetries = 4)
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Album tmpObj) {
        Album obj = rep.findById(id);
        obj.setReleaseDate(tmpObj.getReleaseDate());
        obj.setVersion(tmpObj.getVersion());
        obj.setTitle(tmpObj.getTitle());
        obj.setSingerId(tmpObj.getSingerId());
        return Response.ok().build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(maxRetries = 4)
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.delete(id);
        return Response.ok( )
                .build();
    }
}
