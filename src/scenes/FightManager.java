package scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import app.GameMaster;
import people.Fighter;
import people.Opponent;
import people.Player;

public class FightManager {
	
	private Player player;
	private Opponent opponent;
	private Timer timer = new Timer(200, new TimerHandler()); // create timer ticks every 200 ms.;
	private boolean isP1Turn; // if it is players turn
	private Fighter curAttacker;
	private Fighter curReciever;

	
	private class TimerHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent actionEvent){
			doTick();
		}

		private void doTick(){
			takeTurn();
		}
		
	}
	
	public void startFight(){
		timer.start();
	}
	
	/**
	 * Setup the fight scene with a certain difficulty.
	 * 
	 */
	public void setupFight(int difficulty, Player player){
		opponent = new Opponent(difficulty); // create an enemy
		this.player = player;
		this.player.setCurHp(this.player.getCurMaxHp()); // reset player health
		isP1Turn = true; // always start with players turn
		
		//reset health guis
		GameMaster.getGui().getLblFOpponentHealth().setText(Integer.toString(opponent.getCurHp()) + "/" + Integer.toString(opponent.getCurMaxHp()));
		GameMaster.getGui().getLblFPlayerHealth().setText(Integer.toString(player.getCurHp()) + "/" + Integer.toString(player.getCurMaxHp()));
		
		// hide and show stuff
		GameMaster.getGui().getBtnFStartFightButton().setVisible(true);
		GameMaster.getGui().getTxtrFightTextArea().setText("");
		GameMaster.getGui().getScrlpnFightText().setVisible(false);
		GameMaster.getGui().getTxtrFightTextArea().setVisible(false);
		GameMaster.getGui().getBtnFGoToMenu().setVisible(false);
		GameMaster.getGui().getLblFPlayersName().setText(this.player.getName());
		GameMaster.getGui().getLblFOpponentsName().setText(opponent.getName());
		GameMaster.getGui().getLblFPlayerArmour().setIcon(this.player.getCurArmour().getIcon());
		GameMaster.getGui().getLblFPlayerWeapon().setIcon(this.player.getCurWeapon().getIcon());
		GameMaster.getGui().getLblFOpponentArmour().setIcon(opponent.getCurArmour().getIcon());
		GameMaster.getGui().getLblFOpponentWeapon().setIcon(opponent.getCurWeapon().getIcon());
		GameMaster.getGui().getLblFPlayersIcon().setIcon(this.player.getIcon());
		GameMaster.getGui().getLblFOpponentIcon().setIcon(opponent.getIcon());
		
	}
	
	private void takeTurn(){
		Random random = new Random();

		if(isP1Turn){
			curAttacker = (Fighter)player;
			curReciever = (Fighter)opponent;
		}else{
			curAttacker = (Fighter)opponent;
			curReciever = (Fighter)player;
		}

		// calculate if there is a critical hit
		boolean isCritHit = false;
		String critString = "";
		if(curAttacker.getCurCritBoost() > random.nextInt(100)){
			isCritHit = true;
			critString = "CRITICAL HIT!!! ";
		}
		
		// calculate damage to deal
		int damage = (int) (((curAttacker.getAttSkill() + curAttacker.getCurAttBoost()) - 
				((curReciever.getDefSkill() + curReciever.getCurDefBoost()) / 2)) * 
				(.5 + random.nextDouble())) / 3;
		// damage is atleast 1
		if(damage < 1){
			damage = 1;
		}
		
		// do double damage if critical hit
		if(isCritHit){
			damage *= 2;
		}
		
		// do the damage
		curReciever.recieveDamage(damage);
		
		//display damage
		if(isP1Turn){
			// enemy health goes down
			GameMaster.getGui().getLblFOpponentHealth().setText(Integer.toString(opponent.getCurHp()) + "/" + Integer.toString(opponent.getCurMaxHp()));
		}else{
			// player health goes down
			GameMaster.getGui().getLblFPlayerHealth().setText(Integer.toString(player.getCurHp()) + "/" + Integer.toString(player.getCurMaxHp()));
		}
		
		// show text
		GameMaster.getGui().getTxtrFightTextArea().append(critString + curAttacker.getName() + " " + curAttacker.getCurWeapon().getAttVerb() +
				" " + curReciever.getName() + " for " + Integer.toString(damage) + " points!" + "\n");

		// if reciever died, go to end
		if(curReciever.getCurHp() <= 0){
			timer.stop();
			boolean winner = false;
			if(isP1Turn){
				winner = true;
			}
			endOfFight(winner, curAttacker);
		}
		
		isP1Turn = !isP1Turn; // switch for next turn
	}
	
	private void endOfFight(boolean isP1Winner, Fighter attacker){
		// do finishing things for each fighter
		player.finishFight(isP1Winner);
		opponent.finishFight(isP1Winner);
		
		// display winner
		GameMaster.getGui().getTxtrFightTextArea().append(attacker.getName() + " wins!\n");
		// give money if player won
		if(attacker.equals(player)){
			GameMaster.getGui().getTxtrFightTextArea().append("You win " + Integer.toString(opponent.getCoins()) +" coins!\n");
		}
		GameMaster.getGui().getTxtrFightTextArea().append(opponent.getName() + ": " + opponent.getCurSaying());
		
		// if p1 won or not
		if(isP1Winner){
			player.givePlayerCoins(opponent.getCoins());
		}else{
			player.removePlayerCoins(opponent.getCoins());
		}

		GameMaster.getGui().getBtnFGoToMenu().setVisible(true);
	}

}
