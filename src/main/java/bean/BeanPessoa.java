package bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DaoGeneric;
import model.Pessoa;
import repository.IDaoPessoa;

@javax.faces.view.ViewScoped
@Named(value = "beanPessoa")
public class BeanPessoa implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	private String parametro; 
	
	@Inject
	private DaoGeneric<Pessoa> daoGeneric; 
	
	@Inject
	private IDaoPessoa daoPessoa; 
	
	
	public String consultaPersonalizada() {
		listaPessoas = daoPessoa.consultaParametrizada(parametro);
		
		return "";
	}
	
	public String logar() {
		
		Pessoa usuario = daoPessoa.consultarExistenciaUsuario(pessoa.getUsuario(), pessoa.getSenha()); 
		
		if(usuario != null) {
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext(); 
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest(); 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("usuarioLogado", usuario);
			
			return "pages/home.jsf";
			
		}
		
		return "index.jsf"; 
	}
	
	
	
	
	public String deslogar() {		
		
		 FacesContext context = FacesContext.getCurrentInstance();
		 ExternalContext external = context.getExternalContext();
		 
		 HttpServletRequest req = (HttpServletRequest) external.getRequest();
		 HttpSession session = req.getSession();
		 
		 session.removeAttribute("usuarioLogado");
		 session.invalidate();
		 
		 return "/index.jsf";
		
	}
	
	public boolean permiteAcesso(String acesso) {
		
		 FacesContext context = FacesContext.getCurrentInstance();
		 ExternalContext external = context.getExternalContext();
		 
		 HttpServletRequest req = (HttpServletRequest) external.getRequest();
		 HttpSession session = req.getSession();
		 
		 Pessoa pessoa1 = (Pessoa) session.getAttribute("usuarioLogado");
		 
		 return pessoa1.getPerfil().equals(acesso);
		
	}
	
	public String salvar() { 
		pessoa = daoGeneric.salvarMerge(pessoa); 
		message("Usuário cadastrado com sucesso!");
		pessoa = new Pessoa();
		return ""; 
		
	}
	
	@PostConstruct
	public void carregarPessoas(){
		listaPessoas = daoGeneric.getListEntity(pessoa);		
	}
	
	
	
	public String novo() {
		pessoa = new Pessoa(); 
		return "";			
	}
	
	public String limpar() {
		pessoa = new Pessoa(); 
		return "";			
	}
	
	public String delete() {
		daoGeneric.delete(pessoa);
		return ""; 
	}
	
	public String deletePorId() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();
		
		return ""; 
	}
	
	private void message(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("MENSAGEM:", msg));
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) {
		
		try {
			
			URL url = new URL("https://viacep.com.br/ws/" + pessoa.getCep().replaceAll("\\p{Punct}", "") + "/json/"); 
			URLConnection urlConnection = url.openConnection(); 
			InputStream input = urlConnection.getInputStream(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8")); 
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			
			while ((cep = reader.readLine()) != null ) {
				jsonCep.append(cep);
			}
			
			Pessoa gson = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
			
			pessoa.setLogradouro(gson.getLogradouro());
			pessoa.setBairro(gson.getBairro());
			pessoa.setLocalidade(gson.getLocalidade());
			pessoa.setUf(gson.getUf());	
	
			
			
		} catch (Exception e) {
			e.printStackTrace();
			message("Erro no CEP");
		}
		
	}	
	
	/* Acesso e Modificação */
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}
	
	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	
	public String getParametro() {
		return parametro;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
