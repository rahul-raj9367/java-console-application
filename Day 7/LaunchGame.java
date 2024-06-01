import java.util.*;
class TicTacToe{
    //creating board
    static char [][]board;

    public TicTacToe(){
        board=new char[3][3];
        initboard();
    }

    void initboard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j]=' ';
            }
        }
    }

    static void dispboard(){
        System.out.println("-------------");
        for(int i=0;i<board.length;i++){
            System.out.print("| ");
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static  void placemark(int row,int col,char mark){
        if( row>=0 && row<=2 && col>=0 && col<=2){
            board[row][col]=mark;
        }else{
            System.out.println("Invalid Position");
        }
    }

    static boolean checkcolwin(){
        for(int j=0;j<=2;j++){
            if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]){
                return true;
            }
        }
        return false;
    }

    static boolean checkrowwin(){
        for(int i=0;i<=2;i++){
            if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]){
                return true;
            }
        }
        return false;
    }

    static boolean checkDigwin(){
        if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] ||
        board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]){
            return true;
        }else{
            return false;
        }
    }
}


class HumanPlayer{

    String name;
    char mark;
    public HumanPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }

    void makemove(){
       Scanner scan= new Scanner(System.in);
       int row;
       int col;
       do{
           System.out.println("Enter row and col");
            row=scan.nextInt();
            col=scan.nextInt();
       }while(!isValidMove(row,col));

       TicTacToe.placemark(row,col,mark);

    }

    boolean isValidMove(int row,int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTacToe.board[row][col]==' '){
                return true;
            }
        }
        return false;
    }
}




class LaunchGame{
    public static void main(String[] args) {
        TicTacToe t= new TicTacToe();
        // t.dispboard();
        // // t.placemark(0,0,'X');
        // t.placemark(0,0,'O');
        // // t.placemark(2,0,'X');
        // t.placemark(1,1,'O');
        // t.placemark(2,2,'O');
        // t.placemark(2,1,'O');


        
        // t.dispboard();
        // System.out.println(t.checkcolwin());
        // System.out.println(t.checkrowwin());
        // System.out.println(t.checkDigwin());

        HumanPlayer p1=new HumanPlayer("rahul",'X');
        HumanPlayer p2=new HumanPlayer("raj",'O');

        HumanPlayer cp;
        cp=p1;

        while(true){
            System.out.println(cp.name+"turn");
            cp.makemove();
            TicTacToe.dispboard();


            if(TicTacToe.checkcolwin() || TicTacToe.checkrowwin() || TicTacToe.checkDigwin()){
                System.out.println(cp.name+" was win");
                break;
            }else{
                if(cp==p1){
                    cp=p2;
                }else{
                    cp=p1;
                }
            }
        }
    }
}