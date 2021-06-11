package br.com.fiap.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.UsuarioDAOImpl;
import br.com.fiap.entity.Usuario;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Path("/usuarios")
public class UsuarioEndpoint {

	private EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private UsuarioDAO dao = new UsuarioDAOImpl(em);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarTodos() {
		return dao.listarTodos();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Usuario usuario) {
		if (usuario == null) { 
			return Response.status(Response.Status.BAD_REQUEST).build(); 
		}
		dao.create(usuario);
		return Response.status(Response.Status.CREATED).entity(usuario).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response deletar(@PathParam("id") int id) {
		Usuario usuario;
		try {
			usuario = dao.BuscarPorId(id);
			if(usuario == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			dao.delete(id);
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET 
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarUsuario(@PathParam("id") int id) {
		Usuario usuario;
		try {
			dao.transaction();
			usuario = dao.BuscarPorId(id);
			if(usuario == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.status(Response.Status.OK).entity(usuario).build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response editar(@PathParam("id") int id, Usuario usuario) {
		em.getTransaction().begin();
		usuario.setCodigo(id);
		dao.update(usuario);
		return Response.status(Response.Status.OK).entity(usuario).build();
	}
	
}
