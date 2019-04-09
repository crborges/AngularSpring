package br.com.cr.borges.api.contoller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cr.borges.api.dto.Resumo;
import br.com.cr.borges.api.entity.AlteracoesEstado;
import br.com.cr.borges.api.entity.Ticket;
import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.enums.EstadosEnum;
import br.com.cr.borges.api.enums.PerfilEnum;
import br.com.cr.borges.api.response.Response;
import br.com.cr.borges.api.security.jwt.JwtTokenUtil;
import br.com.cr.borges.api.service.TicketService;
import br.com.cr.borges.api.service.UsuarioService;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins="*")
public class TicketController {
	
	@Autowired
	private TicketService ticketService; 

	@Autowired /*para pesquisar pelo usaurio logado vou buscar do seu token*/
	protected JwtTokenUtil jwtTokenUtil; 
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
	public ResponseEntity<Response<Ticket>> create(HttpServletRequest request, @RequestBody Ticket ticket, BindingResult result){
		Response<Ticket> response = new Response<Ticket>();
			try {
				validarCriacaoTicket(ticket, result);
					if (result.hasErrors()){
						result.getAllErrors().forEach(Error->response.getErros().add(Error.getDefaultMessage()));
						return ResponseEntity.badRequest().body(response);
					}
					
					ticket.setEstado(EstadosEnum.getStatus("NOVO"));/*seta estado como novo*/
					ticket.setUsuario(getUsuarioByRequest(request));/*seta o cara logado como dono*/
					ticket.setData(new Date());/*seta a data da inclusão*/
					ticket.setNumero(gerarNumeroIncidente());/*gera um numero apr ao ticket*/
					Ticket persistido = (Ticket) ticketService.createOrUpdate(ticket);
					response.setDado(persistido);
			} catch (Exception e) {
				response.getErros().add("excessao "+e.getMessage());
				return ResponseEntity.badRequest().body(response);
			}
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
	public ResponseEntity<Response<Ticket>> update(HttpServletRequest request, @RequestBody Ticket ticket, BindingResult result){
		Response<Ticket> response = new Response<Ticket>();
			try {
				validarAtualizacaoTicket(ticket, result);
					if (result.hasErrors()){
						result.getAllErrors().forEach(Error->response.getErros().add(Error.getDefaultMessage()));
						return ResponseEntity.badRequest().body(response);
					}
					Ticket existente = ticketService.findByid(ticket.getId());
					ticket.setEstado(existente.getEstado());
					ticket.setUsuario(existente.getUsuario());
					ticket.setData(existente.getData());
					ticket.setNumero(existente.getNumero());
					if(existente.getUsuarioAtribuido()!=null) {
						ticket.setUsuarioAtribuido(existente.getUsuarioAtribuido());
					}
					Ticket atualizado = (Ticket) ticketService.createOrUpdate(ticket);
					response.setDado(atualizado);
			} catch (Exception e) {
				response.getErros().add("excessao "+e.getMessage());
				return ResponseEntity.badRequest().body(response);
			}
		return ResponseEntity.ok(response);
	}

	
	
	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_TECNICO')")
	public ResponseEntity<Response<Ticket>> fidByid(@PathVariable String id){
		Response<Ticket> response = new Response<Ticket>();
		Ticket ticket = ticketService.findByid(id);
		if(ticket==null) {
				response.getErros().add("erro ao pesquisar o ticket do id "+id); 
				return ResponseEntity.badRequest().body(response);
			}
			List<AlteracoesEstado> alteracoes =new ArrayList<AlteracoesEstado>();
			Iterable<AlteracoesEstado> alteracoesCorrentes = ticketService.listAlteracoesEstado(ticket.getId());
			
			for (Iterator<AlteracoesEstado> iterator = alteracoesCorrentes.iterator(); iterator.hasNext();) {
				AlteracoesEstado alteracoesEstado = (AlteracoesEstado) iterator.next();
				alteracoesEstado.setTicket(null);
				alteracoes.add(alteracoesEstado);
			}
			ticket.setAlteracoes(alteracoes);
			response.setDado(ticket);
			return ResponseEntity.ok(response);
	}

	
	

	
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
	public ResponseEntity<Response<String>> deletar(@PathVariable String id){
		Response<String> response = new Response<String>();
		Ticket ticket = ticketService.findByid(id);
		if(ticket==null) {
				response.getErros().add("nao existe um ticket do id  "+id+" para deletar"); 
				return ResponseEntity.badRequest().body(response);
		}
		ticketService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	
	

	
	@GetMapping("{page}/{count}")
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_TECNICO')")
	public ResponseEntity<Response<Page<Ticket>>> findAll(HttpServletRequest request, @PathVariable("page") int page, @PathVariable("count") int count){
			
		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		
		Page<Ticket> tickets = null;//ticketService.listTicket(page,count);
		Usuario usuario = getUsuarioByRequest(request);
		
		/*SE FOR TECNICO RETORNAL ALL SEM PARAMETROS*/
		if(usuario.getPerfil().equals(PerfilEnum.ROLE_TECNICO)) {
			tickets = ticketService.listTicket(page, count);
		}
		/*SE FOR CLIENTE RETORNA ALD DESTE CLIENTE*/
		else if(usuario.getPerfil().equals(PerfilEnum.ROLE_CLIENTE)){
			tickets = ticketService.findByUsuarioAtual(page, count, usuario.getId());
		}
		response.setDado(tickets);
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping("{page}/{count}/{numero}/{titulo}/{estado}/{prioridade}/{atribuido}")  //
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_TECNICO')")
	public ResponseEntity<Response<Page<Ticket>>> findByParams(
				HttpServletRequest 				request, 
				@PathVariable("page") 			int page, 
				@PathVariable("count") 			int count,
				@PathVariable("numero") 		Integer numero,
				@PathVariable("titulo")			String titulo,
				@PathVariable("estado") 		String estado,
				@PathVariable("prioridade")		String prioridade,
				@PathVariable("atribuido")		boolean atribuido
				
			){
		titulo=titulo.equals("nao_informado") ? "" : titulo;
		estado=estado.equals("nao_informado") ? "" : estado;
		prioridade=prioridade.equals("nao_informado") ? "" : prioridade;
		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		Page<Ticket> tickets = null;
		 
		if(numero > 0) {
			tickets = ticketService.findByNumber(page, count, numero);
		}
		else {
			Usuario usuario = getUsuarioByRequest(request);
			
			if(usuario.getPerfil().equals(PerfilEnum.ROLE_TECNICO)) {
				if(atribuido) 	{tickets = ticketService.findByParametersAndAssignedUser(page, count, titulo, estado, prioridade, usuario.getId());	}
				else 			{tickets = ticketService.findByParameters(page, count, titulo, estado, prioridade);								}
			}
			else if(usuario.getPerfil().equals(PerfilEnum.ROLE_CLIENTE)){
				tickets = ticketService.findByParametersAndCurrentUser(page, count, titulo, estado, prioridade, usuario.getId());
			}
		}
		response.setDado(tickets);
		return ResponseEntity.ok(response);
	}
	
	
	
	@PutMapping(value="{id}/{estado}")
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_TECNICO')")
	public ResponseEntity<Response<Ticket>> atualizarEstado(
			@PathVariable("id") 			String id		,
			@PathVariable("estado")			String estado	,
			HttpServletRequest 				request			,
			@RequestBody 					Ticket ticket	,
			BindingResult result							)
		{
			Response<Ticket> response = new Response<Ticket>();
			try {
				validarAtualizacaEstadoTicket(id,estado,result);
				if (result.hasErrors()){
					result.getAllErrors().forEach(Error->response.getErros().add(Error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(response);
				}
				Ticket existente = ticketService.findByid(id);
				existente.setEstado(EstadosEnum.getStatus(estado));
				if(estado.equals("DESIGNADO")) {
					existente.setUsuarioAtribuido(getUsuarioByRequest(request));
				}
				Ticket atualizado = (Ticket) ticketService.createOrUpdate(existente);
				AlteracoesEstado alteracoesEstado = new AlteracoesEstado();

				alteracoesEstado.setUsuarioAlteracao(getUsuarioByRequest(request));
				alteracoesEstado.setDataAlteracaoEstado(new Date());
				alteracoesEstado.setEstado(EstadosEnum.getStatus(estado));
				alteracoesEstado.setTicket(atualizado);
				ticketService.createAlteracaoStatus(alteracoesEstado);
				response.setDado(atualizado);
			} catch (Exception e) {
				response.getErros().add("excessao "+e.getMessage());
				return ResponseEntity.badRequest().body(response);
			}
			return ResponseEntity.ok(response);
		}
	

	
	
	
	
	
	
	
	
	

	
	
	@GetMapping(value="/resumo")
	@PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_TECNICO')")
	public ResponseEntity<Response<Resumo>> buscarResumo(){
		Response<Resumo> response = new Response<Resumo>();
		Resumo resumo = new Resumo();
		int novos=0;
		int resolvidos=0;
		int aprovados=0;
		int desaprovados=0;
		int atribuidos=0;
		int fechados=0;
		
		Iterable<Ticket> tickets = ticketService.findAll();
		if(tickets!=null) {
			for (Iterator<Ticket> iterator = tickets.iterator();iterator.hasNext();) {
				Ticket ticket = (Ticket) iterator.next();
				if(ticket.getEstado().equals(EstadosEnum.NOVO)) 		{novos++;			}
				if(ticket.getEstado().equals(EstadosEnum.RESOLVIDO)) 	{resolvidos++;		}
				if(ticket.getEstado().equals(EstadosEnum.APROVADO)) 	{aprovados++;		}
				if(ticket.getEstado().equals(EstadosEnum.REPROVADO)) 	{desaprovados++;	}
				if(ticket.getEstado().equals(EstadosEnum.DESIGNADO)) 	{atribuidos++;		}
				if(ticket.getEstado().equals(EstadosEnum.FECHADO)) 		{fechados++;		}
			}
		}
		resumo.setQuantidadeNovos(novos);
		resumo.setQuantidadeResolvidos(resolvidos);
		resumo.setQuantidadeAprovados(aprovados);
		resumo.setQuantidadeDesaprovados(desaprovados);
		resumo.setQuantidadeAtribuidos(atribuidos);
		resumo.setQuantidadeFechados(fechados);
		response.setDado(resumo);
		return ResponseEntity.ok(response);
	}
	
	
	
	/*METODOS ACESSORIOS */
	private void validarCriacaoTicket(Ticket ticket, BindingResult result) {
		if(ticket.getTitulo()==null) {	result.addError(new ObjectError("Ticket", "e obrigatorio informar um titulo"));	}
	}
	
	private void validarAtualizacaoTicket(Ticket ticket, BindingResult result) {
		if(ticket.getId()==null)	 {	result.addError(new ObjectError("Ticket", "e obrigatorio informar um id"));	}
		if(ticket.getTitulo()==null) {	result.addError(new ObjectError("Ticket", "e obrigatorio informar um titulo"));	}
	}
	
	private void validarAtualizacaEstadoTicket(String id, String estado, BindingResult result) {
		if(id==null || id.equals(""))			{	result.addError(new ObjectError("Ticket", "e obrigatorio informar um id"));	}
		if(estado==null || estado.equals(""))	{	result.addError(new ObjectError("Ticket", "e obrigatorio informar um estado"));	}
	}
	
	private Usuario getUsuarioByRequest(HttpServletRequest request) {
		String token= request.getHeader("Authorization");
		String email = jwtTokenUtil.getUsernameFromToken(token);
		return usuarioService.findByEmail(email);
	}
	
	private Integer gerarNumeroIncidente() {
		Random random = new Random();
		return random.nextInt(9999);
	}

}
