import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Juego de tic toc toe con Java
 * 
 * @author Daniel
 *
 */
public class Start extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JButton newGameButton;
    private JLabel statusLabel;
    private boolean xTurn;
  
    public Start() {
// 		Configuración básica de la ventana
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

// 		Inicialización de los botones y etiquetas
        buttons = new JButton[3][3];
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		newGame();
        	}
        });
        statusLabel = new JLabel("X's turn");

// 		Creación del panel del juego
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.addActionListener(this);
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j] = button;
                gamePanel.add(button);
            }
        }

// 		Creación del panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(newGameButton, BorderLayout.CENTER);

// 		Creación del panel de estado
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        statusPanel.add(statusLabel, BorderLayout.CENTER);

// 		Creación del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(statusPanel, BorderLayout.NORTH);

// 		Agregar el panel principal a la ventana
        getContentPane().add(mainPanel);

// 		Mostrar la ventana
        setVisible(true);

// 		Inicialización del juego
        newGame();
    }

// 	Método para comenzar un nuevo juego
    private void newGame() {
// 		Limpiar los botones y reiniciar el estado del juego
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        xTurn = true;
        statusLabel.setText("X's turn");
    }

// 	Método que se llama cuando se hace clic en un botón
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            if (xTurn) {
                button.setText("X");
                statusLabel.setText("O's turn");
            } else {
                button.setText("O");
                statusLabel.setText("X's turn");
            }
            xTurn = !xTurn;
            button.setEnabled(false);
            checkForWin();
        }
    }  

// 	Método para verificar si alguien ha ganado
    private void checkForWin() {
// 		Verificar filas
    	for(int fila =0; fila<3; fila++)
            if (!buttons[fila][0].getText().equals("") &&
                buttons[fila][1].getText().equals(buttons[fila][0].getText()) &&
                buttons[fila][2].getText().equals(buttons[fila][0].getText())) {
            	statusLabel.setText("Wins player " + buttons[fila][0].getText());
            	for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setEnabled(false);
                    }
                }
                return;                 
            }
// 		Verificacion Columnas
    	for(int columna =0; columna<3; columna++)
            if (!buttons[0][columna].getText().equals("") &&
                buttons[1][columna].getText().equals(buttons[0][columna].getText()) &&
                buttons[2][columna].getText().equals(buttons[0][columna].getText())) {
            	statusLabel.setText("Wins player " + buttons[0][columna].getText());
            	for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setEnabled(false);
                    }
                }
            	return;               
            }
// 		Revisa la diagonal principal
        if (!buttons[0][0].getText().equals("") &&
            buttons[1][1].getText().equals(buttons[0][0].getText()) &&
            buttons[2][2].getText().equals(buttons[0][0].getText())) {
        	statusLabel.setText("Wins player " + buttons[0][0].getText());
        	for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
        	return;
        }
// 		Revisa la diagonal secundaria
        if (!buttons[0][2].getText().equals("") &&
            buttons[1][1].getText().equals(buttons[0][2].getText()) &&
            buttons[2][0].getText().equals(buttons[0][2].getText())) {
        	statusLabel.setText("Wins player " + buttons[0][2].getText());
        	for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
        	return;
        }
    }
    
public static void main(String[] args) {
	new Start();
}
}
