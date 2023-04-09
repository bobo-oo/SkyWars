import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GridGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private ArrayList<Row> rows;
    private final int GRID_SIZE = 4;

    public GridGUI() {
        frame = new JFrame("Grid GUI");
        mainPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        rows = new ArrayList<Row>();

        // Create rows and squares
        for (int i = 0; i < GRID_SIZE; i++) {
            Row row = new Row(i);
            rows.add(row);
            for (int j = 0; j < GRID_SIZE; j++) {
                Square square = new Square(j);
                row.getTheSquares().add(square);
                JLabel label = new JLabel(" ");
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                label.setBorder(border);
                square.setLabel(label);
                mainPanel.add(label);
            }
        }

        // Set spaceships
        Spaceship spaceship1 = new Spaceship(0, 0, 0);
        Spaceship spaceship2 = new Spaceship(1, 1, 1);
        rows.get(0).getTheSquares().get(0).addSpaceship(spaceship1);
        rows.get(1).getTheSquares().get(1).addSpaceship(spaceship2);

        // Update GUI
        updateGUI();

        // Show GUI
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateGUI() {
        for (Row row : rows) {
            for (Square square : row.getTheSquares()) {
                if (square.isThereASpaceship()) {
                    square.getLabel().setText("S");
                } else {
                    square.getLabel().setText(" ");
                }
            }
        }
    }

    public static void main(String[] args) {
        new GridGUI();
    }
}
