
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class DicionarioClient {

	public static void main(String[] args) {
		String servidor = "rmi://localhost/";
		String nome = "DicionarioService";
		try {
			Dicionario c = (Dicionario) Naming.lookup(servidor + nome);
			System.out.println("Objeto remoto \'"+ nome + "\' encontrado no servidor.");

			System.out.println("Adicionar: " + c.adicionar("teste", "significa teste"));
			System.out.println("Consultar: " + c.consultar("teste"));
			System.out.println("Remover: " + c.remover("teste"));

		} catch (MalformedURLException e) {
			System.out.println("URL \'" + servidor + nome + "\' mal formatada.");
		} catch (RemoteException e) {
			System.out.println("Erro na invoca��o remota.");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Objeto remoto \'" + nome + "\' n�o est� dispon�vel.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
