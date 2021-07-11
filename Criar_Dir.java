import java.io.File;
import javax.swing.JOptionPane;

public class Criar_Dir {
	static String status;
	public static void Criar_Pasta(){
		String nome_past = JOptionPane.showInputDialog(null,"Digite o nome do diretório que quer CRIAR: ");
		
		if (nome_past.equals("")) {
			JOptionPane.showMessageDialog(null, "NOME INVÁLIDO");
			JOptionPane.showMessageDialog(null, "DIRETÓRIO NÃO FOI CRIADO");
			status = "NOME INVÁLIDO";
		}else {
			File dir = new File("c:/"+nome_past);
			if (!dir.exists()){
				dir.mkdir();
			}
					
			JOptionPane.showMessageDialog(null, "DIRETÓRIO FOI CRIADO COM SUCESSO");
			status = "A PASTA [ "+nome_past+" ] FOI CRIADA COM SUCESSO";
		}
	}

}
