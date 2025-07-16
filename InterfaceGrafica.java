import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class InterfaceGrafica extends JFrame implements ActionListener {
    ConexaoBancoDeDados objBancoDeDados;

    // Atributos para a interface grafica
    private JButton botaoINSERIR;
    private JButton botaoREMOVER;
    private JButton botaoALTERAR;
    private JButton botaoPESQUISAR;
    private JButton botaoCalcularIMC;
    private JButton botaoRelatorio;
    private JButton botaoComplete;

    private JLabel LabelNome;
    private JLabel LabelEndereco;
    private JLabel LabelTelefone;
    private JLabel LabelCPF;
    private JLabel LabelTipoSanguineo;
    private JLabel LabelFatorRH;
    private JLabel LabelCurso;
    private JLabel LabelTelefoneEmergencia;
    private JLabel LabelContatoEmergencia;
    private JLabel LabelPeso, LabelAltura, LabelResultado;
    private JComboBox<String> ComboTipoSanguineo;
    private JComboBox<String> ComboFatorRH;
    private JComboBox<String> ComboCurso;

    private JLabel LabelResultadoPesquisa;
    private JLabel LabelMensagem;
    private JLabel LabelId;

    private JTextField TextNome;
    private JTextField TextEndereco;
    private JTextField TextTelefone;
    private JFormattedTextField TextCPF;
    private JTextField TextTelefoneEmergencia;
    private JTextField TextContatoEmergencia;
    private JTextField TextPeso, TextAltura, TextResultado;

    private JTextField TextId;

    private JTextArea listaPesquisaBancoDeDados;
    private JScrollPane scrollPesquisaBancoDeDados;

    private Container janelaprincipal;

    public InterfaceGrafica() {
        setSize(1000, 370); // tamanho da janela principal
        setTitle("Conexão com Banco de Dados"); // titulo da janela
        janelaprincipal = getContentPane(); // pegar uma referencia para a janela principal
        janelaprincipal.setLayout(null); // limpar todo o conteudo da janela

        TextNome = new JTextField();
        TextEndereco = new JTextField();
        TextContatoEmergencia = new JTextField();

        TextPeso = new JTextField();
        TextAltura = new JTextField();
        TextResultado = new JTextField();
        TextResultado.setEditable(false);

        TextId = new JTextField();

        // criar os componentes da interface grafica
        botaoINSERIR = new JButton("Cadastrar");
        botaoREMOVER = new JButton("Remover");
        botaoALTERAR = new JButton("Alterar");
        botaoPESQUISAR = new JButton("Listagem");
        botaoComplete = new JButton("Pesquisar");
        botaoCalcularIMC = new JButton("Calcular IMC");
        botaoRelatorio = new JButton("Relatório");
        botaoCalcularIMC.addActionListener(this);

        LabelMensagem = new JLabel("----");
        LabelNome = new JLabel("Nome");
        LabelId = new JLabel("ID");
        LabelEndereco = new JLabel("Endereco");

        LabelPeso = new JLabel("Peso (kg):");
        LabelAltura = new JLabel("Altura (m):");
        LabelResultado = new JLabel("Resultado:");

        LabelTelefone = new JLabel("Telefone");
        try {
            TextTelefone = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LabelCPF = new JLabel("CPF");
        try {
            TextCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LabelCurso = new JLabel("Curso");

        LabelTelefoneEmergencia = new JLabel("Telefone Emergência");
        try {
            TextTelefoneEmergencia = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LabelContatoEmergencia = new JLabel("Contato Emergencia");
        LabelTipoSanguineo = new JLabel("Tipo Sanguíneo:");
        LabelFatorRH = new JLabel("Fator RH:");

        String[] tiposSanguineos = {"A", "B", "AB", "O"};
        String[] fatoresRH = {"+", "-"};
        String[] cursos = {"Direito", "Ciência da Computação", "Sistemas de Informação", "Medicina", "Psicologia", "Nutrição"};
        ComboTipoSanguineo = new JComboBox<>(tiposSanguineos);
        ComboFatorRH = new JComboBox<>(fatoresRH);
        ComboCurso = new JComboBox<>(cursos);

        LabelResultadoPesquisa = new JLabel("Resultado da Pesquisa no Banco de Dados");

        listaPesquisaBancoDeDados = new JTextArea();
        scrollPesquisaBancoDeDados = new JScrollPane(listaPesquisaBancoDeDados);

        // configurar o posicionamento dos componentes na tela

        // Inicialmente, esconder os botões e o campo ID

        

        botaoINSERIR.setBounds(10, 600, 90, 40); // deslocamentos na tela: coluna linha comprimento altura
        botaoREMOVER.setBounds(10, 650, 100, 40); // Botão Remover
        botaoALTERAR.setBounds(115, 650, 100, 40); // Botão Alterar
        botaoPESQUISAR.setBounds(220, 650, 100, 40); // Botão Pesquisar
        botaoCalcularIMC.setBounds(109, 600, 120, 40);
        botaoRelatorio.setBounds(235, 600, 120, 40);
        botaoComplete.setBounds(328, 650, 100, 40);

        LabelMensagem.setBounds(50, 600, 250, 20);
        LabelNome.setBounds(10, 40, 80, 20);
        LabelEndereco.setBounds(10, 85, 100, 20);
        LabelTelefone.setBounds(10, 130, 100, 20);
        LabelCPF.setBounds(10, 175, 100, 20);
        LabelCurso.setBounds(10, 270, 100, 20);
        LabelResultadoPesquisa.setBounds(480, 5, 300, 20);

        LabelTelefoneEmergencia.setBounds(8, 320, 200, 20);
        TextTelefoneEmergencia.setBounds(150, 320, 200, 25);

        LabelContatoEmergencia.setBounds(8, 360, 200, 20);
        TextContatoEmergencia.setBounds(150, 360, 200, 25);

        LabelPeso.setBounds(20, 400, 100, 25);
        TextPeso.setBounds(150, 400, 200, 25);
        LabelAltura.setBounds(20, 450, 100, 25);
        TextAltura.setBounds(150, 450, 200, 25);
        LabelResultado.setBounds(20, 500, 100, 25);
        TextResultado.setBounds(150, 500, 200, 25);

        LabelId.setBounds(190, 550, 30, 30);

        scrollPesquisaBancoDeDados.setBounds(420, 25, 450, 235);
        TextNome.setBounds(150, 40, 200, 25);
        TextEndereco.setBounds(150, 85, 200, 25);
        TextTelefone.setBounds(150, 130, 200, 25);
        TextCPF.setBounds(150, 175, 200, 25);
        LabelTipoSanguineo.setBounds(8, 220, 140, 25);
        ComboTipoSanguineo.setBounds(150, 220, 50, 25);
        LabelFatorRH.setBounds(230, 220, 80, 25);
        ComboFatorRH.setBounds(300, 220, 50, 25);
        ComboCurso.setBounds(150, 270, 200, 25);
        TextId.setBounds(220, 550, 30, 30);

        // adicionar os componentes na tela
        janelaprincipal.add(botaoINSERIR);
        janelaprincipal.add(botaoREMOVER);
        janelaprincipal.add(botaoALTERAR);
        janelaprincipal.add(botaoPESQUISAR);
        janelaprincipal.add(botaoComplete);
        janelaprincipal.add(LabelEndereco);
        janelaprincipal.add(LabelNome);
        janelaprincipal.add(LabelTelefone);
        janelaprincipal.add(LabelCPF);

        janelaprincipal.add(LabelPeso);
        janelaprincipal.add(TextPeso);
        janelaprincipal.add(LabelAltura);
        janelaprincipal.add(TextAltura);
        janelaprincipal.add(LabelResultado);
        janelaprincipal.add(TextResultado);
        janelaprincipal.add(botaoCalcularIMC);
        janelaprincipal.add(botaoRelatorio);
        janelaprincipal.add(LabelResultadoPesquisa);
        janelaprincipal.add(scrollPesquisaBancoDeDados);

        janelaprincipal.add(LabelTelefoneEmergencia);
        janelaprincipal.add(TextTelefoneEmergencia);

        janelaprincipal.add(LabelContatoEmergencia);
        janelaprincipal.add(TextContatoEmergencia);

        janelaprincipal.add(TextNome);
        janelaprincipal.add(TextEndereco);
        janelaprincipal.add(TextTelefone);
        janelaprincipal.add(TextCPF);

        janelaprincipal.add(LabelTipoSanguineo);
        janelaprincipal.add(ComboTipoSanguineo);
        janelaprincipal.add(LabelFatorRH);
        janelaprincipal.add(ComboFatorRH);
        janelaprincipal.add(LabelCurso);
        janelaprincipal.add(ComboCurso);
        janelaprincipal.add(ComboCurso);
        janelaprincipal.add(LabelMensagem);
        janelaprincipal.add(LabelId);
        janelaprincipal.add(TextId);

        // fazer com que todos os componentes fiquem visiveis na tela
        setVisible(true);

        // inserir tratamento dos eventos para os botões INICIAR E PARAR
        botaoINSERIR.addActionListener(this);
        botaoREMOVER.addActionListener(this);
        botaoALTERAR.addActionListener(this);
        botaoPESQUISAR.addActionListener(this);
        botaoComplete.addActionListener(this);
        botaoCalcularIMC.addActionListener(this);
        botaoRelatorio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar se o usuario clicou no botao "inserir"
        if (e.getActionCommand().equals("Cadastrar")) {
            String nome = TextNome.getText();
            String endereco = TextEndereco.getText();
            String telefone = TextTelefone.getText();
            String cpf = TextCPF.getText();
            String tipoSanguineo = (String) ComboTipoSanguineo.getSelectedItem();
            String fatorRH = (String) ComboFatorRH.getSelectedItem();
            String curso = (String) ComboCurso.getSelectedItem();
            String telefoneemergencia = TextTelefoneEmergencia.getText();
            String contatoemergencia = TextContatoEmergencia.getText();
            double peso = Double.parseDouble(TextPeso.getText());
            double altura = Double.parseDouble(TextAltura.getText());

            Pessoa objeto = new Pessoa(nome, endereco, telefone, cpf, tipoSanguineo, fatorRH, curso, telefoneemergencia, contatoemergencia, peso, altura);

            try {
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                String mensagem = objBancoDeDados.InserirDados(objeto);
                JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar o cadastro. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }

        // Verificar se o usuario clicou no botão "Remover"
        if (e.getActionCommand().equals("Remover")) {
//            Pessoa objeto = new Pessoa(TextNome.getText(), TextEndereco.getText(), TextTelefone.getText(), TextCPF.getText(), ComboTipoSanguineo.getActionCommand(), ComboFatorRH.getActionCommand(), ComboCurso.getActionCommand(), TextTelefoneEmergencia.getText(), TextContatoEmergencia.getText(), Double.parseDouble(TextPeso.getText()), Double.parseDouble(TextAltura.getText()));

            try {
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                int id = Integer.parseInt(TextId.getText());
                String mensagem = objBancoDeDados.RemoveDados(id);
                JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "Erro ao remover dados. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }

        // Verificar se o usuario clicou no botao "Alterar"
        if (e.getActionCommand().equals("Alterar")) {
        	Pessoa objeto = new Pessoa(TextNome.getText(), TextEndereco.getText(), TextTelefone.getText(), TextCPF.getText(), (String) ComboTipoSanguineo.getSelectedItem(), (String) ComboFatorRH.getSelectedItem(), (String) ComboCurso.getSelectedItem(), TextTelefoneEmergencia.getText(), TextContatoEmergencia.getText(), Double.parseDouble(TextPeso.getText()), Double.parseDouble(TextAltura.getText()));

            try {
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                int id = Integer.parseInt(TextId.getText());
                String mensagem = objBancoDeDados.AlterarDados(objeto, id);
                JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar dados. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }

        // verificar se o usuario clicou no botao "Pesquisar"
        if (e.getActionCommand().equals("Listagem")) {
            try {
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                ArrayList<String> relatorioBancoDeDados = objBancoDeDados.Relatorio();

                listaPesquisaBancoDeDados.setText("");
                for (String texto : relatorioBancoDeDados) {
                    listaPesquisaBancoDeDados.append(texto + "\n\n");
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "Erro ao acessar o Banco de Dados. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
        
        if(e.getActionCommand().equals("Pesquisar")) {
        	ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
        	int id = Integer.parseInt(TextId.getText());
        	try {
        		Pessoa pessoa = objBancoDeDados.pesquisar(id);
        	
	        	int indexTipoSanguineo = 0;
	        	int indexFatorRh = 0;
	        	int indexCurso = 0;
	        	
	        	String nome = pessoa.getNome();
	    		String endereco = pessoa.getEndereco();
	    		String telefone = pessoa.getTelefone();
	    		String cpf = pessoa.getCPF();
	    		String tiposanguineo = pessoa.getTipoSanguineo();
	    		String fatorrh = pessoa.getfatorRH();
	    		String curso = pessoa.getCurso();
	    		String telefoneemergencia = pessoa.getTelefoneEmergencia();
	    		String contatoemergencia = pessoa.getContatoEmergencia();
	    		Double peso = pessoa.getPeso();
	    		Double altura = pessoa.getAltura();
	    		Double imc = pessoa.calcularIMC();
	        	
	    		
	    		
	    		switch(tiposanguineo) {
	    		case("A"):
	    			indexTipoSanguineo = 0;
	    		case("B"):
	    			indexTipoSanguineo = 1;
	    		case("AB"):
	    			indexTipoSanguineo = 2;
	    		case("O"):
	    			indexTipoSanguineo = 3;
	    		}
	    		
	    		switch(fatorrh) {
	    		case("+"):
	    			indexFatorRh = 0;
	    		case("-"):
	    			indexFatorRh = 1;
	    		}
	    		
	    		switch(curso) {
	    		case("Direito"):
	    			indexCurso = 0;
	    		case("Ciência da Computação"):
	    			indexCurso = 1;
	    		case("Sistemas de Informação"):
	    			indexCurso = 2;
	    		case("Medicina"):
	    			indexCurso = 3;
	    		case("Psicologia"):
	    			indexCurso = 4;
	    		case("Nutrição"):
	    			indexCurso = 5;
	    		}
	    		
	    		
	    		
	    		TextNome.setText(nome);
	    		TextEndereco.setText(endereco);
	    		TextTelefone.setText(telefone);
	    		TextCPF.setText(cpf);
	    		TextTelefoneEmergencia.setText(telefoneemergencia);
	    		TextContatoEmergencia.setText(contatoemergencia);
	    		TextPeso.setText(String.format("%.2f", peso));
	    		TextAltura.setText(String.format("%.2f", altura));
	    		ComboTipoSanguineo.setSelectedIndex(indexTipoSanguineo);
	    		ComboFatorRH.setSelectedIndex(indexFatorRh);
	    		ComboCurso.setSelectedIndex(indexCurso);
	    		TextResultado.setText(String.format("%.2f", imc));
        	}catch(Exception e1){
        		System.out.println("erro aqui");
        		e1.printStackTrace();
        	}
        }

        if (e.getActionCommand().equals("Calcular IMC")) {
            try {
                try {
                    double peso = Double.parseDouble(TextPeso.getText());
                    double altura = Double.parseDouble(TextAltura.getText());

                    Pessoa pessoa = new Pessoa(TextNome.getText(), TextEndereco.getText(), TextTelefone.getText(), TextCPF.getText(), ComboTipoSanguineo.getActionCommand(), ComboFatorRH.getActionCommand(), ComboCurso.getActionCommand(), TextTelefoneEmergencia.getText(), TextContatoEmergencia.getText(), peso, altura);
                    double resultado = pessoa.calcularIMC();
                    TextResultado.setText(String.format("%.2f", resultado));
                    if(resultado >= 18.5 && resultado <= 25) {
                    	JOptionPane.showMessageDialog(this, "Peso Ideal", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(resultado > 25) {
                    	JOptionPane.showMessageDialog(this, "Você está acima do peso ideal", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(resultado < 18.5) {
                    	JOptionPane.showMessageDialog(this, "Você está abaixo do peso ideal", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Insira valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao calcular o IMC. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        if (e.getActionCommand().equals("Relatório")) {
        	System.out.println("botao");
        	ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
        	StringBuilder relatorio = objBancoDeDados.gerarRelatorio();
        	listaPesquisaBancoDeDados.setText(relatorio.toString());
        }
    }
}