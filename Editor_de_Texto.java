import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Editor_de_Texto extends JFrame {
	private Container c1;
	private JLabel lbTextBox, lbStatus, lbFont, lbEstilo, lbSize;
	private JButton btSave, btAbrir, btLimpar, btCriar_Dir, btExcluir_Dir;
	
	private JComboBox<String> novaFonte;
	private JComboBox<Integer> novoTamanho;
	private JComboBox<String> novoEstilo;
	private ArrayList<Integer> tamanhoPermitido;
	
	private Integer codigoEstilo;
	private JTextField tfTexto;
	private JTextArea taTextArea;
	private JScrollPane scrollPane;
	
	private FileDialog fdAbrir, fdSalvar;
	private String nome_do_arquivo;
	
	ComponentOrientation posicao = ComponentOrientation.LEFT_TO_RIGHT;

	public static void main(String args[]) {
		JFrame frame = new Editor_de_Texto();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //Centraliza a tela
		frame.setVisible(true);
	}

	public Editor_de_Texto() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {
		c1 = getContentPane();
		
		//JANELA
		setTitle("WORD");
		setLayout(null);
		setBounds(250, 50, 720, 400);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 80, 700, 200);
		scrollPane.setComponentOrientation(posicao);
		
		taTextArea = new JTextArea();	
		taTextArea.setEditable(true); 
		scrollPane.setViewportView(taTextArea);
				
		tfTexto = new JTextField();
		tfTexto.setEditable(false);
		
		lbFont = new JLabel("Fonte");
		lbEstilo = new JLabel("Estilo");
		lbSize = new JLabel("Tamanho");
		lbTextBox = new JLabel("Digite o texto aqui: ");
		lbStatus = new JLabel("Status: ");
		btAbrir = new JButton("Abrir");
		btSave = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		btCriar_Dir = new JButton("+ Criar Diretório");
		btExcluir_Dir = new JButton("- Excluir Diretório");
		
		taTextArea.setFont(new Font("Arial", Font.PLAIN, 8));
		
		//Monta combo Fontes
		//Carrega Fontes disponiveis
		novaFonte = new JComboBox();
		novaFonte.setEditable(false); //Não deixa editar
		novaFonte.setBounds(5, 28, 205, 22);
		add(novaFonte);
		loadFontes();		
				
		//Carrega Estilo da Fonte
		novoEstilo = new JComboBox();
		novoEstilo.setEditable(false);
		novoEstilo.setBounds(230, 28, 113, 22);
		add(novoEstilo);
		loadEstilo();
		
		//Carrega Tamanho da Fonte
		loadTamanhoFonte();
		novoTamanho = new JComboBox(tamanhoPermitido.toArray());
		novoTamanho.setBounds(360, 28, 56, 22);
		add(novoTamanho);
		
		novaFonte.setSelectedItem("Arial");
		
		btCriar_Dir.setBorder(BorderFactory.createLineBorder(new Color(180, 8, 8), 2));
		btCriar_Dir.setBorderPainted(false);	 
		btCriar_Dir.setContentAreaFilled(false);
		btExcluir_Dir.setBorder(BorderFactory.createLineBorder(new Color(180, 8, 8), 2));
		btExcluir_Dir.setBorderPainted(false);	
		btExcluir_Dir.setContentAreaFilled(false);
						
		//Positions
		lbFont.setBounds(5, 10, 80, 20);
		lbEstilo.setBounds(230, 10, 80, 20);
		lbSize.setBounds(360, 10, 200, 20);
		lbTextBox.setBounds(5, 60, 200, 20);
		lbStatus.setBounds(5, 335, 200, 20);
		btAbrir.setBounds(5, 290, 70, 25);	
		btSave.setBounds(100, 290, 80, 25);			
		btLimpar.setBounds(205, 290, 80, 25);
		btCriar_Dir.setBounds(425, 290, 130, 25);			
		btExcluir_Dir.setBounds(565, 290, 134, 25);
		tfTexto.setBounds(50, 335, 650, 20);
		
		fdAbrir = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSalvar = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		
		add(lbFont);
		add(lbEstilo);
		add(lbSize);
		add(lbTextBox);
		add(lbStatus);
		add(tfTexto);
		add(scrollPane);
		add(btSave);
		add(btAbrir);
		add(btLimpar);
		add(btCriar_Dir);
		add(btExcluir_Dir);
	}

	public void definirEventos() {
		//Monitora alteracao na combo Fonte
		novaFonte.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int estiloAtual=tipoEstilo();
				taTextArea.setFont(new Font((String) novaFonte.getSelectedItem(),estiloAtual, (Integer) novoTamanho.getSelectedItem()));
			}
		});
		
		//Monitora alteracao na combo Estilo
		novoEstilo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int estiloAtual=tipoEstilo();
				taTextArea.setFont(new Font((String) novaFonte.getSelectedItem(),estiloAtual, (Integer) novoTamanho.getSelectedItem()));
			}
		});
		
		//Monitora alteracao na combo Tamano 
		novoTamanho.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int estiloAtual=tipoEstilo();
				taTextArea.setFont(new Font((String) novaFonte.getSelectedItem(),estiloAtual, (Integer) novoTamanho.getSelectedItem()));
			}
		});
		
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTextArea.setText("");
				tfTexto.setText("");
			}
		});
		btSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdSalvar.setVisible(true);
					if (fdSalvar.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdSalvar.getDirectory()
							+ fdSalvar.getFile();
					FileWriter out = new FileWriter(nome_do_arquivo);
					out.write(taTextArea.getText());
					out.close();
					tfTexto.setText("Arquivo gravado com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao gravar no arquivo"
							+ erro.toString());
				}

			}
		});
		btAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdAbrir.setVisible(true);
					if (fdAbrir.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdAbrir.getDirectory()
							+ fdAbrir.getFile();
					FileReader in = new FileReader(nome_do_arquivo);
					String s = "";
					int i = in.read();
					while (i != -1) {
						s = s + (char) i;
						i = in.read();
					}
					taTextArea.setText(s);
					in.close();
					tfTexto.setText("Arquivo aberto com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao abrir no arquivo"
							+ erro.toString());
				}

			}
		});
		
		btCriar_Dir.addMouseListener(new MouseListener() {			
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				Criar_Dir a1 = new Criar_Dir();
				a1.Criar_Pasta();
				tfTexto.setText(""+a1.status);
			}
			public void mouseExited(MouseEvent e) {
				btCriar_Dir.setBorderPainted(false);
			}
			public void mouseEntered(MouseEvent e) {
				btCriar_Dir.setBorderPainted(true);
			}
			public void mouseClicked(MouseEvent e) {}
		});
		
		btExcluir_Dir.addMouseListener(new MouseListener() {			
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				Excluir_Dir a1 = new Excluir_Dir();
				a1.Excluir_Pasta();
				tfTexto.setText(""+a1.status);
			}
			public void mouseExited(MouseEvent e) {
				btExcluir_Dir.setBorderPainted(false);
			}
			public void mouseEntered(MouseEvent e) {
				btExcluir_Dir.setBorderPainted(true);
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	public void loadFontes() {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 String[] fonts = e.getAvailableFontFamilyNames(); // Get the fonts
		 for (String fnts : fonts) 
			 novaFonte.addItem(fnts);
	}
		
	//Tamanho de Fontes selecionaveis
	public void loadTamanhoFonte(){
		tamanhoPermitido = new ArrayList<Integer>();
		int tam = 8;
		do{
			tamanhoPermitido.add(tam);
			tam++;
		}while (tam <= 35);
	}
	
	//Define Estilos selecionaveis
	public void loadEstilo() {
		novoEstilo.addItem("Normal");
		novoEstilo.addItem("Bold");
		novoEstilo.addItem("Italic");
		novoEstilo.addItem("Bold and Italic");
	}
	
	//Recupera o codigo do tipo de estilo
	public int tipoEstilo()   {
        codigoEstilo = Font.PLAIN;
        if(novoEstilo.getSelectedItem().equals("Bold"))
        {
            codigoEstilo = Font.BOLD;
        }
        else if(novoEstilo.getSelectedItem().equals("Italic"))
        {
        	codigoEstilo = Font.ITALIC;
        }
        else if(novoEstilo.getSelectedItem().equals("Bold and Italic"))
        {
        	codigoEstilo = Font.BOLD|Font.ITALIC;
        }
        return codigoEstilo;
    }
	
}