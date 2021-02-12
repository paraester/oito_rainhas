package projetooitorainhas;
/**
 * Classe para objetos do tipo Tabuleiro, onde serão contidos, valores e métodos para o mesmo.
 * A classe Tabuleiro é a responsável por gerenciar toda lógica de alocação e remoção rainha no jogo.
 * O contrutor é responsável por iniciar o tabuleiro sempre com zero (0) em cada casinha.
 * Este construtor chama os métodos internos iniciarTabuleiro() e imprimeTabuleiro().
 * @author Ester Campos e Amanda Fogaça
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Tabuleiro {
    private int larguracasinha; //colunas
    private int alturacasinha; //linhas
    private int quantidadeRainhas = 0; //nenhuma rainha adicionada
    private int[][] casasTabuleiro = new int[8][8];
    private String colunas[] = {"-", "a","b","c","d","e","f","g","h"};
    /**
    * Contrutor Tabuleiro 8 Rainhas - Possui 64 casinhas, que são lugares onde podem ser colocadas peças chamadas Rainhas, neste aplicativo será possível realizar a colocação de 8 Rainhas em 8 casinhas distintas, seguindo as regras de check/ataque para rainhas.
    */
    public Tabuleiro() {
        iniciarTabuleiro();
        System.out.println("---iniciado o tabuleiro vazio----");   // apenas para log     
        imprimeTabuleiro();
    }
    /**
     * Método que carrega o tabuleiro atribuindo o valor de cada casinha como zero (0), atribuindo que no inicio nenhuma rainha foi adicionada.
     */
    public void iniciarTabuleiro(){
        quantidadeRainhas = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casasTabuleiro[i][j] = 0;
            }
        }
    }
    /**
     * Método que irá verificar no tabuleiro qual é o valor que está na casinha do tabuleiro
     * Retorna o valor que está na casinha do tabuleiro dado as coordenadas (x,y). 
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     * @return inteiro
     */
    public int getValor(int x, int y) {
        if (dentroTabuleiro(x,y)) {
            return casasTabuleiro[x][y];
        }
        return -1;        
    }
    /** Método que irá informar se na casinha do tabuleiro tem Rainha ou não.
     * Método retorna verdadeiro ou falso se há rainha ou não na casa dentro do tabuleiro. Utiliza o método dentroTabuleiro(x,y) para verificar se as coordenadas pertencem ao tabuleiro e se naquelas coordenadas a casinha tem valor igual a dois (2)
     * Chama o método privado dentroTabuleiro(x,y), que é quem verifica se as coordenadas em questão pertencem ao tabuleiro.
     * @return boolean 
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     */
    public boolean temRainha(int x, int y)
    {
        if (dentroTabuleiro(x,y) && casasTabuleiro[x][y] == 2)
        {
            return true;           
        }
        return false;
    }
    /**Método para retorno se a casinha está com valor zero isso é se permite adicionar Rainha 
     * Chamada de método interno privado dentroTabuleiro(int x, int y), que verifica se x e y respectivamente pertencem [0-8] 
     * e verifica se na grid do tabuleiro a casinha está disponível, ie casasTabuleiro[x][y] == 0
     * Método retorna verdadeiro ou falso se poderá aceitar rainha ou não na casinha específicada dentro do tabuleiro. 
     * Utiliza o método dentroTabuleiro(x,y) para verificar se as coordenadas pertencem ao tabuleiro e se naquelas coordenadas a casinha tem valor igual a zero (0) isto é casasTabuleiro[x][y] == 0 , condições necessárias para adicionar uma rainha.
     * @return boolean 
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     */
    public boolean aceitaRainha(int x, int y)
    {
        if (dentroTabuleiro(x,y) && casasTabuleiro[x][y] == 0)  //(2,2) && casinha vazias igual a zero
        {
           return true;           
        }
       return false;
    }                         
    /** 
     * Devolve true se a posição no tabuleiro está ok, ie, está liberada
     * Condição importante para aceitar um rainha na casinha e também para adicionar
     * Analisa se as coordenadas são maiores ou iguais a zero e menores ou iguais a 8
     * Resumidamente o método privado que verifica se as coordenadas recebidas pertencem ao tabuleiro e se são maiores que zero e menores do que oito, uma condição importante, tanto para adicionar quanto para remover uma rainha do tabuleiro. 
     * Ele retorna true ou false
     * @param x parâmetro do tipo inteiro 
     * @param y parâmetro do tipo inteiro 
     * @return true ou false
     */
    private boolean dentroTabuleiro(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
    /**
     * Adiciona uma rainha para nas coordenadas x e y dadas: 
     * Método que adiciona rainha para as coordenadas que foram digitadas, neste caso (x,y). O método irá:
     * 1 - Atualizar o atributo quantidadeRainhas deste objeto, que havia começado com zero (0) ao iniciar o tabuleiro e a cada rainha adicionada incrementa este valor em uma unidade. 
     * 2 - Setar o valor dois (2) na casinha do tabuleiro, isto é casasTabuleiro[x][y] = 2;, considerando que os testes já foram feitos anteriormente.
     * 3 - Chamar o método interno administraTabuleiro(x,y,true); que marcará a linha, coluna e diagonais, referentes a esta rainha, adicionada com valor um (1). 
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     */
    public void adicionaRainha(int x, int y) {
        quantidadeRainhas++;
        casasTabuleiro[x][y] = 2; //rainha adicionada
        administraTabuleiro(x,y,true);
        System.out.println("---adicionado----"); // apenas para log       
        imprimeTabuleiro();
    }
    /**
     * Remove uma rainha que esteja nas coordenadas x e y dadas.
     * 	Método que remove rainha para as coordenadas que foram digitadas, neste caso (x,y). O método irá:
     * 1 - Setar o valor zero (0) na casinha (x,y) do tabuleiro, isto é casasTabuleiro[x][y] = 0;, considerando que os testes já foram feitos anteriormente.
     * 2 - Chamar o método interno administraTabuleiro(x,y,false); que marcará a linha, coluna e diagonais, referentes a esta rainha com valor um (0).
     * 3 - Atualizar o atributo quantidadeRainhas deste objeto para o valor zero (0).
     * 4 - Percorrer o tabuleiro procurando rainhas e chamando o método adicionaRainha(i,j) para cada i e j da matriz onde o valor for dois (2).
     * @param x parâmetro do tipo inteiro
     * @param y parâmetro do tipo inteiro
     */
    public void removeRainha(int x, int y) {
        casasTabuleiro[x][y] = 0;
        administraTabuleiro(x,y,false);
        quantidadeRainhas = 0;
        //redesenha o tabuleiro após remover alguma rainha
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (casasTabuleiro[i][j] == 2) {
                    adicionaRainha(i,j); 
                }
            }
        }
        // apenas para log
        System.out.println("----removido---");
        imprimeTabuleiro();
    }  
    
    /**
     * Método que será utilizado pelos métodos adicionaRainha e removeRainha quando chamado marcará linha, coluna e diagonais, com valor 1 ou zero 0 tendo como referência uma rainha (x,y);
     * @param x parâmetro do tipo inteiro 
     * @param y parâmetro do tipo inteiro
     * @param adicionaRemove boolean true adiciona rainha e false para remover - Marca 0 ou 1 na coluna e linha e diagonais conforme seja adicionaRemove
     */
    public void administraTabuleiro(int x, int y, boolean adicionaRemove){
        int i, valor;
        if (adicionaRemove){
            valor = 1;
        } else {
            valor = 0;
        }
        for (i = 0; i < 8; i++) {
            if (i != y && casasTabuleiro[x][i] != 2) {//vai marcar a linha com 1's
                casasTabuleiro[x][i] = valor;
            }
            if (i != x && casasTabuleiro[i][y] != 2) {//vai marcar a coluna
                casasTabuleiro[i][y] = valor;
            }
        }
        for (i=1; dentroTabuleiro(x-i,y+i); i++) {
                casasTabuleiro[x-i][y+i] = valor;
        }
        for (i=1; dentroTabuleiro(x-i,y-i); i++) {
            casasTabuleiro[x-i][y-i] = valor;
        }
        for (i=1; dentroTabuleiro(x+i,y+i); i++) {
            casasTabuleiro[x+i][y+i] = valor;
        }  
        for (i=1; dentroTabuleiro(x+i,y-i); i++) {
            casasTabuleiro[x+i][y-i] = valor;
        }
    }
    /**
    * Método rainhaAtaca - Quando um jogador colocar uma nova rainha em uma casa que está em posição de ataque, isto é a posição em questão tem valor 1.
    * Este método irá buscar a posição da rainha mais próxima que fará o ataque. O método utilizará a ideia de varrer o tabuleiro subtraindo uma unidade em x e acrescentando uma unidade em y.
    * O método atribui a rainha encontrada uma posição (z,w) com base nesta posição conseguiremos informar ao jogador de quem veio o ataque.
    * Em outras palavras: este método é responsável por mostrar qual rainha mais próxima irá atacar a rainha que o jogador está tentando adicionar e cuja posição não é permitida. 
    * O método irá varrer em adjacências de distâncias um  (1) , distâncias dois  (2)  e sucessivamente até que se encontre uma casinha que tenha valor igual a dois  (2).
    * Quando for encontrado uma rainha o método retornará um objeto do tipo Mensagem que conterá um código de erro e as coordenadas  (z,w)  da rainha que fez o ataque.
    * @param x posição x da rainha que está sendo colocada
    * @param y posição y da rainha que está sendo colocada
    * @return inteiro
    */  
    public Mensagem rainhaAtaca(int x, int y){
        int limitesupi = x; 
        int limitesupj = y; 
        int i = x; 
        int j = y; 
        if (dentroTabuleiro(x,y) && casasTabuleiro[x][y] == 2)
        {
            return new Mensagem(1, x, y);
        }
        for (int d = 1; d < 8; d++) {//distancia
            i--;
            j--;
            limitesupi++;
            limitesupj++;
            //agora será feito o acréscimo e decrescimo, ficando assim
            //(2,0)   (2,1)   (2,2)
            //(3,0)   (3,1)   (3,2)
            //(4,0)   (4,1)   (4,2)
            //           z=2; z<=4; 
            for (int z = i; z <= limitesupi; z++) {
                for (int w = j; w <= limitesupj; w++) {
                    if (dentroTabuleiro(z,w) && casasTabuleiro[z][w] == 2 && constataAtaque(x,y,z,w)){//se z,w estiver no tabuleiro e a casinha tiver rainha
                        return new Mensagem(2, z, w);
                    }
                }
            }
        }
        return new Mensagem(2, x, y);//caso nao ache rainha alguma atacando ate 8 casinhas  de distancia
    }
    /**
     * Método constataAtaque constatará se ocorreu ataque analisando linha, coluna e diagonal.
     * Em outras palavras: para constatar se ocorreu ataque na linha ou coluna o método analisará se para as coordenadas existentes (x) e (y) são iguais as coordenadas digitadas/recebidas (a) e (b).
     * Para constatar ataque na diagonal crescente ou diagonal decrescente 
     * Se as coordenadas (a,b) estão nos quadrantes 1 ou 3 neste caso tangente de $45 é igual a 1 ou se as coordenadas (a,b) então ataque na diagonal crescente. 
     * Se estão no quadrante 3 ou 4 neste caso tangente de 45 é igual a -1 encontramos atque na diagonal oposta.
     * @param x parâmetro que indica a posição de quem está sendo colocado
     * @param y parâmetro que indica a posição de quem está sendo colocado
     * @param a parâmetro que indica a posição de quem irá atacar
     * @param b parâmetro que indica a posição de quem irá atacar
     * @return boolean 
     */
    private boolean constataAtaque(int x, int y, int a, int b)
    { 
      if (x == a || y == b )  { //caso que está na mesma linha ou coluna
          return true;
      }
      if ((x-a) == (y - b)){ //mesma diagonal, lembrar tangente do angulo é 1. Quadrantes 1 ou 3
          return true;
      }
      if ((-1)*(x-a) == (y -b)){ //mesma diagonal, lembrar tangente do angulo é -1.  Quadrantes 2 ou 4
          return true;
      return false;//não constatamos ataque para este caso (testar o exemplo (a1, c2, testando colocar a8) se não fosse este método c2 seria mais próxima, mas no caso ela não tem conflito
    }
    /**
     * Método fimDeJogo mostra uma mensagem caso o jogador consiga colocar as oito rainhas
     * retorna true caso o jogador consiga colocar as oito rainhas no tabuleiro.
    * @return boolean
    */    
    public boolean fimDeJogo(){
        return quantidadeRainhas == 8;
    }
    /**
     * Método privado que serve para acompanhar os logs do terminal de cada ação
     */    
    private void imprimeTabuleiro()
    {
        System.out.println("Agora estamos com " + quantidadeRainhas + " rainha(s) no tabuleiro");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(casasTabuleiro[i][j] + " ");
            }
            System.out.println(" ");
        }
    }   
}
