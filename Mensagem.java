package projetooitorainhas;
/**
 * Classe Mensagem é responsável por armazenar códigos de erros relacionando determinadas coordenadas
 * @author Ester Campos e Amanda Fogaça
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Mensagem {
    private final int erro;
    private final int coordx;
    private final int coordy;
    /**
     * Contrutor da classe Mensagem que irá receber um código de erro e atribuir ao atributo e, receber um valor x e atribuir a coordx e receber um valor y atribuindo a coordy.
     * @param e referente ao código do erro
     * @param x coordenadas x
     * @param y coordenadas y
    */     
    public Mensagem(int e, int x, int y){
        this.coordx = x;
        this.coordy = y;
        this.erro = e;  
    }
    /**
     * Método privado responsável por devolver um número inteiro que será referente ao código de erro de uma mensagem.
     * @return inteiro
    */
    private int getErro(){
        return this.erro;
    }
    /**
     * Método privado responsável por devolver um número inteiro que é referente a coordenada x do elemento que está na mensagem.
     * @return inteiro
    */
    private int getX(){
        return this.coordx;
    }
    /**
     * Método privado responsável por devolver um número inteiro que é referente a coordenada y do elemento que está na mensagem.
     * @return inteiro
    */
    private int getY(){
        return this.coordy;
    }
    /**
     * Método público responsável por devolver o texto relacionado ao código de erro.
     * @return string
    */
    public String getText() {
        int e, x, y;
        e = getErro();
        x = getX();
        y = getY();
        String colunas[] = {"-", "a","b","c","d","e","f","g","h"};
        switch( e ) {
            case 0: 
              return "ok";
            case 1:
                return "Já existe uma Rainha na coluna '" + colunas[x+1] + "' e na linha '" +  (8-y) +"'!";                  
            case 2:
                return "Existe conflito! Sofre ataque da Rainha da coluna '" + colunas[x+1] + "' e da linha '" +  (8-y) +"'";                  
            case 3: 
                return "Existe conflito na linha '" + colunas[x+1]  + "' e coluna '" + (8-y) + "' mas não achamos com qual rainha ";                  
            case 5: 
                return "Não existe rainha na coluna '" + colunas[x+1] + "' e linha '" + (8-y) + "'";                  
            case 99: 
                return "Valores inválidos para coluna ou linha!"; 
            case 100: 
               return "Parabéns Você conseguiu colocar as 8 rainhas!!"; 
            default:
               return "Valores inválidos!";
        }         
    }   
}
