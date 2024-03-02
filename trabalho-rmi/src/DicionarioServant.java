import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {

	private static final long serialVersionUID = 1L;
	
	public DicionarioServant() throws java.rmi.RemoteException {
		super();
	}

	public String consultar(String palavra) throws java.rmi.RemoteException {
		JSONArray palavras = new JSONArray("dicionario.json");
		for (int i = 0; i < palavras.length(); i++) {
			if (palavras.getJSONObject(i).getString("palavra") == palavra) {
				return palavras.getJSONObject(i).getString("significado");
			}
		}
		return "Palavra não encontrada!";
	}

	public String adicionar(String palavra, String significado) throws java.rmi.RemoteException {
		JSONArray palavras = new JSONArray("dicionario.json");
		boolean exists = false;
		for (int i = 0; i < palavras.length(); i++) {
			if (palavras.getJSONObject(i).getString("palavra") == palavra) {
				exists = true;
			}
		}
		if (exists ==  true) {
			return "Palavra já existe no dicionário! (duplicado)";
		} else {
			JSONObject obj = new JSONObject();
			obj.put("palavra", palavra);
			obj.put("significado", significado);
			palavras.put(obj);
		}
		try {
			this.writeFile(palavras.toString());
			return "Sucesso!";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	public String remover(String palavra) throws java.rmi.RemoteException {
		JSONArray palavras = new JSONArray("dicionario.json");
		for (int i = 0; i < palavras.length(); i++) {
			if (palavras.getJSONObject(i).getString("palavra") == palavra) {
				palavras.remove(i);
				try {
					this.writeFile(palavras.toString());
					return "Sucesso!";
				} catch (IOException e) {
					e.printStackTrace();
					return "Erro";
				}
			}
		}
		return "Não encontrado!";	
	}

	private void writeFile(String text) throws IOException {
		FileWriter file = new FileWriter("dicionario.json");
		file.write(text);
		file.close();
	}
}
