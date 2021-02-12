package projetooitorainhas;

import java.applet.Applet; // usa awt para graficos
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**Classe para objetos do tipo screen (tela), onde serão contidos, valores e métodos para o mesmo.
 * A Classe Rainha irá herdar os atributos da classe Applet estendendo suas funcionalidades com extends.
 * E implementa uma interface com implements ActionListener
 * interface é uma classe abstrata composta somente por métodos abstratos. Não pode ser instanciada. 
 * Contém as declarações dos métodos e constantes, nenhuma implementação é só o 'molde'.
 * Classe abstrata - utiliza abstract que impede ser instanciada, ie serve para polimorfismo e herança dos atributos e métodos
 * Polimorfismo é a capacidade de um objeto poder ser referenciado de várias formas.
 * Classe responsável por inicializar a aplicação e proporcionar ao usuário um interface gráfica simples. Desta forma a classe foi criada utilizando a palavra reservada extends para estender as funcionalidades da classe Screen. Também implementa uma interface a ActionListener por isso faz uso da palavra reservada implements.  
É na classe Screen que estão representadas as ações de adicionar ou remover rainha e também de limpar o tabuleiro. Toda interação da aplicação se dá a partir desta classe ela irá gerenciar toda estrutura gráfica externa do tabuleiro como a criação de botões, campos da interface, nome dos campos, letras e números das bordas.
Esta classe irá se relacionar com as classes TabuleiroGrafico e Mensagem.
 * @author Ester e Amanda
 * @version 1.00
 * @since Release 01 da aplicação
 */
public class Screen extends Applet implements ActionListener {
    private Button botaoAdicionaRainha;
    private Button botaoRemoveRainha;
    private Button botaoLimpar;
    private TabuleiroGrafico tabuleiro;    
    private TextField coordenadaLinha; 
    private TextField coordenadaColuna;
    private String colunas[] = {"-", "a","b","c","d","e","f","g","h"};
    private Graphics gr;
    /** O construtor da classe  Screen  irá herdar os atributos da classe  Applet  reescrevendo-os. 
     * No código a palavra reservada  @Override  indica a reescrita, neste caso o método vem da classe  Applet  e sobreescrevemos. 
     * Pode-se dizer que a reescrita de método que indica que este existe na classe pai com o mesmo nome. 
     * Este  método é invocado quando o usuário (jogador) clica em um botão ou quando digita em um campo de texto e pressiona Enter  ou  quando seleciona um item de menu. 
     * O método  init  é chamado automaticamente pelo  conteiner  da classe  applets ,o qual carrega o  applet , inicializa as variáveis de instância e cria a interface gráfica. 
     * Neste método iniciamos com a criação de botões, paínéis, posicionamento, dimensionamento, caixas de texto, cor, alinhamento e chamando a classe tabuleiroGrafico que irá desenhar o tabuleiro na tela e as demais atividades já criadas. O construtor da classe  Screen  irá herdar os atributos da classe  Applet  reescrevendo-os. 
     * Chama o método init();
     */
    public void Screen() {
        init();
    }
    /** 
     * No código a palavra reservada  @Override  indica a reescrita, neste caso o método vem da classe  Applet  e sobreescrevemos. 
     * Pode-se dizer que a reescrita de método que indica que este existe na classe pai com o mesmo nome. 
     * Este  método é invocado quando o usuário (jogador) clica em um botão ou quando digita em um campo de texto e pressiona Enter  ou  quando seleciona um item de menu. 
     * O método  init  é chamado automaticamente pelo  conteiner  da classe  applets ,o qual carrega o  applet , inicializa as variáveis de instância e cria a interface gráfica. 
     * Neste método iniciamos com a criação de botões, paínéis, posicionamento, dimensionamento, caixas de texto, cor, alinhamento e chamando a classe tabuleiroGrafico que irá desenhar o tabuleiro na tela e as demais atividades já criadas.
     */
    @Override 
    public void init() {
        gr = this.getGraphics();
        botaoAdicionaRainha = new Button("adiciona");
        botaoRemoveRainha = new Button("remove");
        botaoLimpar = new Button("Limpar");
        tabuleiro = new TabuleiroGrafico();
        coordenadaLinha = new TextField(5); //(lin, col)
        coordenadaColuna = new TextField(5); //(lin, col)
        setLayout(new BorderLayout());
        setSize(800, 600);
        setBackground(Color.white);
        Panel bPanel = new Panel();
        add("South", bPanel);  
        bPanel.setLayout(new GridLayout(1,6));
        bPanel.add(new Label(" Coluna de a - h"));
        bPanel.add(coordenadaLinha);    
        bPanel.add(new Label(" Linha de 1-8"));
        bPanel.add(coordenadaColuna);   
        bPanel.add(botaoAdicionaRainha);
        bPanel.add(botaoRemoveRainha);
        bPanel.add(new Label(" "));
        bPanel.add(botaoLimpar);
        bPanel.setBackground(Color.BLACK);
        bPanel.setForeground(Color.YELLOW);
        botaoRemoveRainha.addActionListener(this);
        botaoAdicionaRainha.addActionListener(this);
        botaoLimpar.addActionListener(this);
        JOptionPane.showMessageDialog(null, "Oito  Rainhas desafio lógico de dispor oito peças em um tabuleiro de forma que nenhuma seja atacada por outra. \n + Assim, faz-se necessário que duas rainhas quaisquer não estejam numa mesma linha, coluna ou diagonal.\n");
    }
    /**
     * Reescrita de método da API ActionListener;
     * É chamado depois que o usuário chama/executa uma ação (inserir ou excluir uma Rainha)
     * Este método é responsável por realizar uma determinada ação quando for disparado este evento, Dentro do mesmo, é criado uma variavel local para linha e coluna e faz a verificação se quando o botão for clicado,irá retornar uma mensagem que chama o método da classe tabuleiro a qual realiza todas as funções de controle para cada ação.
     * @param e do ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) { 
        String linha = coordenadaLinha.getText();
        String coluna = coordenadaColuna.getText();
        coordenadaLinha.setText("");
        coordenadaColuna.setText(""); // limpar campos fornecidos para proxima entrada
        int x = coordX(linha);
        int y = coordY(coluna);
        if (e.getSource() == botaoLimpar) { 
            showDialog(tabuleiro.botaoLimpar());
        } else if (e.getSource() == botaoAdicionaRainha) {             
            showDialog(tabuleiro.botaoAdiciona(x, y));
        } else if (e.getSource() == botaoRemoveRainha) {                   
            showDialog(tabuleiro.botaoRemove(x, y));
        }
    }
/**
 * Método do tipo privado, para que somente fique visivel a esta determinada classe. Dentro dele são definidos as mensagens da classe Mensagem, onde este método chamará outra classe para realizar determinada ação.
 * @param mensagens 
 */
    private void showDialog(Mensagem mensagens) {
        if (mensagens.getText().equals("ok"))
        {
            return;
        }
        JOptionPane.showMessageDialog(null, mensagens.getText());    
    }
    /**
     * Método para transformar letras das colunas (a-h) em números (0-7)
     * Este método transforma as letras das colunas de (a-h) em numeros de (0-7), pois cada letra digitada irá ser transformada em um numero dentro do tabuleiro, para que não haja conflito e as coordenadas fiquem iguais.
     * @param linha letras que representam as colunas do tabuleiro
     * @return int número da coluna
     */
    public int coordX(String linha){
        switch( linha )
        {
            case "a": return 0;
            case "b": return 1;
            case "c": return 2;
            case "d": return 3;
            case "e": return 4;
            case "f": return 5;
            case "g": return 6;
            case "h": return 7;
            default: return -1; //pra desconsiderar qualquer coisa diferente
        }    
    }
    /**
     * Método para transformar letras das colunas (1-7) em números (0-7)
     * Este método é como o anterior, porem ela transforma as linhas de (1-8) em numero de (0-7), para que tanto linha e coluna fiquei em numero e sejam dispostos de maneira correta no tabuleiro.
     * @param coluna número que representam as linhas do tabuleiro
     * @return int número da coluna
     */
      public int coordY(String coluna){
        switch( coluna )
        {
            case "8": return 0;
            case "7": return 1;
            case "6": return 2;
            case "5": return 3;
            case "4": return 4;
            case "3": return 5;
            case "2": return 6;
            case "1": return 7;
            default: return -1;
        } 
    }
}
