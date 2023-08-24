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


    @GET
    public List<AlbumDto> findAll(){
        return rep.findAll()
                .stream()
                .map(obj ->{
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
                }).collect(Collectors.toList());
    }

//    @GET
//    public List<Album> findAll(){
//        return rep.findAll().list();
//    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var obj = rep.findById(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(obj).build();
    }

    @POST
    public Response create(Album p) {
        rep.create(p);
        return Response.status(Response.Status.CREATED.getStatusCode(), "album created").build();
    }

    @PUT
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
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.delete(id);
        return Response.ok( )
                .build();
    }
}
