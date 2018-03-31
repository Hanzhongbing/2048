import javax.swing.*;

public class App extends JFrame{
    public App() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("2048");
        Panel2048  panel2048 =new Panel2048(this);
        this.add(panel2048);


    }
    public static void main(String[] args){
       App app= new App();
       app.setVisible(true);
       app.setResizable(false);
    }

}
