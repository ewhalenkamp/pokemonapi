package com.erikhalenkamp.pokemonapi;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BasePokemon {
    
    private ArrayList<BaseAbility> possibleAbilities;
    private ArrayList<BaseMove> possibleMoves;
    
    private String name,type1;
    private String type2;
    private int dexnum,instancenum;
    private int basehp,baseatk,basedef,basespatk,basespdef,basespeed;
    
    //gamedata attributes
    
    private int dataid;
    private String dataname;
    private double height,weight;
    private boolean ismega,isalt,canchange;

    /***********************************************************************
    * Method:   BasePokemon Constructor                                    *
    * Purpose:  Constructor for the BasePokemon object                     *
    * Parameters:                                                          *
    *     name - name of the Pokemon                                       *
    *     dexnum - index of a unique Pokemon in the Pokedex                *
    *     basehp - the base HP value of the Pokemon                        *
    *     baseatk - the base Attack value of the Pokemon                   *
    *     basedef - the base Defense value of the Pokemon                  *
    *     basespatk - the base Special Attack value of the Pokemon         *
    *     basespdef - the base Special Defense value of the Pokemon        *
    *     basespeed - the base Speed value of the Pokemon                  *
    *     type1 - the primary type of the Pokemon                          *
    *     type2 - the secondary type of the Pokemon, if any                *
    * Return Value:  none                                                  *
    ***********************************************************************/
    
    public BasePokemon(String name, int dexnum, int instancenum, int basehp, int baseatk, int basedef, int basespatk, int basespdef, int basespeed, String type1, String type2) throws IOException
    {
        int index;
        ArrayList<Character> chars = new ArrayList<Character>();
        if (dexnum == 1) {
            chars.clear();
            for (int i=3;i<name.length();i++)
                chars.add(name.charAt(i));
            name = "";
            for (char c : chars) {
                name += c;
            }
        }
        this.possibleAbilities = new ArrayList<BaseAbility>();
        this.possibleMoves = new ArrayList<BaseMove>();
        this.name = name;
        this.dexnum = dexnum;
        this.instancenum = instancenum;
        this.basehp = basehp;
        this.baseatk = baseatk;
        this.basedef = basedef;
        this.basespatk = basespatk;
        this.basespdef = basespdef;
        this.basespeed = basespeed;
        this.type1 = type1;
        this.type2 = type2;
    }
    
    /***********************************************************************
    * Method:   BasePokemon Constructor                                    *
    * Purpose:  Constructor for the BasePokemon object                     *
    * Parameters:                                                          *
    *     name - name of the Pokemon                                       *
    *     dexnum - index of a unique Pokemon in the Pokedex                *
    *     basehp - the base HP value of the Pokemon                        *
    *     baseatk - the base Attack value of the Pokemon                   *
    *     basedef - the base Defense value of the Pokemon                  *
    *     basespatk - the base Special Attack value of the Pokemon         *
    *     basespdef - the base Special Defense value of the Pokemon        *
    *     basespeed - the base Speed value of the Pokemon                  *
    *     type1 - the primary type of the Pokemon                          *
    * Return Value:  none                                                  *
    ***********************************************************************/
    
    public BasePokemon(String name, int dexnum, int instancenum, int basehp, int baseatk, int basedef, int basespatk, int basespdef, int basespeed, String type1) throws IOException
    {
        this(name, dexnum, instancenum, basehp, baseatk, basedef, basespatk, basespdef, basespeed, type1, "");
    }
    
    /***********************************************************************
    * Method:   BasePokemon Constructor                                    *
    * Purpose:  Constructor for the BasePokemon object                     *
    * Parameters:                                                          *
    *     name - name of the Pokemon                                       *
    *     dexnum - index of a unique Pokemon in the Pokedex                *
    *     type1 - the primary type of the Pokemon                          *
    * Return Value:  none                                                  *
    ***********************************************************************/
    
    public BasePokemon(String name, int dexnum, int instancenum, String type1) throws IOException
    {
        this(name, dexnum,instancenum, 0, 0, 0, 0, 0, 0, type1, "");
    }
    
    /***********************************************************************
    * Method:   BasePokemon Constructor                                    *
    * Purpose:  Constructor for the BasePokemon object                     *
    * Parameters:                                                          *
    *     name - name of the Pokemon                                       *
    *     dexnum - index of a unique Pokemon in the Pokedex                *
    *     type1 - the primary type of the Pokemon                          *
    *     type2 - the secondary type of the Pokemon, if any                *
    * Return Value:  none                                                  *
    ***********************************************************************/
    
    public BasePokemon(String name, int dexnum, int instancenum, String type1, String type2) throws IOException
    {
        this(name, dexnum, instancenum, 0, 0, 0, 0, 0, 0, type1, type2);
    }
    
    //method creates ActivePokemon component, unique per battle
    
    public void addData(String dataname,int dataid, double height, double weight, boolean isalt,boolean ismega,boolean canchange) {
        this.dataname = dataname;
        this.dataid = dataid;
        this.height = height;
        this.weight = weight;
        this.isalt = isalt;
        this.ismega = ismega;
        this.canchange = canchange;
    }
    
    /*the following methods called from the merge() method of data, and add the possible abilities/moves the Pokemon can 
    have to the possibleAbilities/possibleMoves attribute*/
    
    public void addAbilities(BaseAbility ability) {
        this.possibleAbilities.add(ability);
    }
    
    public ArrayList<BaseAbility> getPossibleAbilities() {
        return this.possibleAbilities;
    }
    
    
    public void addMoves(BaseMove move) {
        if (!(possibleMoves.contains(move)))
            this.possibleMoves.add(move);
    }
    
    public ArrayList<BaseMove> getPossibleMoves() {
        return this.possibleMoves;
    }
    
    
    /***********************************************************************
    * Method:   Getters and Setters                                        *
    * Purpose:  Getters and Setters for each of the attributes             *
    * Attributes:                                                          *
    *     name, dexnum, basehp, baseatk, basedef, basespatk, basespdef,    *
    *     basespeed, type1, type2                                          *                                                *
    ***********************************************************************/
    
    public String getDataName() {
        return this.dataname;
    }
    
    public void setDataName(String dataname) {
        this.dataname = dataname;
    }
    
    public int getDataID() {
        return this.dataid;
    }
    
    public void setDataID(int dataid) {
        this.dataid = dataid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getDexNum() {
        return this.dexnum;
    }
    
    public void setDexNum(int dexnum) {
        this.dexnum = dexnum;
    }
    
    public int getInstanceNum() {
        return this.instancenum;
    }
    
    public void setInstanceNum(int dexnum) {
        this.instancenum = instancenum;
    }
    
    public int getBaseHP() {
        return this.basehp;
    }
    
    public void setBaseHP(int basehp) {
        this.basehp = basehp;
    }
    
    public int getBaseAtk() {
        return this.baseatk;
    }
    
    public void setBaseAtk(int baseatk) {
        this.baseatk = baseatk;
    }
    
    public int getBaseDef() {
        return this.basedef;
    }
    
    public void setBaseDef(int basedef) {
        this.basedef = basedef;
    }
    
    public int getBaseSpAtk() {
        return this.basespatk;
    }
    
    public void setBaseSpAtk(int basespatk) {
        this.basespatk = basespatk;
    }
    
    public int getBaseSpDef() {
        return this.basespdef;
    }
    
    public void setBaseSpDef(int basespdef) {
        this.basedef = basespdef;
    }
    
    public int getBaseSpeed() {
        return this.basespeed;
    }
    
    public void setBaseSpeed(int basespeed) {
        this.basespeed = basespeed;
    }
    
    public String getType1() {
        return this.type1;
    }
    
    public void setType1(String type) {
        this.type1 = type;
    }
    
    public String getType2() {
        return this.type2;
    }
    
    public void setType2(String type) {
        this.type2 = type;
    }
    
    public boolean isMega() {
        return this.ismega;
    }
    
    public boolean isAlt() {
        return this.isalt;
    }
    
    public Double getHeight() {
        return this.height;
    }
    
    public Double getWeight() {
        return this.weight;
    }
   
    
    public void setPossibleAbilities(ArrayList<BaseAbility> possibleAbilities) {
        this.possibleAbilities = possibleAbilities;
    }
    
    /***********************************************************************
    * Method:   baseStatTotal()                                            *
    * Purpose:  Calculates the base stat total of the Pokmeon in question  *
    * Parameters:                                                          *
    *     none                                                             *
    * Return Value: this.basehp + this.baseatk + this.basedef +            *
    *               this.basespatk + this.basespdef + this.basespeed;      *
    ***********************************************************************/
    
    public int baseStatTotal() {
        return this.basehp + this.baseatk + this.basedef + this.basespatk + this.basespdef + this.basespeed;
    }
    
    /***********************************************************************
    * Method:   description()                                              *
    * Purpose:  Creates a more user-friendly display of important stats    *
    * Parameters:                                                          *
    *     none                                                             *
    * Return Value: string displaying name, types, and BST                 *
    ***********************************************************************/
    
    public String description() {
        return this.name + "\n" + this.type1 + " " + this.type2 + "\n" + "BST: " + this.baseStatTotal();
    }
    
    /***********************************************************************
    * Method:   toString   ()                                              *
    * Purpose:  Returns all attributes in a line                           *
    * Parameters:                                                          *
    *     none                                                             *
    * Return Value: all attributes, non-formatted                          *
    ***********************************************************************/
    
    public String createJSON(DataClass data, Boolean isIndividual) {
    	//shameful implementation, but I can't for the life of me find out why Bulbasaur's name is getting truncated
    	if (this.name.equals("lbasaur")) {
    		this.name = "Bulbasaur";
    		System.out.println(this.possibleMoves);
    	}
    	if (this.name.contains("'")) {
    		this.name = this.name.replaceAll("'", "=");
    	}
    	String abilityString = "", moveString = "", statsString = "", descriptionString = "";
    	if (isIndividual) {
	    	abilityString = "'possibleabilities': [";
	    	moveString = "'possiblemoves': [";
	    	//NOTE: make dependency for empty ability/movelists to revert to previous pokemon
	    	Boolean checkbool = false;
	    	for (BaseAbility ability : this.possibleAbilities) {
	    		abilityString += "'" + ability.getName() + "', ";
	    		checkbool = true;
	    	}
	    	if (checkbool) abilityString = (abilityString.substring(0, abilityString.length() - 2));
	    	abilityString += "], ";
	    	checkbool = false;
	    	for (BaseMove move : this.possibleMoves) {
	    		moveString += move.createJSON() + ", ";
	    		checkbool = true;
	    	}
	    	if (checkbool) moveString = (moveString.substring(0, moveString.length() - 2));
	    	moveString += "], ";
	    	descriptionString = "'description': '" + data.getDexList().get(this.dexnum-1) + "', ";
	    	statsString = "'stats': {'basehp': " + Integer.toString(this.basehp) + ", 'baseatk': " + 
    			Integer.toString(this.baseatk) + ", 'basedef': " + Integer.toString(this.basedef) + ", 'basespatk': " + 
        		Integer.toString(this.basespatk) + ", 'basespdef': " + Integer.toString(this.basespdef) + ", 'basespeed': " + 
    			Integer.toString(this.basespeed) + "}, ";
    	}
    	String type2Text = ", {'name': '" + this.type2 + "', 'rgb': '" + this.colorToString(this.getTypeColor(this.type2)) + "'}";
    	if (this.type2.equals(""))
    		type2Text = "";
    	String context = "{'id': '" + Integer.toString(this.instancenum) + "', 'dexnum': '" + Integer.toString(this.dexnum) + 
        		"', 'name': '" + this.name + "', " + statsString + "'types': [{'name': '" + this.type1 + "', 'rgb': '" + 
        		this.colorToString(this.getTypeColor(this.type1)) + "'}" + type2Text + "], " + descriptionString + abilityString + moveString + "'characteristics': {'height': '" + Double.toString(this.height) + 
    			"', 'weight': '" + Double.toString(this.weight) + "'}}";
    	context = context.replaceAll("'", "\"");
    	context = context.replaceAll("=", "'");
		return context;
    }
    
    /***********************************************************************
    * Method:   displayImage(int, int)                                     *
    * Purpose:  Draws saved image of the pokemon in question               *
    * Parameters:                                                          *
    *     displayX - input from tester on where to draw image              *
    *     displayX - input from tester on where to draw image              *
    * Return Value: none                                                   *
    ***********************************************************************/

    public String colorToString(ArrayList<Integer> colors) {
    	if (colors != null) {
	    	String str = "";
	    	for (int color : colors) {
	    		str += Integer.toString(color) + ",";
	    	}
	    	System.out.println(str.length());
	    	str = str.substring(0,str.length()-1);
	    	
	    	return str;
    	}
    	return "";
    }
    
    public ArrayList<Integer> getTypeColor(String type) {
    	if (type == "")
    		return null;
        ArrayList<Integer> colors = new ArrayList<Integer>();
        if (type.equals("Water")) {
            colors.add(104);
            colors.add(152);
            colors.add(207);
        }
        else if (type.equals("Fire")) {
            colors.add(248);
            colors.add(144);
            colors.add(48);
        }
        else if (type.equals("Grass")) {
            colors.add(144);
            colors.add(232);
            colors.add(128);
        }
        else if (type.equals("Ground")) {
            colors.add(191);
            colors.add(172);
            colors.add(33);
        }
        else if (type.equals("Rock")) {
            colors.add(200);
            colors.add(160);
            colors.add(72);
        }
        else if (type.equals("Poison")) {
            colors.add(224);
            colors.add(144);
            colors.add(248);
        }
        else if (type.equals("Ghost")) {
            colors.add(168);
            colors.add(112);
            colors.add(248);
        }
        else if (type.equals("Fighting")) {
            colors.add(248);
            colors.add(112);
            colors.add(112);
        }
        else if (type.equals("Bug")) {
            colors.add(160);
            colors.add(200);
            colors.add(136);
        }
        else if (type.equals("Flying")) {
            colors.add(88);
            colors.add(200);
            colors.add(240);
        }
        else if (type.equals("Ice")) {
            colors.add(48);
            colors.add(216);
            colors.add(207);
        }
        else if (type.equals("Dark")) {
            colors.add(144);
            colors.add(136);
            colors.add(136);
        }
        else if (type.equals("Normal")) {
            colors.add(184);
            colors.add(184);
            colors.add(168);
        }
        else if (type.equals("Steel")) {
            colors.add(184);
            colors.add(184);
            colors.add(208);
        }
        else if (type.equals("Electric")) {
            colors.add(224);
            colors.add(224);
            colors.add(0);
        }
        else if (type.equals("Dragon")) {
            colors.add(70);
            colors.add(130);
            colors.add(180);
        }
        else if (type.equals("Fairy")) {
            colors.add(255);
            colors.add(101);
            colors.add(213);
        }
        else if (type.equals("Psychic")) {
            colors.add(248);
            colors.add(56);
            colors.add(168);
        }
        return colors;
    }
    
   
}


