package bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import dao.DaoGeneric;
import dao.DaoUpload;
import model.Upload;
import repository.IDaoUpload;

@javax.faces.view.ViewScoped
@Named(value = "beanUpload")
public class BeanUpload implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private Upload upload = new Upload();
	private List<Upload> listaUpload = new ArrayList<Upload>();
	private DaoGeneric<Upload> daoGeneric = new DaoGeneric<Upload>();
	private IDaoUpload daoUpload = new DaoUpload(); 
	
	private Part arquivo;
	
	//private StreamedContent file; 
	
	
	public String salvar() throws IOException {
		
		/* Processamento da imagem */
		byte[] imagemByte = getByte(arquivo.getInputStream());
		upload.setFotoCompletaBase64(imagemByte);
		
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagemByte));		

		/* Pega o tipo da imagem */
		int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

		int largura = 200;
		int altura = 200;

		BufferedImage bufferedMiniatura = new BufferedImage(largura, altura, type);
		Graphics2D g = bufferedMiniatura.createGraphics();
		g.drawImage(bufferedImage, 0, 0, largura, altura, null);
		g.dispose();
		
		/* Escrever a imagem em tamanho menor */
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		String extensao = arquivo.getContentType().split("\\/")[1];
		ImageIO.write(bufferedMiniatura, extensao, byteArrayOutputStream);

		String miniImagem = "data:" + arquivo.getContentType() + ";base64"
				+ DatatypeConverter.printBase64Binary(byteArrayOutputStream.toByteArray());
		
		upload.setFotoInconBase64(miniImagem); 
		upload.setExtensaoFoto(extensao); 

		upload = daoGeneric.salvarMerge(upload);
		consultarTodos();
		return ""; 
	}
	
	/*Verificar como vai ficar a questão dos Downloads com o PrimeFaces*/
	
	public String download() throws IOException {
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
		String fileDownloadId = params.get("fileDownloadId");
		
		Upload upload = daoGeneric.consultarEntidade(Upload.class, fileDownloadId); 
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
		
		response.addHeader("Content-Disposition", "attachment; filename=download." + upload.getExtensaoFoto());
		response.setContentType("application/octet-stream");
		response.setContentLength(upload.getFotoCompletaBase64().length);
		response.getOutputStream().write(upload.getFotoCompletaBase64()); 
		response.getOutputStream().flush();
		FacesContext.getCurrentInstance().responseComplete(); 
		
		return ""; 
	}
	
	/*
	 * public String novoDownload() throws IOException {
	 * 
	 * Map<String, String> params =
	 * FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap
	 * (); String fileDownloadId = params.get("fileDownloadId");
	 * 
	 * Upload upload = daoGeneric.consultarEntidade(Upload.class, fileDownloadId);
	 * try {
	 * 
	 * 
	 * file = DefaultStreamedContent.builder()
	 * .name("arquivo."+upload.getExtensaoFoto())
	 * .contentType("application/octet-stream") .stream(null) .build();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return "";
	 * 
	 * }
	 */
	
	@PostConstruct
	public void consultarTodos() {
		listaUpload = daoUpload.consultarTodos();
	}
	
	private byte[] getByte(InputStream is) throws IOException {

		int lent;
		int size = 1024;
		byte[] buf = null;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			lent = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];

			while ((lent = is.read(buf, 0, size)) != -1) {
				bos.write(buf, 0, lent);
			}

			buf = bos.toByteArray();

		}

		return buf;
	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public List<Upload> getListaUpload() {
		return listaUpload;
	}

	public void setListaUpload(List<Upload> listaUpload) {
		this.listaUpload = listaUpload;
	}

	public DaoGeneric<Upload> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Upload> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public IDaoUpload getDaoUpload() {
		return daoUpload;
	}

	public void setDaoUpload(IDaoUpload daoUpload) {
		this.daoUpload = daoUpload;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}





	

	
	
	
	

}
