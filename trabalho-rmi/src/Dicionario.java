public interface Dicionario extends java.rmi.Remote {

	public String consultar(String palavra) throws java.rmi.RemoteException;

	public String adicionar(String palavra, String significado) throws java.rmi.RemoteException;

	public String remover(String palavra) throws java.rmi.RemoteException;
}
