import java.io.File;
import javax.swing.JOptionPane;
public class Excluir_Dir{
	static String status;
	public static void Excluir_Pasta(){	
		String nome_past = JOptionPane.showInputDialog(null,"Digite o nome do diret�rio que quer EXCLUIR: ");
		
		if (nome_past.equals("")) {
			JOptionPane.showMessageDialog(null, "NOME INV�LIDO");
			status = "NOME INV�LIDO";
		}else {
		
			File dir = new File("c:/"+nome_past);
			String x = "";
			if(dir.isDirectory()){
				if(dir.delete()){
					x = dir.getName() + "A PASTA "+nome_past+" EXCLU�DO";
					JOptionPane.showMessageDialog(null, "EXCLU�DO COM SUCESSO");
					status = "A PASTA [ "+nome_past+" ] FOI EXCLU�DA COM SUCESSO";
				} 
			}else {
				JOptionPane.showMessageDialog(null, "EST� PASTA N�O EXISTE");
				status = "A PASTA [ "+nome_past+" ] N�O EXISTE";
			}
		}
	}
}
