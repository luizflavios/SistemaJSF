package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import dao.DaoGeneric;
import model.Cidade;
import model.Estado;
import model.Lancamento;
import repository.IDaoLancamento;
import util.JPAUtil;

@javax.faces.view.ViewScoped
@Named(value = "beanLancamento")
public class BeanLancamento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento(); 
	private List<Lancamento> listaLancamentos = new ArrayList<Lancamento>();
	
	@Inject
	private DaoGeneric<Lancamento> daoGeneric;	
	
	@Inject
	private IDaoLancamento daoLancamento;
	
	private List<SelectItem> estados;
	private List<SelectItem> cidades;
	private List<SelectItem> usuarios;
	
	@Inject
	private JPAUtil jpaUtil;
	
	
	
	public String salvar() {				  
		 lancamento = daoGeneric.salvarMerge(lancamento);
		 lancamento = new Lancamento();
		 message("Lançamento cadastrado com sucesso!");
		 return "";
	}
	
	public String delete() {
		daoGeneric.delete(lancamento);
		carregarLancamentos();
		return "";
	}
	
	
	@PostConstruct
	private void carregarLancamentos() {				 	
		listaLancamentos = 	daoLancamento.consultarLimite5(); 			
	}
	
	@SuppressWarnings("unchecked")
	public void carregaCidades(AjaxBehaviorEvent event) {
		
		Estado estado = (Estado) ((HtmlSelectOneMenu) event.getSource()).getValue(); 
		
						
			if(estado != null) {
				
				lancamento.setEstados(estado);
				
				List<Cidade> cidades = jpaUtil.getEntityManager().createQuery("FROM Cidade WHERE estado.id = " + estado.getId()).getResultList();
				
				List<SelectItem> selectCidades = new ArrayList<SelectItem>();
				
				for (Cidade cidade : cidades) {
					selectCidades.add(new SelectItem(cidade, cidade.getNome()));
				}
				
				setCidades(selectCidades);
				
			}
			
		}
	
	/*Arrumar edição*/
	@SuppressWarnings("unchecked")
	public String editar() {
		if(lancamento.getCidades() != null) {
			Estado estado = lancamento.getCidades().getEstado();
			lancamento.setEstados(estado);
			
			List<Cidade> cidades = jpaUtil.getEntityManager().createQuery("FROM Cidade WHERE estado.id = " + estado.getId()).getResultList();
			
			List<SelectItem> selectCidades = new ArrayList<SelectItem>();
			
			for (Cidade cidade : cidades) {
				selectCidades.add(new SelectItem(cidade, cidade.getNome()));
			}
			
			setCidades(selectCidades);
		}
		
		Object pk = jpaUtil.getPrimaryKey(lancamento);  
		lancamento = daoGeneric.consultarEntidade(Lancamento.class, pk.toString());
		return "lancamento.jsf"; 
	}
	
		
	private void message(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("MENSAGEM:", msg));
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public DaoGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}
	public void setDaoGeneric(DaoGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	public List<Lancamento> getListaLancamentos() {
		return listaLancamentos;
	}
	public void setListaLancamentos(List<Lancamento> listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}
	public IDaoLancamento getDaoLancamento() {
		return daoLancamento;
	}
	public void setDaoLancamento(IDaoLancamento daoLancamento) {
		this.daoLancamento = daoLancamento;
	}
	public List<SelectItem> getEstados() {
		estados = daoLancamento.listaEstados();
		return estados;
	}
	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}
	
	public List<SelectItem> getUsuarios() {
		usuarios = daoLancamento.listaUsuarios();
		return usuarios;
	}
	
	public void setUsuarios(List<SelectItem> usuarios) {
		this.usuarios = usuarios;
	}
	
	public List<SelectItem> getCidades() {
		return cidades;
	}
	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}
	
	
}
