package model;

import java.util.ArrayList;
import java.util.Random;


public class PossibleHazards{
	int hazard_num;
	ArrayList<Hazard> hazards;


	/**constructor for the possible hazards class which contains 
	 * all of the possible hazards that can spawn in a given round
	 * 
	 */
	public PossibleHazards(int hazard_num){
		this.hazard_num = hazard_num;
		this.hazards = new ArrayList<Hazard>();
	}
	public void generateHazards(){
		Random gen = new Random();
		int spawnwindow = 1000;
		int spawnInt;
		for(int i=0; i<hazard_num; i++){
			spawnInt = gen.nextInt(spawnwindow);
			System.out.println("Spawn: " + spawnInt);
			switch(gen.nextInt(6)){
			case 0:
				hazards.add( new Enemy1(350, gen.nextInt(350)+1, 1, 0, spawnInt));
				break;
			case 1:
				hazards.add(new Enemy2(350, gen.nextInt(350)+1, 1, 0, spawnInt));
				break;
			case 2:
				hazards.add(new Enemy3(350, gen.nextInt(350)+1, 1, 0, spawnInt));
				break;
			case 3:
				hazards.add(new Trash(350, gen.nextInt(350)+1, 1, 0, spawnInt, Tool.TRASH));
				break;
			case 4:
				hazards.add(new Trash(350, gen.nextInt(350)+1, 1, 0, spawnInt, Tool.RECYCLE));
				break;
			case 5:
				hazards.add(new Trash(350, gen.nextInt(350)+1, 1, 0, spawnInt, Tool.COMPOST));
				break;
			}
			
		}
	}
	/**
	 * @param hazards 
	 * hazards are an array of all possible hazards
	 * Sets the array containing all of the possible hazards
	 */
	
	public void setpossibleHazards(ArrayList<Hazard> hazards){
		this.hazards = hazards;
	}

	/**
	 * an array of all possible hazards
	 * @return void
	 * gets the possible Hazards from the array list
	 */ 
	public ArrayList<Hazard> getHazardsList(){
		return this.hazards;
	}
	public void moveLeft(){
		for(int i=0; i<hazard_num; i++){
			hazards.get(i).moveLeft();
		}
	}
	/**
	 * @param hazard
	 * an array of all possible hazards
	 * adds a single hazard to the array
	 * 
	 */
	public void addHazard(Hazard hazard){
		hazards.add(hazard);
	}

	/**
	 * @param index
	 * index is an int that represents the position we want in the array
	 * Removes a hazard from the array at the given index
	 */
	public void removeHazard(int index){
		hazards.remove(index);
	}

	/**
	 * select a random hazard in array
	 */
	public Hazard selectRandom(){
		Random gen = new Random();
		return hazards.get(gen.nextInt(hazards.size()));
	}
}