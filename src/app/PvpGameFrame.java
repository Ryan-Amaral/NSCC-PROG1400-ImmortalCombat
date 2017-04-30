package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import scenes.FightManager;
import scenes.PlayerCreator;
import scenes.ShopManager;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;

import people.Fighter;
import people.Opponent;
import people.Player;
import people.Races;

import java.awt.SystemColor;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import equipment.EquipmentList;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PvpGameFrame extends JFrame {

	private JPanel contentPane;
	
	//the player
	private Player player;
	
	// declare scripts to help with certain scenes to keep this script more clean
	private PlayerCreator playerCreator = new PlayerCreator();
	private ShopManager shopManager = new ShopManager();
	protected FightManager fightManager = new FightManager();
	
	private JPanel panelSplashScreen;
	private JPanel panelPlayerCreation;
	private JTextField txtName;
	private final ButtonGroup raceSelectButtonGroup = new ButtonGroup();
	private JButton btnCreatePlayer;

	protected boolean rollStatsClicked = false; // determines if the roll stats button was clicked
	protected boolean nameCheckPassed = false; // determines if the check name button was clicked and a valid name is entered
	
	private JLabel lblChosenNameValue;
	private JLabel lblHealthValue;
	private JLabel lblAttackValue;
	private JLabel lblDefenseValue;
	private JButton btnCheckName;
	private JRadioButton rdbtnHuman;
	private JRadioButton rdbtnGoblin;
	private JRadioButton rdbtnElf;
	private JRadioButton rdbtnDwarf;
	private JRadioButton rdbtnAngel;
	private JRadioButton rdbtnDemon;
	private JLabel lblPlayerRace;
	private JPanel panelMainMenu;
	private JLabel lblArmourName;
	private JLabel lblPlayersName;
	private JLabel lblHealthLabel;
	private JLabel lblAttackLabel;
	private JLabel lblDefenseLabel;
	private JLabel lblCriticalChanceLabel;
	private JLabel lblHealthStat;
	private JLabel lblAttackStat;
	private JLabel lblDefenseStat;
	private JLabel lblHealthBoost;
	private JLabel lblAttackBoost;
	private JLabel lblDefenseBoost;
	private JLabel lblCriticalChanceBoost;
	private JComboBox cbxWeapons;
	private JComboBox cbxArmours;
	private JLabel lblSelectWeapon;
	private JLabel lblSelectArmour;
	private JLabel lblStatExplain;
	private JButton btnEasyFight;
	private JButton btnMediumFight;
	private JButton btnHardFight;
	private JButton btnToShop;
	private JLabel lblCoins;
	private JLabel lblWeaponName;
	private JLabel lblPlayersIcon;
	private JLabel lblCurWeaponIcon;
	private JLabel lblCurArmourIcon;
	private JPanel panelShop;
	private JComboBox cbxBrowseWeapons;
	private JLabel lblBrowseWeapons;
	private JLabel lblBrowseArmour;
	private JComboBox cbxBrowseArmours;
	private JButton btnBuyWeapon;
	private JLabel lblWeaponPrice;
	private JLabel lblArmourPrice;
	private JButton btnBuyArmour;
	private JLabel lblYourCoins;
	private JLabel lblEquipmentImage;
	private JLabel lblItemHealthBoostLabel;
	private JLabel lblItemAttackBoostLabel;
	private JLabel lblItemDefenseBoostLabel;
	private JLabel lblItemCriticalChanceBoostLabel;
	private JLabel lblItemHealthBoostValue;
	private JLabel lblItemAttackBoostValue;
	private JLabel lblItemDefenseBoostValue;
	private JLabel lblItemCriticalChanceBoostValue;
	private JLabel lblItemName;
	private JLabel lblItemType;
	private JButton btnGoToMenu;

	private boolean firstTimeShop; // if the shop was entered first time
	protected JPanel panelFight;
	private JPanel panelTextValid;
	private JLabel lblFPlayerArmour;
	private JLabel lblFOpponentWeapon;
	private JLabel lblFOpponentArmour;
	private JPanel panelFOpponentBack;
	private JPanel panelFPlayerBack2;
	private JPanel panelFOpponentBack2;
	private JLabel lblFPlayersName;
	private JLabel lblFOpponentsName;
	private JLabel lblFOpponentHealth;
	private JLabel lblFPlayerHealth;
	private JButton btnFStartFightButton;
	private JButton btnFGoToMenu;
	private JTextArea txtrFightTextArea;
	private JScrollPane scrlpnFightText;
	
	private int ticks;
	private JLabel lblWinLose;
	private JLabel lblFPlayersIcon;
	private JLabel lblFPlayerWeapon;
	private JLabel lblFOpponentIcon;

	public JLabel getLblFOpponentHealth() {
		return lblFOpponentHealth;
	}

	public void setLblFOpponentHealth(JLabel lblFOpponentHealth) {
		this.lblFOpponentHealth = lblFOpponentHealth;
	}

	public JLabel getLblFPlayerHealth() {
		return lblFPlayerHealth;
	}

	public void setLblFPlayerHealth(JLabel lblFPlayerHealth) {
		this.lblFPlayerHealth = lblFPlayerHealth;
	}

	public JScrollPane getScrlpnFightText() {
		return scrlpnFightText;
	}

	public void setScrlpnFightText(JScrollPane scrlpnFightText) {
		this.scrlpnFightText = scrlpnFightText;
	}

	public JTextArea getTxtrFightTextArea() {
		return txtrFightTextArea;
	}

	public void setTxtrFightTextArea(JTextArea txtrFightTextArea) {
		this.txtrFightTextArea = txtrFightTextArea;
	}

	public JButton getBtnFGoToMenu() {
		return btnFGoToMenu;
	}

	public void setBtnFGoToMenu(JButton btnFGoToMenu) {
		this.btnFGoToMenu = btnFGoToMenu;
		btnFGoToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// go back to menu
				panelMainMenu.setVisible(true);
				panelFight.setVisible(false);
				//update main menu
				updateMainMenu();
			}
		});
	}

	public JButton getBtnFStartFightButton() {
		return btnFStartFightButton;
	}

	public void setBtnFStartFightButton(JButton btnFStartFightButton) {
		this.btnFStartFightButton = btnFStartFightButton;
	}

	public JLabel getLblFPlayersName() {
		return lblFPlayersName;
	}

	public void setLblFPlayersName(JLabel lblFPlayersName) {
		this.lblFPlayersName = lblFPlayersName;
	}

	public JLabel getLblFOpponentsName() {
		return lblFOpponentsName;
	}

	public void setLblFOpponentsName(JLabel lblFOpponentsName) {
		this.lblFOpponentsName = lblFOpponentsName;
	}

	public JLabel getLblFPlayerArmour() {
		return lblFPlayerArmour;
	}

	public void setLblFPlayerArmour(JLabel lblFPlayerArmour) {
		this.lblFPlayerArmour = lblFPlayerArmour;
	}

	public JLabel getLblFPlayerWeapon() {
		return lblFPlayerWeapon;
	}

	public void setLblFPlayerWeapon(JLabel lblFPlayerWeapon) {
		this.lblFPlayerWeapon = lblFPlayerWeapon;
	}

	public JLabel getLblFOpponentArmour() {
		return lblFOpponentArmour;
	}

	public void setLblFOpponentArmour(JLabel lblFOpponentArmour) {
		this.lblFOpponentArmour = lblFOpponentArmour;
	}

	public JLabel getLblFOpponentWeapon() {
		return lblFOpponentWeapon;
	}

	public void setLblFOpponentWeapon(JLabel lblFOpponentWeapon) {
		this.lblFOpponentWeapon = lblFOpponentWeapon;
	}

	public JLabel getLblFPlayersIcon() {
		return lblFPlayersIcon;
	}

	public void setLblFPlayersIcon(JLabel lblFPlayersIcon) {
		this.lblFPlayersIcon = lblFPlayersIcon;
	}

	public JLabel getLblFOpponentIcon() {
		return lblFOpponentIcon;
	}

	public void setLblFOpponentIcon(JLabel lblFOpponentIcon) {
		this.lblFOpponentIcon = lblFOpponentIcon;
	}

	/**
	 * Create the frame.
	 */
	public PvpGameFrame() {
		setTitle("Immortal Combat - Ryan Amaral");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		///////////////////
		// SPLASH SCREEN //
		///////////////////
		panelSplashScreen = new JPanel();
		contentPane.add(panelSplashScreen, "name_100513454296753");
		panelSplashScreen.setLayout(null);
		
		JButton btnStartGame = new JButton("Start Game!");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// button is clicked, go to player creation
				panelSplashScreen.setVisible(false);
				panelPlayerCreation.setVisible(true);
			}
		});
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStartGame.setBounds(219, 295, 135, 45);
		panelSplashScreen.add(btnStartGame);
		
		JLabel lblSplashScreenImage = new JLabel("");
		lblSplashScreenImage.setIcon(new ImageIcon(PvpGameFrame.class.getResource("/images/splashscreen.jpg")));
		lblSplashScreenImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblSplashScreenImage.setBounds(87, 75, 400, 200);
		panelSplashScreen.add(lblSplashScreenImage);
		
		JLabel lblImmortalCombat = new JLabel("Immortal Combat!");
		lblImmortalCombat.setForeground(Color.RED);
		lblImmortalCombat.setHorizontalAlignment(SwingConstants.CENTER);
		lblImmortalCombat.setHorizontalTextPosition(SwingConstants.LEADING);
		lblImmortalCombat.setFont(new Font("Tw Cen MT", Font.PLAIN, 58));
		lblImmortalCombat.setBounds(68, 11, 438, 52);
		panelSplashScreen.add(lblImmortalCombat);
		
		/////////////////////
		// Player Creation //
		/////////////////////
		panelPlayerCreation = new JPanel();
		contentPane.add(panelPlayerCreation, "name_423601356830821");
		panelPlayerCreation.setLayout(null);
		
		lblPlayerRace = new JLabel("");
		lblPlayerRace.setIcon(new ImageIcon(PvpGameFrame.class.getResource("/images/races/human.jpg")));
		lblPlayerRace.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblPlayerRace.setBounds(375, 25, 150, 150);
		panelPlayerCreation.add(lblPlayerRace);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(46, 50, 51, 14);
		panelPlayerCreation.add(lblName);
		
		rdbtnHuman = new JRadioButton("Human");
		rdbtnHuman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// human button is selected, set race and update icon
				if(playerCreator.getPlayerRace() != Races.HUMAN){
					playerCreator.setPlayerRace(Races.HUMAN);
					playerCreator.setPlayerIcon(Races.getRaceIcon(Races.HUMAN));
					lblPlayerRace.setIcon(playerCreator.getPlayerIcon());
				}
			}
		});
		raceSelectButtonGroup.add(rdbtnHuman);
		rdbtnHuman.setSelected(true);
		rdbtnHuman.setFont(new Font("Dialog", Font.PLAIN, 18));
		rdbtnHuman.setBounds(60, 108, 109, 23);
		panelPlayerCreation.add(rdbtnHuman);
		
		rdbtnGoblin = new JRadioButton("Goblin");
		rdbtnGoblin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// goblin button is selected, set race and update icon
				if(playerCreator.getPlayerRace() != Races.GOBLIN){
					playerCreator.setPlayerRace(Races.GOBLIN);
					playerCreator.setPlayerIcon(Races.getRaceIcon(Races.GOBLIN));
					lblPlayerRace.setIcon(playerCreator.getPlayerIcon());
				}
			}
		});
		raceSelectButtonGroup.add(rdbtnGoblin);
		rdbtnGoblin.setFont(new Font("Dialog", Font.PLAIN, 18));
		rdbtnGoblin.setBounds(60, 138, 109, 23);
		panelPlayerCreation.add(rdbtnGoblin);
		
		rdbtnElf = new JRadioButton("Elf");
		rdbtnElf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// elf button is selected, set race and update icon
				if(playerCreator.getPlayerRace() != Races.ELF){
					playerCreator.setPlayerRace(Races.ELF);
					playerCreator.setPlayerIcon(Races.getRaceIcon(Races.ELF));
					lblPlayerRace.setIcon(playerCreator.getPlayerIcon());
				}
			}
		});
		raceSelectButtonGroup.add(rdbtnElf);
		rdbtnElf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnElf.setBounds(60, 168, 109, 23);
		panelPlayerCreation.add(rdbtnElf);
		
		rdbtnDwarf = new JRadioButton("Dwarf");
		rdbtnDwarf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dwarf button is selected, set race and update icon
				if(playerCreator.getPlayerRace() != Races.DWARF){
					playerCreator.setPlayerRace(Races.DWARF);
					playerCreator.setPlayerIcon(Races.getRaceIcon(Races.DWARF));
					lblPlayerRace.setIcon(playerCreator.getPlayerIcon());
				}
			}
		});
		raceSelectButtonGroup.add(rdbtnDwarf);
		rdbtnDwarf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnDwarf.setBounds(171, 108, 109, 23);
		panelPlayerCreation.add(rdbtnDwarf);
		
		rdbtnAngel = new JRadioButton("Angel");
		rdbtnAngel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// angel button is selected, set race and update icon
				if(playerCreator.getPlayerRace() != Races.ANGEL){
					playerCreator.setPlayerRace(Races.ANGEL);
					playerCreator.setPlayerIcon(Races.getRaceIcon(Races.ANGEL));
					lblPlayerRace.setIcon(playerCreator.getPlayerIcon());
				}
			}
		});
		raceSelectButtonGroup.add(rdbtnAngel);
		rdbtnAngel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnAngel.setBounds(171, 138, 109, 23);
		panelPlayerCreation.add(rdbtnAngel);
		
		rdbtnDemon = new JRadioButton("Demon");
		rdbtnDemon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// demon button is selected, set race and update icon
				if(playerCreator.getPlayerRace() != Races.DEMON){
					playerCreator.setPlayerRace(Races.DEMON);
					playerCreator.setPlayerIcon(Races.getRaceIcon(Races.DEMON));
					lblPlayerRace.setIcon(playerCreator.getPlayerIcon());
				}
			}
		});
		raceSelectButtonGroup.add(rdbtnDemon);
		rdbtnDemon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnDemon.setBounds(171, 168, 109, 23);
		panelPlayerCreation.add(rdbtnDemon);
		
		JButton btnRollStats = new JButton("Roll Stats");
		btnRollStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Roll Stats button was clicked
				// roll stats and show button was clicked
				if(!rollStatsClicked){
					rollStatsClicked = true;
				}
				playerCreator.rollStats();
				//check if name is already selected too
				if(rollStatsClicked && nameCheckPassed && !btnCreatePlayer.isEnabled()){
					btnCreatePlayer.setEnabled(true);
				}
				// display stats
				lblHealthValue.setText(Integer.toString(playerCreator.getHpStat()));
				lblAttackValue.setText(Integer.toString(playerCreator.getAttStat()));
				lblDefenseValue.setText(Integer.toString(playerCreator.getDefStat()));
			}
		});
		btnRollStats.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRollStats.setBounds(106, 230, 122, 37);
		panelPlayerCreation.add(btnRollStats);
		
		JLabel lblNameRules = new JLabel("Less than 13 characters, letters, dashes, and apostrophes only.");
		lblNameRules.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNameRules.setBounds(50, 75, 279, 14);
		panelPlayerCreation.add(lblNameRules);
		
		btnCheckName = new JButton("Check");
		btnCheckName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// name check button was clicked
				boolean isCurNameValid = playerCreator.isValidName(txtName.getText());
				if(isCurNameValid){
					nameCheckPassed = true;
					// if name is good and stats are rolled, the player can be created
					if(rollStatsClicked && nameCheckPassed && !btnCreatePlayer.isEnabled()){
						btnCreatePlayer.setEnabled(true);
					}
					//change color of text background
					if(panelTextValid.getBackground() != Color.LIGHT_GRAY){
						panelTextValid.setBackground(Color.LIGHT_GRAY);
					}
					playerCreator.setPlayerName(txtName.getText()); // set the players name
 					lblChosenNameValue.setText(playerCreator.getPlayerName()); // display the players name
				}else{
					//change color of text background
					if(panelTextValid.getBackground() != Color.RED){
						panelTextValid.setBackground(Color.RED);
					}
				}
			}
		});
		btnCheckName.setBounds(287, 49, 74, 23);
		panelPlayerCreation.add(btnCheckName);
		
		btnCreatePlayer = new JButton("Create Player");
		btnCreatePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create the player
				player = new Player(playerCreator.getPlayerName(), playerCreator.getPlayerIcon(), playerCreator.getHpStat(),
						playerCreator.getAttStat(), playerCreator.getDefStat());
				// switch to main menu panel
				panelPlayerCreation.setVisible(false);
				panelMainMenu.setVisible(true);
				setupMainMenu(); // initialize main menu
			}
		});
		btnCreatePlayer.setEnabled(false);
		btnCreatePlayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreatePlayer.setBounds(82, 291, 170, 37);
		panelPlayerCreation.add(btnCreatePlayer);
		
		JLabel lblChosenName = new JLabel("Name:");
		lblChosenName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChosenName.setBounds(367, 186, 61, 14);
		panelPlayerCreation.add(lblChosenName);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHealth.setBounds(369, 217, 46, 14);
		panelPlayerCreation.add(lblHealth);
		
		JLabel lblAttack = new JLabel("Attack:");
		lblAttack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAttack.setBounds(369, 240, 46, 14);
		panelPlayerCreation.add(lblAttack);
		
		JLabel lblDefense = new JLabel("Defense:");
		lblDefense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDefense.setBounds(358, 265, 61, 14);
		panelPlayerCreation.add(lblDefense);
		
		lblChosenNameValue = new JLabel("-");
		lblChosenNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChosenNameValue.setForeground(new Color(0, 128, 0));
		lblChosenNameValue.setBounds(422, 186, 142, 20);
		panelPlayerCreation.add(lblChosenNameValue);
		
		lblHealthValue = new JLabel("-");
		lblHealthValue.setForeground(new Color(0, 128, 0));
		lblHealthValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHealthValue.setBounds(425, 217, 46, 14);
		panelPlayerCreation.add(lblHealthValue);
		
		lblAttackValue = new JLabel("-");
		lblAttackValue.setForeground(new Color(0, 128, 0));
		lblAttackValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAttackValue.setBounds(425, 242, 46, 14);
		panelPlayerCreation.add(lblAttackValue);
		
		lblDefenseValue = new JLabel("-");
		lblDefenseValue.setForeground(new Color(0, 128, 0));
		lblDefenseValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDefenseValue.setBounds(422, 265, 46, 14);
		panelPlayerCreation.add(lblDefenseValue);
		
		panelTextValid = new JPanel();
		panelTextValid.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTextValid.setBounds(101, 30, 176, 47);
		panelPlayerCreation.add(panelTextValid);
		panelTextValid.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(6, 16, 164, 28);
		panelTextValid.add(txtName);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtName.setColumns(10);
		
		///////////////
		// Main Menu //
		///////////////
		panelMainMenu = new JPanel();
		contentPane.add(panelMainMenu, "name_449309646375733");
		panelMainMenu.setLayout(null);
		
		lblPlayersIcon = new JLabel("");
		lblPlayersIcon.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblPlayersIcon.setBounds(140, 11, 150, 150);
		panelMainMenu.add(lblPlayersIcon);
		
		lblCurWeaponIcon = new JLabel("");
		lblCurWeaponIcon.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblCurWeaponIcon.setBounds(30, 39, 75, 75);
		panelMainMenu.add(lblCurWeaponIcon);
		
		lblCurArmourIcon = new JLabel("");
		lblCurArmourIcon.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblCurArmourIcon.setBounds(325, 39, 75, 75);
		panelMainMenu.add(lblCurArmourIcon);
		
		lblWeaponName = new JLabel("Weapon Name");
		lblWeaponName.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeaponName.setBounds(0, 117, 137, 14);
		panelMainMenu.add(lblWeaponName);
		
		lblArmourName = new JLabel("Armour Name");
		lblArmourName.setHorizontalAlignment(SwingConstants.CENTER);
		lblArmourName.setBounds(292, 117, 140, 14);
		panelMainMenu.add(lblArmourName);
		
		lblPlayersName = new JLabel("Players Name");
		lblPlayersName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayersName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlayersName.setBounds(149, 163, 132, 20);
		panelMainMenu.add(lblPlayersName);
		
		lblHealthLabel = new JLabel("Health:");
		lblHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHealthLabel.setBounds(120, 189, 43, 20);
		panelMainMenu.add(lblHealthLabel);
		
		lblAttackLabel = new JLabel("Attack:");
		lblAttackLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAttackLabel.setBounds(120, 209, 54, 20);
		panelMainMenu.add(lblAttackLabel);
		
		lblDefenseLabel = new JLabel("Defense:");
		lblDefenseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDefenseLabel.setBounds(120, 229, 54, 20);
		panelMainMenu.add(lblDefenseLabel);
		
		lblCriticalChanceLabel = new JLabel("Critical Chance:");
		lblCriticalChanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCriticalChanceLabel.setBounds(120, 249, 94, 20);
		panelMainMenu.add(lblCriticalChanceLabel);
		
		lblHealthStat = new JLabel("100");
		lblHealthStat.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealthStat.setForeground(new Color(0, 128, 0));
		lblHealthStat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHealthStat.setBounds(170, 189, 33, 20);
		panelMainMenu.add(lblHealthStat);
		
		lblAttackStat = new JLabel("100");
		lblAttackStat.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttackStat.setForeground(new Color(0, 128, 0));
		lblAttackStat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttackStat.setBounds(170, 209, 33, 20);
		panelMainMenu.add(lblAttackStat);
		
		lblDefenseStat = new JLabel("100");
		lblDefenseStat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefenseStat.setForeground(new Color(0, 128, 0));
		lblDefenseStat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDefenseStat.setBounds(180, 229, 33, 20);
		panelMainMenu.add(lblDefenseStat);
		
		lblHealthBoost = new JLabel("(+100)");
		lblHealthBoost.setHorizontalAlignment(SwingConstants.LEFT);
		lblHealthBoost.setForeground(Color.BLUE);
		lblHealthBoost.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHealthBoost.setBounds(210, 189, 54, 20);
		panelMainMenu.add(lblHealthBoost);
		
		lblAttackBoost = new JLabel("(+100)");
		lblAttackBoost.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttackBoost.setForeground(Color.BLUE);
		lblAttackBoost.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttackBoost.setBounds(210, 209, 54, 20);
		panelMainMenu.add(lblAttackBoost);
		
		lblDefenseBoost = new JLabel("(+100)");
		lblDefenseBoost.setHorizontalAlignment(SwingConstants.LEFT);
		lblDefenseBoost.setForeground(Color.BLUE);
		lblDefenseBoost.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDefenseBoost.setBounds(220, 229, 54, 20);
		panelMainMenu.add(lblDefenseBoost);
		
		lblCriticalChanceBoost = new JLabel("(+100)");
		lblCriticalChanceBoost.setHorizontalAlignment(SwingConstants.LEFT);
		lblCriticalChanceBoost.setForeground(Color.BLUE);
		lblCriticalChanceBoost.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCriticalChanceBoost.setBounds(220, 249, 54, 20);
		panelMainMenu.add(lblCriticalChanceBoost);
		
		cbxWeapons = new JComboBox();
		cbxWeapons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// change weapon
				player.setCurWeapon(player.getOwnedWeapons().get(
						cbxWeapons.getSelectedIndex()));
				// update info on screen
				lblCurWeaponIcon.setIcon(player.getCurWeapon().getIcon());
				lblWeaponName.setText(player.getCurWeapon().getName());
				lblAttackBoost.setText("(+" + Integer.toString(player.getCurAttBoost()) + ")");
				lblCriticalChanceBoost.setText("(+" + Integer.toString(player.getCurCritBoost()) + ")");
			}
		});
		cbxWeapons.setBounds(119, 295, 200, 20);
		panelMainMenu.add(cbxWeapons);
		
		cbxArmours = new JComboBox();
		cbxArmours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// change armour
				player.setCurArmour(player.getOwnedArmour().get(
						cbxArmours.getSelectedIndex()));
				// update info on screen
				lblCurArmourIcon.setIcon(player.getCurArmour().getIcon());
				lblArmourName.setText(player.getCurArmour().getName());
				lblHealthBoost.setText("(+" + Integer.toString(player.getCurHpBoost()) + ")");
				lblDefenseBoost.setText("(+" + Integer.toString(player.getCurDefBoost()) + ")");
			}
		});
		cbxArmours.setBounds(119, 320, 200, 20);
		panelMainMenu.add(cbxArmours);
		
		lblSelectWeapon = new JLabel("Select Weapon");
		lblSelectWeapon.setBounds(20, 298, 95, 14);
		panelMainMenu.add(lblSelectWeapon);
		
		lblSelectArmour = new JLabel("Select Armour");
		lblSelectArmour.setBounds(20, 323, 95, 14);
		panelMainMenu.add(lblSelectArmour);
		
		lblStatExplain = new JLabel("green = base stat, blue = boost from equip.");
		lblStatExplain.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblStatExplain.setBounds(120, 268, 200, 14);
		panelMainMenu.add(lblStatExplain);
		
		btnEasyFight = new JButton("Easy Fight");
		btnEasyFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// start an easy fight
				panelMainMenu.setVisible(false);
				panelFight.setVisible(true);
				fightManager.setupFight(0, player); // easy fight is 0
			}
		});
		btnEasyFight.setForeground(Color.RED);
		btnEasyFight.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEasyFight.setBounds(430, 85, 135, 40);
		panelMainMenu.add(btnEasyFight);
		
		btnMediumFight = new JButton("Medium Fight");
		btnMediumFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// start a medium fight
				panelMainMenu.setVisible(false);
				panelFight.setVisible(true);
				fightManager.setupFight(1, player); // medium fight is 1
			}
		});
		btnMediumFight.setForeground(Color.RED);
		btnMediumFight.setFont(new Font("Dialog", Font.BOLD, 14));
		btnMediumFight.setBounds(430, 140, 135, 40);
		panelMainMenu.add(btnMediumFight);
		
		btnHardFight = new JButton("Hard Fight");
		btnHardFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// start a hard fight
				panelMainMenu.setVisible(false);
				panelFight.setVisible(true);
				fightManager.setupFight(2, player); // hard fight is 2
			}
		});
		btnHardFight.setForeground(Color.RED);
		btnHardFight.setFont(new Font("Dialog", Font.BOLD, 14));
		btnHardFight.setBounds(430, 195, 135, 40);
		panelMainMenu.add(btnHardFight);
		
		btnToShop = new JButton("Go To Shop");
		btnToShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// go to the shop
				panelMainMenu.setVisible(false);
				panelShop.setVisible(true);
				setupShopGui();
			}
		});
		btnToShop.setFont(new Font("Dialog", Font.BOLD, 18));
		btnToShop.setBounds(409, 294, 146, 46);
		panelMainMenu.add(btnToShop);
		
		JLabel lblFight = new JLabel("Fight!");
		lblFight.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFight.setForeground(Color.ORANGE);
		lblFight.setHorizontalAlignment(SwingConstants.CENTER);
		lblFight.setBounds(430, 49, 134, 34);
		panelMainMenu.add(lblFight);
		
		JPanel panelBehindFight = new JPanel();
		panelBehindFight.setBackground(Color.GRAY);
		panelBehindFight.setBounds(425, 49, 145, 193);
		panelMainMenu.add(panelBehindFight);
		panelBehindFight.setLayout(new CardLayout(0, 0));
		
		lblCoins = new JLabel("Coins: 1000");
		lblCoins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoins.setBounds(409, 268, 146, 24);
		panelMainMenu.add(lblCoins);
		
		lblWinLose = new JLabel("W 0 - L 0");
		lblWinLose.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinLose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWinLose.setBounds(446, 24, 102, 24);
		panelMainMenu.add(lblWinLose);
		
		//////////
		// Shop //
		//////////
		panelShop = new JPanel();
		contentPane.add(panelShop, "name_28811704310828");
		panelShop.setLayout(null);
		
		cbxBrowseWeapons = new JComboBox();
		cbxBrowseWeapons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxBrowseWeapons.getSelectedIndex() != -1){
					// show icon and stats
					lblEquipmentImage.setIcon(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getIcon());
					lblItemType.setText("Weapon");
					lblItemName.setText(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getName());
					lblItemAttackBoostValue.setText("(+" + shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getAttBoost() + ")");
					lblItemCriticalChanceBoostValue.setText("(+" + shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getCritBoost() + ")");
					lblItemHealthBoostValue.setText("(+0)");
					lblItemDefenseBoostValue.setText("(+0)");
					lblWeaponPrice.setText(Integer.toString(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getPrice()) + " Coins");
					// see if player can buy item
					if(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getPrice() <= player.getCoins()){
						btnBuyWeapon.setEnabled(true); // show buy button if can afford
					}else if(btnBuyWeapon.isEnabled()){
						btnBuyWeapon.setEnabled(false); // hide buy button if can't afford
					}
				}
			}
		});
		cbxBrowseWeapons.setBounds(30, 50, 160, 20);
		panelShop.add(cbxBrowseWeapons);
		
		lblBrowseWeapons = new JLabel("Browse Weapons:");
		lblBrowseWeapons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBrowseWeapons.setBounds(55, 31, 111, 17);
		panelShop.add(lblBrowseWeapons);
		
		lblBrowseArmour = new JLabel("Browse Armour:");
		lblBrowseArmour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBrowseArmour.setBounds(245, 32, 111, 17);
		panelShop.add(lblBrowseArmour);
		
		cbxBrowseArmours = new JComboBox();
		cbxBrowseArmours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxBrowseArmours.getSelectedIndex() != -1){
					// show icon and stats
					lblEquipmentImage.setIcon(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getIcon());
					lblItemType.setText("Armour");
					lblItemName.setText(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getName());
					lblItemHealthBoostValue.setText("(+" + shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getHpBoost() + ")");
					lblItemDefenseBoostValue.setText("(+" + shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getDefBoost() + ")");
					lblItemAttackBoostValue.setText("(+0)");
					lblItemCriticalChanceBoostValue.setText("(+0)");
					lblArmourPrice.setText(Integer.toString(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getPrice()) + " Coins");
					// see if player can buy item
					if(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getPrice() <= player.getCoins()){
						btnBuyArmour.setEnabled(true); // show buy button if can afford
					}else if(btnBuyArmour.isEnabled()){
						btnBuyArmour.setEnabled(false); // hide buy button if can't afford
					}
				}
			}
		});
		cbxBrowseArmours.setBounds(220, 50, 160, 20);
		panelShop.add(cbxBrowseArmours);
		
		btnBuyWeapon = new JButton("Buy Weapon");
		btnBuyWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// buy a weapon
				player.removePlayerCoins(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getPrice()); // remove coins from player
				player.addWeapon(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex())); // add weapon to player
				// update combo box for player weapon select
				cbxWeapons.addItem(shopManager.getShopWeapons().get(cbxBrowseWeapons.getSelectedIndex()).getName());
				// remove weapon from shop
				shopManager.removeWeapon((cbxBrowseWeapons.getSelectedIndex()));
				// reset shop gui
				setupShopGui();
			}
		});
		btnBuyWeapon.setForeground(new Color(0, 128, 0));
		btnBuyWeapon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuyWeapon.setBounds(30, 230, 160, 40);
		panelShop.add(btnBuyWeapon);
		
		lblWeaponPrice = new JLabel("1000 Coins");
		lblWeaponPrice.setForeground(new Color(218, 165, 32));
		lblWeaponPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeaponPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeaponPrice.setBounds(63, 210, 95, 20);
		panelShop.add(lblWeaponPrice);
		
		lblArmourPrice = new JLabel("1000 Coins");
		lblArmourPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblArmourPrice.setForeground(new Color(218, 165, 32));
		lblArmourPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArmourPrice.setBounds(253, 210, 95, 20);
		panelShop.add(lblArmourPrice);
		
		btnBuyArmour = new JButton("Buy Armour");
		btnBuyArmour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// buy an armour
				player.removePlayerCoins(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getPrice()); // remove coins from player
				player.addArmour(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex())); // add armour to player
				// update combo box for player armour select
				cbxArmours.addItem(shopManager.getShopArmours().get(cbxBrowseArmours.getSelectedIndex()).getName());
				// remove armour from shop
				shopManager.removeArmour((cbxBrowseArmours.getSelectedIndex()));
				// reset shop gui
				setupShopGui();
			}
		});
		btnBuyArmour.setForeground(new Color(0, 128, 0));
		btnBuyArmour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuyArmour.setBounds(220, 230, 160, 40);
		panelShop.add(btnBuyArmour);
		
		lblYourCoins = new JLabel("Your Coins: 1000");
		lblYourCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourCoins.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblYourCoins.setBounds(201, 313, 171, 27);
		panelShop.add(lblYourCoins);
		
		lblEquipmentImage = new JLabel("");
		lblEquipmentImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblEquipmentImage.setBounds(450, 72, 75, 75);
		panelShop.add(lblEquipmentImage);
		
		lblItemHealthBoostLabel = new JLabel("Health:");
		lblItemHealthBoostLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemHealthBoostLabel.setBounds(410, 190, 43, 20);
		panelShop.add(lblItemHealthBoostLabel);
		
		lblItemAttackBoostLabel = new JLabel("Attack:");
		lblItemAttackBoostLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemAttackBoostLabel.setBounds(410, 210, 54, 20);
		panelShop.add(lblItemAttackBoostLabel);
		
		lblItemDefenseBoostLabel = new JLabel("Defense:");
		lblItemDefenseBoostLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemDefenseBoostLabel.setBounds(410, 230, 54, 20);
		panelShop.add(lblItemDefenseBoostLabel);
		
		lblItemCriticalChanceBoostLabel = new JLabel("Critical Chance:");
		lblItemCriticalChanceBoostLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemCriticalChanceBoostLabel.setBounds(410, 250, 94, 20);
		panelShop.add(lblItemCriticalChanceBoostLabel);
		
		lblItemHealthBoostValue = new JLabel("(+100)");
		lblItemHealthBoostValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemHealthBoostValue.setForeground(Color.BLUE);
		lblItemHealthBoostValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemHealthBoostValue.setBounds(459, 190, 54, 20);
		panelShop.add(lblItemHealthBoostValue);
		
		lblItemAttackBoostValue = new JLabel("(+100)");
		lblItemAttackBoostValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemAttackBoostValue.setForeground(Color.BLUE);
		lblItemAttackBoostValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemAttackBoostValue.setBounds(459, 210, 54, 20);
		panelShop.add(lblItemAttackBoostValue);
		
		lblItemDefenseBoostValue = new JLabel("(+100)");
		lblItemDefenseBoostValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemDefenseBoostValue.setForeground(Color.BLUE);
		lblItemDefenseBoostValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemDefenseBoostValue.setBounds(469, 230, 54, 20);
		panelShop.add(lblItemDefenseBoostValue);
		
		lblItemCriticalChanceBoostValue = new JLabel("(+100)");
		lblItemCriticalChanceBoostValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemCriticalChanceBoostValue.setForeground(Color.BLUE);
		lblItemCriticalChanceBoostValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemCriticalChanceBoostValue.setBounds(510, 250, 54, 20);
		panelShop.add(lblItemCriticalChanceBoostValue);
		
		lblItemName = new JLabel("Item Name");
		lblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemName.setBounds(410, 165, 154, 14);
		panelShop.add(lblItemName);
		
		lblItemType = new JLabel("Item Type");
		lblItemType.setForeground(new Color(128, 0, 0));
		lblItemType.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemType.setBounds(450, 150, 75, 14);
		panelShop.add(lblItemType);
		
		btnGoToMenu = new JButton("Go To Menu");
		btnGoToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// go to menu
				panelShop.setVisible(false);
				panelMainMenu.setVisible(true);
				updateMainMenu(); // update the menu gui
			}
		});
		btnGoToMenu.setFont(new Font("Dialog", Font.BOLD, 18));
		btnGoToMenu.setBounds(409, 294, 146, 46);
		panelShop.add(btnGoToMenu);
			
		///////////
		// Fight //
		///////////
		
		panelFight = new JPanel();
		contentPane.add(panelFight, "name_2341295376837");
		panelFight.setLayout(null);
		
		setLblFPlayersIcon(new JLabel(""));
		getLblFPlayersIcon().setBorder(new LineBorder(new Color(30, 144, 255), 2));
		getLblFPlayersIcon().setBounds(80, 10, 150, 150);
		panelFight.add(getLblFPlayersIcon());
		
		setLblFOpponentIcon(new JLabel(""));
		getLblFOpponentIcon().setBorder(new LineBorder(new Color(255, 0, 0), 2));
		getLblFOpponentIcon().setBounds(345, 5, 150, 150);
		panelFight.add(getLblFOpponentIcon());
		
		setLblFPlayerWeapon(new JLabel(""));
		getLblFPlayerWeapon().setBorder(new LineBorder(new Color(30, 144, 255), 2));
		getLblFPlayerWeapon().setBounds(5, 85, 75, 75);
		panelFight.add(getLblFPlayerWeapon());
		
		setLblFPlayerArmour(new JLabel(""));
		getLblFPlayerArmour().setBorder(new LineBorder(new Color(30, 144, 255), 2));
		getLblFPlayerArmour().setBounds(230, 85, 75, 75);
		panelFight.add(getLblFPlayerArmour());
		
		setLblFOpponentWeapon(new JLabel(""));
		getLblFOpponentWeapon().setBorder(new LineBorder(new Color(255, 0, 0), 2));
		getLblFOpponentWeapon().setBounds(270, 5, 75, 75);
		panelFight.add(getLblFOpponentWeapon());
		
		setLblFOpponentArmour(new JLabel(""));
		getLblFOpponentArmour().setBorder(new LineBorder(new Color(255, 0, 0), 2));
		getLblFOpponentArmour().setBounds(495, 5, 75, 75);
		panelFight.add(getLblFOpponentArmour());
		
		JPanel panelFPlayerBack = new JPanel();
		panelFPlayerBack.setBorder(new LineBorder(new Color(0, 0, 139), 2, true));
		panelFPlayerBack.setBackground(new Color(0, 0, 255));
		panelFPlayerBack.setBounds(0, 80, 310, 85);
		panelFight.add(panelFPlayerBack);
		
		panelFOpponentBack = new JPanel();
		panelFOpponentBack.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		panelFOpponentBack.setBackground(new Color(139, 0, 0));
		panelFOpponentBack.setBounds(265, 0, 310, 85);
		panelFight.add(panelFOpponentBack);
		
		panelFPlayerBack2 = new JPanel();
		panelFPlayerBack2.setBorder(new LineBorder(new Color(0, 0, 139), 2, true));
		panelFPlayerBack2.setBackground(new Color(0, 0, 255));
		panelFPlayerBack2.setBounds(75, 5, 160, 95);
		panelFight.add(panelFPlayerBack2);
		
		panelFOpponentBack2 = new JPanel();
		panelFOpponentBack2.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		panelFOpponentBack2.setBackground(new Color(139, 0, 0));
		panelFOpponentBack2.setBounds(340, 64, 160, 95);
		panelFight.add(panelFOpponentBack2);
		
		setLblFPlayersName(new JLabel("Players Name"));
		getLblFPlayersName().setHorizontalAlignment(SwingConstants.CENTER);
		getLblFPlayersName().setFont(new Font("Tahoma", Font.PLAIN, 16));
		getLblFPlayersName().setBounds(89, 165, 132, 20);
		panelFight.add(getLblFPlayersName());
		
		setLblFOpponentsName(new JLabel("Opponents Name"));
		getLblFOpponentsName().setHorizontalAlignment(SwingConstants.CENTER);
		getLblFOpponentsName().setFont(new Font("Tahoma", Font.PLAIN, 16));
		getLblFOpponentsName().setBounds(355, 165, 132, 20);
		panelFight.add(getLblFOpponentsName());
		
		setBtnFGoToMenu(new JButton("Go To Menu"));
		getBtnFGoToMenu().setVisible(false);
		getBtnFGoToMenu().setFont(new Font("Tahoma", Font.BOLD, 12));
		getBtnFGoToMenu().setBounds(213, 326, 132, 20);
		panelFight.add(getBtnFGoToMenu());
		
		setScrlpnFightText(new JScrollPane());
		getScrlpnFightText().setVisible(false);
		
		setBtnFStartFightButton(new JButton("Fight!"));
		getBtnFStartFightButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// show guis
				getScrlpnFightText().setVisible(true);
				getTxtrFightTextArea().setVisible(true);
				getBtnFStartFightButton().setVisible(false); // hide fight button
				fightManager.startFight(); // start fight
			}
		});
		getBtnFStartFightButton().setForeground(new Color(255, 0, 0));
		getBtnFStartFightButton().setFont(new Font("Tahoma", Font.BOLD, 30));
		getBtnFStartFightButton().setBounds(207, 196, 160, 74);
		panelFight.add(getBtnFStartFightButton());
		getScrlpnFightText().setBounds(5, 206, 565, 114);
		panelFight.add(getScrlpnFightText());
		
		setTxtrFightTextArea(new JTextArea());
		getTxtrFightTextArea().setVisible(false);
		getTxtrFightTextArea().setEditable(false);
		getScrlpnFightText().setViewportView(getTxtrFightTextArea());
		getTxtrFightTextArea().setLineWrap(true);
		
		setLblFPlayerHealth(new JLabel("100/100"));
		getLblFPlayerHealth().setHorizontalAlignment(SwingConstants.CENTER);
		getLblFPlayerHealth().setForeground(new Color(0, 128, 0));
		getLblFPlayerHealth().setFont(new Font("Tahoma", Font.BOLD, 22));
		getLblFPlayerHealth().setBounds(93, 182, 124, 25);
		panelFight.add(getLblFPlayerHealth());
		
		setLblFOpponentHealth(new JLabel("100/100"));
		getLblFOpponentHealth().setHorizontalAlignment(SwingConstants.CENTER);
		getLblFOpponentHealth().setForeground(new Color(0, 128, 0));
		getLblFOpponentHealth().setFont(new Font("Tahoma", Font.BOLD, 22));
		getLblFOpponentHealth().setBounds(363, 182, 117, 25);
		panelFight.add(getLblFOpponentHealth());
		
	}
	
	/**
	 * Only called the first time the main menu is opened.
	 * 
	 */
	private void setupMainMenu(){
		// populate images and text
		lblPlayersIcon.setIcon(player.getIcon());
		lblPlayersName.setText(player.getName());
		lblCurWeaponIcon.setIcon(player.getCurWeapon().getIcon());
		lblWeaponName.setText(player.getCurWeapon().getName());
		lblCurArmourIcon.setIcon(player.getCurArmour().getIcon());
		lblArmourName.setText(player.getCurArmour().getName());
		lblHealthStat.setText(Integer.toString(player.getHpSkill()));
		lblHealthBoost.setText("(+" + Integer.toString(player.getCurHpBoost()) + ")");
		lblAttackStat.setText(Integer.toString(player.getAttSkill()));
		lblAttackBoost.setText("(+" + Integer.toString(player.getCurAttBoost()) + ")");
		lblDefenseStat.setText(Integer.toString(player.getDefSkill()));
		lblDefenseBoost.setText("(+" + Integer.toString(player.getCurDefBoost()) + ")");
		lblCriticalChanceBoost.setText("(+" + Integer.toString(player.getCurCritBoost()) + ")");
		lblCoins.setText("Coins: " + Integer.toString(player.getCoins()));
		
		// populate combo boxes
		cbxWeapons.addItem(player.getOwnedWeapons().get(0).getName());
		cbxArmours.addItem(player.getOwnedArmour().get(0).getName());
	}
	
	protected void updateMainMenu() {
		lblCoins.setText("Coins: " + Integer.toString(player.getCoins()));
		// update wins/losses
		lblWinLose.setText("W " + player.getWins() + " - " + "L " + player.getLosses());
	}
	
	private void setupShopGui(){
		// clear and populate combo boxes
		cbxBrowseWeapons.removeAllItems();
		for(int i = 0; i < shopManager.getShopWeapons().size(); i++){
			cbxBrowseWeapons.addItem(shopManager.getShopWeapons().get(i).getName());
		}
		cbxBrowseArmours.removeAllItems();
		for(int i = 0; i < shopManager.getShopArmours().size(); i++){
			cbxBrowseArmours.addItem(shopManager.getShopArmours().get(i).getName());
		}
		
		// update images and text
		lblWeaponPrice.setText("- Coins");
		lblArmourPrice.setText("- Coins");
		lblYourCoins.setText(player.getName() + "'s Coins: " + Integer.toString(player.getCoins()));
		lblEquipmentImage.setIcon(null);
		lblItemType.setText("-");
		lblItemName.setText("-");
		lblItemHealthBoostValue.setText("(+0)");
		lblItemAttackBoostValue.setText("(+0)");
		lblItemDefenseBoostValue.setText("(+0)");
		lblItemCriticalChanceBoostValue.setText("(+0)");
		
		// disable buy buttons
		btnBuyWeapon.setEnabled(false);
		btnBuyArmour.setEnabled(false);
	}
}
