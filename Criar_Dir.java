import java.io.File;
import javax.swing.JOptionPane;

public class Criar_Dir {
	static String status;
	public static void Criar_Pasta(){
		String nome_past = JOptionPane.showInputDialog(null,"Digite o nome do diret�rio que quer CRIAR: ");
		
		if (nome_past.equals("")) {
			JOptionPane.showMessageDialog(null, "NOME INV�LIDO");
			JOptionPane.showMessageDialog(null, "DIRET�RIO N�O FOI CRIADO");
			status = "NOME INV�LIDO";
		}else {
			File dir = new File("c:/"+nome_past);
			if (!dir.exists()){
				dir.mkdir();
			}
					
			JOptionPane.showMessageDialog(null, "DIRET�RIO FOI CRIADO COM SUCESSO");
			status = "A PASTA [ "+nome_past+" ] FOI CRIADA COM SUCESSO";
		}
	}

}
