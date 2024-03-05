import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {

	private static final long serialVersionUID = 1L;
	
	public DicionarioServant() throws java.rmi.RemoteException {
		super();
	}

	public String consultar(String palavra) throws java.rmi.RemoteException {
		// JSONArray palavras = new JSONArray("dicionario.json");
		List<Palavra> palavras = null;
		try {
			File file = new File("dicionario.ser");
			if (file.length() == 0) {
				palavras = new ArrayList<>();
			} else {
				FileInputStream fis = new FileInputStream("dicionario.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				palavras = (List<Palavra>) ois.readObject();
				ois.close();
			}			
			for (int i = 0; i < palavras.size(); i++) {
				if (palavras.get(i).palavra.equals(palavra)) {
					return palavras.get(i).significado;
				}
			}
			return "Palavra não encontrada!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	public String adicionar(String palavra, String significado) throws java.rmi.RemoteException {
		// JSONArray palavras = new JSONArray("dicionario.json");
		List<Palavra> palavras = null;
		try {
			File file = new File("dicionario.ser");
			boolean exists = false;
			if (file.length() == 0) {
				palavras = new ArrayList<>();
			} else {
				FileInputStream fis = new FileInputStream("dicionario.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				palavras = (List<Palavra>) ois.readObject();
				ois.close();
				for (int i = 0; i < palavras.size(); i++) {
					if (palavras.get(i).palavra.equals(palavra)) {
						exists = true;
					}
				}
			}			
			if (exists ==  true) {
				return "Palavra já existe no dicionário! (duplicado)";
			} else {
				Palavra novaPalavra = new Palavra(palavra, significado);
				palavras.add(novaPalavra);
				FileOutputStream fout = new FileOutputStream("dicionario.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(palavras);
				oos.close();
			}
			
			// this.writeFile(palavras.toString());
			return "Sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	public String remover(String palavra) throws java.rmi.RemoteException {
		// JSONArray palavras = new JSONArray("dicionario.json");
		List<Palavra> palavras = null;
		try {
			File file = new File("dicionario.ser");
			if (file.length() == 0) {
				palavras = new ArrayList<>();
			} else {
				FileInputStream fis = new FileInputStream("dicionario.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				palavras = (List<Palavra>) ois.readObject();
				ois.close();
			}			
			for (int i = 0; i < palavras.size(); i++) {
				if (palavras.get(i).palavra.equals(palavra)) {
					palavras.remove(i);
					// this.writeFile(palavras.toString());
					FileOutputStream fout = new FileOutputStream("dicionario.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(palavras);
					oos.close();
					return "Sucesso!";
				}
			}
			return "Não encontrado!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
