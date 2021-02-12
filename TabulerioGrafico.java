package projetooitorainhas;
import java.awt.*;
/**
 * A Classe TabuleiroGrafico irá se relacionar com a classe Mensagem e com a classe Tabuleiro. 
 * Irá herdar os atributos da classe Canvas para estender suas funcionalidades. 
 * A classe TabuleiroGrafico é a responsável por gerenciar  a parte gráfica de um tabuleiro (casinhas), como: mostrar na tela as cores das casinhas e a representação de uma rainha na casinha.
 * O construtor irá criar um objeto do tipo Tabuleiro que é onde serão adquiridas informações para serem desenhadas na parte gráfica do tabuleiro.
 * @author Ester e Amanda
 * @version 1.00
 * @since Release 01 da aplicação
 */
public class TabuleiroGrafico extends Canvas 
{
    private Graphics parteGrafica;
    private Tabuleiro tabuleiroLogico;
    private int larguracasinha; 
    private int alturacasinha; 
/**
 * A Classe TabuleiroGrafico irá se relacionar com a classe Mensagem e com a classe Tabuleiro.
 * Irá herdar os atributos da classe Canvas para estender suas funcionalidades. 
 * A classe TabuleiroGrafico é a responsável por gerenciar  a parte gráfica de um tabuleiro (casinhas), como: mostrar na tela as cores das casinhas e a representação de uma rainha na casinha.
 */
    public TabuleiroGrafico()
    {
        this.tabuleiroLogico = new Tabuleiro();
        this.parteGrafica = this.getGraphics();
    }
/**
 * Método @Override vem da classe Canvas e sobreescrevemos. Este método é responsável por chamar o método interno pintarTabuleiro.
 * @param g 
 */
    @Override //paint vem da classe Applet e sobreescrevemos
    public void paint(Graphics g) {
        pintarTabuleiro(g);
    }
    /**
     * Tabuleiro com letras/números ao redor, chama os métodos internos desenhaCasinha
     * Método que constrói as casinhas do tabuleiro na parte gráfica setando as propriedades de cor dentro do mesmo.
     * @param g será renomeado dentro desse método para parteGrafica
     */    
    public void pintarTabuleiro(Graphics g) {
        parteGrafica = g;
        larguracasinha = getBounds().width/10;
        alturacasinha = getBounds().height/10;
        String colunas[] = {"-", "a","b","c","d","e","f","g","h"};
        //String linhas[] = {"0","1","2","3","4","5","6","7","8"};
        String linhas[] = {"-","8","7","6","5","4","3","2","1"};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                desenhaCasinha(i,j);
            }
        }
        parteGrafica.setColor(Color.black); // Cor das letras ao redor do tabuleiro
        int deslocar = alturacasinha/4;
        for (int i = 1; i < 9; i++) { //colocando as letrinhas ao redor do tabuleiro
            //linha superior
            parteGrafica.drawString(colunas[i], i*larguracasinha + larguracasinha/2, alturacasinha/2 + deslocar);
            //linha inferior
            parteGrafica.drawString(colunas[i], i*larguracasinha + larguracasinha/2, alturacasinha/2  + alturacasinha * 9);
            //coluna esquerda
            parteGrafica.drawString(linhas[i], larguracasinha/2 + deslocar, i*alturacasinha + alturacasinha/2 );
            //coluna direita
            parteGrafica.drawString(linhas[i], larguracasinha/2 -deslocar*2 + larguracasinha * 9, i*alturacasinha + alturacasinha/2 );
        }
    }
    /**
     * Desenha uma casinha no tabuleiro, em específico, chamada o método casasTabuleiro
     * Método responsável por desenhar cada casa dentro do tabuleiro, bem como a cor da mesma, largura e altura de cada. 
     * Chama o método insereCoroa.
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     */
    private void desenhaCasinha(int x, int y) {
        int coresAlternadas = 0;
        parteGrafica.setColor(Color.yellow);
        parteGrafica.drawRect((x+1)*larguracasinha, (y+1)*alturacasinha, larguracasinha, alturacasinha); //(y+1) para transladar
        if ((x + y) % 2 == 0) {    
            coresAlternadas = 10;
        }     
        switch (tabuleiroLogico.getValor(x, y)) {
            case 0:
                parteGrafica.setColor(new Color(230+coresAlternadas, 230+coresAlternadas, 230+coresAlternadas)); 
                break;

            case 1: // área de conflito com alguma(s) rainha(s) do tabuleiro
                parteGrafica.setColor(new Color(230+coresAlternadas, 230+coresAlternadas, 230+coresAlternadas)); 
              //parteGrafica.setColor(new Color(160,110,110)); //se quiser ver as dicas
                break;
            case 2:
                parteGrafica.setColor(new Color(0,0,0));
                break;
        }
        //aqui abaixo é a linha que desenha a casinha com a cor selecionada acima
        parteGrafica.fillRect((x+1)*larguracasinha+1, (y+1)*alturacasinha+1, larguracasinha-1, alturacasinha-1);
        insereCoroa(x, y); // se for o caso
    }
    /** 
     * Método é para adicionar o desenho da coroa na casinha que for marcada como da rainha.
     * Método o que insere uma Coroa na casinha que estiver marcada como tendo uma rainha. 
     * Caso o tamanho da tela seja menor do que a padrão este desenho se resumirá a uma letra W.
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     */
    private void insereCoroa(int x, int y)
    {
        if (tabuleiroLogico.getValor(x, y) != 2) {
            return; // não tem rainha neste x,y            
        }
        Dimension xy = getSize(); //Dimensoes padrão são 800 por 569 pra exibir coroa
        parteGrafica.setColor(Color.red);
        if (xy.width < 569 || xy.height < 569) {
            // apenas letra 'W' se área disponível for pequena
            int coordx = (x+1)*larguracasinha + (larguracasinha-6)/2; // letra W supomos ter 6x6 de tamanho
            int coordy = (y+1)*alturacasinha + (alturacasinha+6)/2;
            parteGrafica.drawString("W", coordx, coordy);                                            
            return;
        }
        int mediaa = (larguracasinha - 50)/2; //coroa supomos ter 50x50
        int mediab = (alturacasinha - 50)/2;
        int a = (x+1)*larguracasinha + mediaa;
        int b = (y+1)*alturacasinha + mediab;
        parteGrafica.drawLine(a+5, b+45, a+45, b+45);
        parteGrafica.drawLine(a+5, b+45, a+5, b+5);
        parteGrafica.drawLine(a+45, b+45, a+45, b+5);
        parteGrafica.drawLine(a+5, b+35, a+45, b+35);
        parteGrafica.drawLine(a+5, b+5, a+15, b+20);
        parteGrafica.drawLine(a+15, b+20, a+25, b+5);
        parteGrafica.drawLine(a+25, b+5, a+35, b+20);
        parteGrafica.drawLine(a+35, b+20, a+45, b+5);
        parteGrafica.drawOval(a+20, b+20, 10, 10);
    }  
    /**
     * Método que é acionado ao clicar no botão limpar
     * Método acionado quando o botão limpar é clicado. 
     * Nele é chamado a o método iniciarTabuleiro() da classe Tabuleiro que inicia e desenha o tabuleiro novamente instanciando o método interno pintarTabuleiro(Graphics g)) passando como parâmetro parteGrafica.
     * @return objeto do tipo Mensagem
     */
    public Mensagem botaoLimpar() { 
        parteGrafica = this.getGraphics();
        tabuleiroLogico.iniciarTabuleiro();
        pintarTabuleiro(parteGrafica);
        return new Mensagem(0,0,0); //tudo ocorreu bem
    }
    /**
     * Método que é acionado ao clicar no botão remover rainha
     * Método acionado quando o botão remove é clicado. 
     * Primeramente neste método é verificado se as coordenadas são válidas para a rainha ser removida. 
     * Posteriormente os métodos externos da classe Tabuleiro temRainha(x, y)) e removeRainha(x, y) são chamados que em síntese irão verificar se existe rainha no tabuleiro com as coordenadas informadas e a remoção da rainha, ou não. 
     * Finalmente, o método interno pintarTabuleiro(Graphics g)) é chamado passando como parâmetro $parteGrafica$.
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     * @return objeto do tipo Mensagem 
     */
    public Mensagem botaoRemove(int x, int y) {
        parteGrafica = this.getGraphics();
        if (x < 0 || y < 0) {
            return new Mensagem(99,0,0); //coordenadas invalidas   
        }
        if (tabuleiroLogico.temRainha(x, y)) { // teste para ver se tem alguém para ser removido
            tabuleiroLogico.removeRainha(x, y); //chamada de método externo
            pintarTabuleiro(parteGrafica);
        } else {
            return new Mensagem(5,x,y); //Não existe rainha na linha " + linha + " e coluna " + coluna
        }            
        return new Mensagem(0,0,0); //tudo ocorreu bem
    }
    /**
     *Jogador clicou no botão Adiciona para adicionar uma rainha.
     * Método acionado quando o botão adiciona é clicado. 
     * Primeramente neste método é verificado se as coordenadas são válidas para a rainha ser adicionada. 
     * Em seguida, chama os métodos externos da classe Tabuleiro 
     * aceitaRainha(x, y)) e adicionaRainha(x, y) que em síntese irão verificar se uma nova rainha pode ser inserida no tabuleiro e consequentemente fazendo a adição ou não no tabuleiro. 
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     * @return Mensagem
     */
    public Mensagem botaoAdiciona(int x, int y) {
        parteGrafica = this.getGraphics();
        if (x < 0 || y < 0) {
            return new Mensagem(99,0,0); //coordenadas invalidas   
        }
        if (tabuleiroLogico.aceitaRainha(x, y)){//chamada de método externo - Dá para adicionar a rainha?
            tabuleiroLogico.adicionaRainha(x, y); //chamada de método interno           
            pintarTabuleiro(parteGrafica);
            if ( tabuleiroLogico.fimDeJogo() ) {
                return new Mensagem(100,0,0); //Parabéns Você conseguiu colocar as 8 rainhas
            }
        } else {
           return tabuleiroLogico.rainhaAtaca(x,y);
        }
        return new Mensagem(0,0,0); 
    }
}
