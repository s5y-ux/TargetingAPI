package net.ddns.vcccd;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class Target implements Listener{
	
	@EventHandler
	public void OnFire(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		int BlockArea = 10;
		try {
		Mob test = getTarget(player, BlockArea);
		test.damage(test.getHealth());}
		catch(Exception exception) {
			assert true;
		}
			
		
		
	}
	
	private Mob getTarget(Player player, int BlockArea) {
		
		//Value to be returned from the Function
		Mob returnVal = null;
		
		//Iterates over the array of entities retrieved form the area
		for(Entity IterativeEntity: player.getNearbyEntities(BlockArea, BlockArea, BlockArea)) {
			
			//We want to make sure the target is a Mob
			if(IterativeEntity instanceof Mob) {
				
			//If so, we want to get the directional vector of the eye location
			Vector PlayerDirectionalVector = player.getEyeLocation().getDirection();
			
			//And we also want to get the Local Targets location
			Location TargetLocal = IterativeEntity.getLocation();
			
			//TargetLocal is located at the base of the entity, we want the body
			//I know all mobs aren't two blocks tall, will change this later
			TargetLocal.setY(TargetLocal.getY()+1);
			
			//We then want to subtract the new target location from the eye location
			Vector TargetPositionalVector = TargetLocal.toVector().subtract(player.getEyeLocation().toVector());
			
			//And we take angle Theta of the direction the player is facing and the position of the target
			double Theta = PlayerDirectionalVector.angle(TargetPositionalVector);
			
			//If that Theta value is within a certain threshold, we can assume targeting
			if(Theta < 0.130) {
				
				//We then cast the entity to type mob (Note instanceof keyword...)
				Mob target = (Mob) IterativeEntity;
				
				//We set the return value to the first enemy in that threshold
				returnVal = target;
				
				//And break the loop...
				break;
			}
			}
			
		}
		
		//We then return the value stored in the return value
		return(returnVal);
		
		/*
		 * Please note that if there is no targeted enemy, 
		 * the function will return null. Bukkit does not
		 * like this very much. So any implementation must
		 * be put in a try, except structure.
		 */
	}

}
