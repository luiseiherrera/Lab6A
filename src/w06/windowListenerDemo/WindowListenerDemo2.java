package w06.windowListenerDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListenerDemo2 extends JFrame {
    public static final int WIDTH = 300; //for main window
    public static final int HEIGHT = 200; //for main window
    public static final int SMALL_WIDTH = 200; //for confirm window
    public static final int SMALL_HEIGHT = 100;//for confirm window

    private class CheckOnExit extends WindowAdapter { //EXTENDING WINDOWADAPTER CLASS RATHER THAN IMPLEMENTING WINDOWLISTENER INTERFACE
        //WindowAdapter is an abstract class (cannot instantiate objects) with trivially implemented concrete methods
        //which are inherited and can overriden as required, just the one you need
        //much shorter and cleaner - the trivial implementations in WindowAdapter class are inherited - 1 is overriden here
	    @Override
        public void windowClosing(WindowEvent e){
	        ConfirmWindow checkers = new ConfirmWindow( );
	        checkers.setVisible(true);
	    }
    } //End of inner class CheckOnExit

    private class ConfirmWindow extends JFrame implements ActionListener {
        public ConfirmWindow( ){
            setSize(SMALL_WIDTH, SMALL_HEIGHT);
            getContentPane( ).setBackground(Color.YELLOW);
            setLayout(new BorderLayout( ));

            JLabel confirmLabel = new JLabel(
                           "Are you sure you want to exit?");
            add(confirmLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel( );
            buttonPanel.setBackground(Color.ORANGE);
            buttonPanel.setLayout(new FlowLayout( ));

            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);

            JButton cancelButton = new JButton("No");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand( );

            if (actionCommand.equals("Yes"))
                System.exit(0);
            else if (actionCommand.equals("No"))
                dispose( );//Destroys only the ConfirmWindow.
            else
                System.out.println("Unexpected Error in Confirm Window.");
        }
    } //End of inner class ConfirmWindow

    public static void main(String[] args){
        WindowListenerDemo demoWindow = new WindowListenerDemo( );
        demoWindow.setVisible(true);
    }

    public WindowListenerDemo2( ){
        setSize(WIDTH, HEIGHT);
        setTitle("Window Listener Demonstration");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CheckOnExit( ));

        getContentPane( ).setBackground(Color.LIGHT_GRAY);
        JLabel aLabel = new JLabel("I like to be sure you are sincere.");
        add(aLabel);
    }
}
