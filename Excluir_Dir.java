import java.io.File;
import javax.swing.JOptionPane;
public class Excluir_Dir{
	static String status;
	public static void Excluir_Pasta(){	
		String nome_past = JOptionPane.showInputDialog(null,"Digite o nome do diretório que quer EXCLUIR: ");
		
		if (nome_past.equals("")) {
			JOptionPane.showMessageDialog(null, "NOME INVÁLIDO");
			status = "NOME INVÁLIDO";
		}else {
		
			File dir = new File("c:/"+nome_past);
			String x = "";
			if(dir.isDirectory()){
				if(dir.delete()){
					x = dir.getName() + "A PASTA "+nome_past+" EXCLUÍDO";
					JOptionPane.showMessageDialog(null, "EXCLUÍDO COM SUCESSO");
					status = "A PASTA [ "+nome_past+" ] FOI EXCLUÍDA COM SUCESSO";
				} 
			}else {
				JOptionPane.showMessageDialog(null, "ESTÁ PASTA NÃO EXISTE");
				status = "A PASTA [ "+nome_past+" ] NÃO EXISTE";
			}
		}
	}
}
