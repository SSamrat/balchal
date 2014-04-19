package boardUI;

import java.awt.Color;
import java.awt.*;

import javax.swing.*;

import board.operation.BackgroundBoard;
import player.ActivePlayer;

@SuppressWarnings("serial")
public class Board3D extends JFrame {
	/**
	 * Variable Declaration #*******D*******#
	 **/
	private JLabel jLabelStage, jLabelPlayGround, jLabelNotification;
	private JLabel jLabelResult, jLabelMessage;

	// play ground buttons
	private ShapedButton jButton[][][];
	// icon
	private ImageIcon icon[];
	// progress bar
	private JProgressBar jProgressBar;
	// other variables
	ActivePlayer activePlayer, player1, player2;
	BackgroundBoard backgroundBoard;

	boolean moveGiven;

	// End of Variable Declaration #_______D_______#

	// ** Constructor **//
	public Board3D(ActivePlayer player1, ActivePlayer player2,
			BackgroundBoard backgroundBoard) {
		this.player1 = player1;
		this.player2 = player2;
		this.backgroundBoard = backgroundBoard;

		moveGiven = false;

		activePlayer = player1;

		InitialComponent();
	}

	// ** Method for Initializing **//
	private void InitialComponent() {
		/**
		 * Initialization #*******I*******#
		 **/
		// main labels
		jLabelStage = new JLabel();
		jLabelPlayGround = new JLabel();
		jLabelNotification = new JLabel();

		// auxiliary labels
		jLabelResult = new JLabel();
		jLabelMessage = new JLabel();

		// PlayGround buttons
		jButton = new ShapedButton[4][4][4];
		// icons
		icon = new ImageIcon[2];
		// progress bar
		jProgressBar = new JProgressBar(0, 70);

		// End of Initialization #_______I_______#

		/**
		 * Setting Bounds and Attributes of the Elements #*******S*******#
		 **/
		// labels
		jLabelStage.setBounds(0, 0, 490, 60);
		jLabelStage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/board/pictures/Notification.jpg")));

		jLabelPlayGround.setBounds(0, 60, 490, 410);
		jLabelPlayGround.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/board/pictures/PlayGround.jpg")));

		jLabelNotification.setBounds(0, 470, 490, 30);
		jLabelNotification.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/board/pictures/Notification.jpg")));
		jLabelNotification.setLayout(null);

		// JProgressBar

		jProgressBar.setBounds(0, 470, 490, 20);
		jProgressBar.setForeground(Color.red);

		// auxiliary
		jLabelResult.setBounds(0, 12, 490, 35);
		jLabelResult.setHorizontalAlignment(0);
		jLabelResult.setFont(new java.awt.Font("Tahoma", 0, 24));
		jLabelResult.setText("Player" + activePlayer.getPlayerNo());

		jLabelMessage.setBounds(0, 3, 490, 25);
		jLabelMessage.setHorizontalAlignment(0);
		jLabelMessage.setFont(new java.awt.Font("Tahoma", 1, 21));

		// play ground buttons
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					final String argument = "" + i + j + k;

					jButton[i][j][k] = new ShapedButton();
					jButton[i][j][k].setSize(50, 20);
					jButton[i][j][k].setLocation(j * 10 + k * 65 + 100, i * 100
							+ j * 17 + 20);
					jButton[i][j][k].setBackground(new java.awt.Color(
							130 + j * 20, 130 + j * 20, 130 + j * 20));
					jButton[i][j][k]
							.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(
										java.awt.event.ActionEvent evt) {
									jButtonActionPerformed(evt, argument);
								}
							});
				}
			}
		}
		// jButton[0][0][0].setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/board/pictures/WhiteBall.jpg")));
		// jButton[1][1][1].setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/board/pictures/BlackBall.jpg")));

		// icon
		icon[0] = new ImageIcon(getClass().getResource(
				"/board/pictures/WhiteBall1.png"));
		icon[1] = new ImageIcon(getClass().getResource(
				"/board/pictures/BlackBall1.png"));
		// End of Setting Bounds and Attributes #_______S_______#

		/**
		 * Adding Components #*******A*******#
		 **/
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					jLabelPlayGround.add(jButton[i][j][k]);
				}
			}
		}

		jLabelStage.add(jLabelResult);
		jLabelNotification.add(jLabelMessage);
		// End of Adding Components #_______A_______#

		// Setting Criterion of the Frame//
		setBounds(200, 80, 490, 540);
		setTitle("3D Tic Tac Toe");
		add(jLabelStage);
		add(jLabelPlayGround);
		// add(jLabelNotification);
		add(jProgressBar);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
	}

	/**
	 * Action Events #********AE*******#
	 **/
	private void jButtonActionPerformed(java.awt.event.ActionEvent evt,
			String stringArgument) {
		int i = (int) stringArgument.charAt(0) - 48, j = (int) stringArgument
				.charAt(1) - 48, k = (int) stringArgument.charAt(2) - 48;

		if (jButton[i][j][k].isRolloverEnabled()) {

			while (i < 3) {
				if (jButton[i + 1][j][k].isRolloverEnabled()) {
					i++;
				} else {
					break;
				}
			}
			// System.out.println(i+" "+j+" "+k);//test

			jButton[i][j][k].setIcon(icon[activePlayer.getPlayerNo() - 1]);
			jButton[i][j][k].setRolloverEnabled(false);

			// operations
			activePlayer.giveNextMove(backgroundBoard, i, j, k);
			// backgroundBoard.printBoard();//test

			moveGiven = true;

			// player change
			if (activePlayer == player1)
				activePlayer = player2;
			else
				activePlayer = player1;

			jLabelResult.setText("Player" + activePlayer.getPlayerNo()
					+ "'s turn");
		}

		// System.out.println(backgroundBoard.getBallInBox(i, j,
		// k).getColor());//test
	}

	// End of Action Events #________AE_______#

	// ** Main Method **//

	/**
	 * Auxiliary Methods #********AM*******#
	 **/
	public void notify(String message, int Outcome) {
		jLabelMessage.setText(message);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					jButton[i][j][k].setRolloverEnabled(false);
				}
			}
		}

		if (Outcome == 0) {
			jLabelResult.setText("Player" + (3 - activePlayer.getPlayerNo())
					+ " Wins!!!");
		} else {
			jLabelResult.setText("It is a draw!");
		}
	}

	public void setJLabelMessage(String message) {
		jLabelMessage.setText(message);
	}

	public void setProgress(int i) {
		jProgressBar.setValue(i);
	}

	public void changePlayer() {
		if (activePlayer == player1)
			activePlayer = player2;
		else
			activePlayer = player1;

		jLabelResult.setText("Player" + activePlayer.getPlayerNo() + "'s turn");
	}

	public void setMoveGiven(boolean moveGiven) {
		this.moveGiven = moveGiven;
	}

	public boolean getMoveGiven() {
		return moveGiven;
	}

	// End of Auxiliary Methods #________AM_______#
}
