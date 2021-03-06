package com.erikhalenkamp.pokemonapi;
import java.util.*;

public class PokemonCollection 
{
    private String name;
    private Boolean forward = true;
    private ArrayList<BasePokemon> pokeList;
    
      /***********************************************************************
      * Method:   PokemonCollection Constructor                              *
      * Purpose:  Constructor for the PokemonCollection object               *
      * Parameters:                                                          *
      *     name - name of the Collection                                    *
      * Return Value:  none                                                  *
      ***********************************************************************/
    
    public PokemonCollection(ArrayList<BasePokemon> pokeList)
    {
        this.name = "";
        this.pokeList = pokeList;
    }
    
    /***********************************************************************
      * Method:   PokemonCollection Constructor                              *
      * Purpose:  Constructor for the PokemonCollection object               *
      * Parameters:                                                          *
      *     none                                                             *
      * Return Value:  none                                                  *
      ***********************************************************************/
    
    public PokemonCollection()
    {
        this.name = "";
        this.pokeList = new ArrayList<BasePokemon>();
    }
    
    /***********************************************************************
      * Method:   getPokemon(int)                                            *
      * Purpose:  Grabs a specific Pokemon at the specified index            *
      * Parameters:                                                          *
      *     index - index number of the collection                           *
      * Return Value:  BasePokemon - the pokemon in question                 *
      ***********************************************************************/
    
    public BasePokemon getPokemon(int index)  
    {
        return this.pokeList.get(index);
    }
    
    /***********************************************************************
      * Method:   getSize()                                                  *
      * Purpose:  Finds the size of the collection                           *
      * Parameters:                                                          *
      *     none                                                             *
      * Return Value: size of the collection                                 *
      ***********************************************************************/
    
    public int getSize()
    {
        return this.pokeList.size();
    }
    
    /***********************************************************************
      * Method:   printSummary()                                             *
      * Purpose:  Prints all Pokemon in the collection - more detailed       *
      * Parameters:                                                          *
      *     none                                                             *
      * Return Value: none                                                   *
      ***********************************************************************/
    
    public void printCollection()
    {
        System.out.println("\nCollection: "+this.name+"\n");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < this.pokeList.size(); i++)
        {
            System.out.println((i+1)+": "+this.getPokemon(i).toString());
        }
        System.out.println(this.pokeList.size()+" in collection.");
        System.out.println("--------------------------------------------");
    }
    
    /***********************************************************************
      * Method:   printCollection()                                          *
      * Purpose:  Prints all Pokemon in the collection                       *
      * Parameters:                                                          *
      *     none                                                             *
      * Return Value: none                                                   *
      ***********************************************************************/
    
    public void printSummary()
    {
        for (int i = 0; i < this.pokeList.size(); i++)
        {
            System.out.println(this.getPokemon(i).description());
        }
    }
    
    /***********************************************************************
      * Method:   addPokemon  ()                                             *
      * Purpose:  adds a Pokemon to the end of the collection                *
      * Parameters:                                                          *
      *     newMon - new Pokemon to be added                                 *
      * Return Value: none                                                   *
      ***********************************************************************/
    
    public void addPokemon(BasePokemon newMon)
    {
        this.pokeList.add(newMon);
    }
    
    /***********************************************************************
      * Method:   getCollection()                                            *
      * Purpose:  returns the collection                                     *
      * Parameters:                                                          *
      *     none                                                             *
      * Return Value: this.PokeList - the collection                         *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> getCollection()
    {
        return this.pokeList;
    }
    
    /***********************************************************************
      * Method:   searchByName(String)                                       *
      * Purpose:  returns all pokemon matching the name specified            *
      * Parameters:                                                          *
      *     name - searching for name                                        *
      * Return Value: searchList - refined list based on search terms        *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> searchByName (String name)
    {
        
        ArrayList<BasePokemon> searchList = new ArrayList<BasePokemon>();
        for (BasePokemon pokemon : this.pokeList) {
            if (pokemon.getName().contains(name))
                searchList.add(pokemon);
        }
        return searchList;
    }
    
    public BasePokemon searchOneByName(String name) {
        for (BasePokemon pokemon : this.pokeList) {
            if (name.equals(pokemon.getName()))
                return pokemon;
        }
        return null;
    }
    
    /***********************************************************************
      * Method:   searchByType(String)                                       *
      * Purpose:  returns all pokemon matching the type specified            *
      * Parameters:                                                          *
      *     type - searching for type                                        *
      * Return Value: searchList - refined list based on search terms        *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> searchByType (String type)
    {
        ArrayList<BasePokemon> searchList = new ArrayList<BasePokemon>();
        for (BasePokemon pokemon : pokeList) {
            if (pokemon.getType1().equals(type) || pokemon.getType2().equals(type))
                searchList.add(pokemon);
        }
        return searchList;
    }
    
      /***********************************************************************
      * Method:   searchByType(String, String)                               *
      * Purpose:  returns all pokemon matching the types specified           *
      * Parameters:                                                          *
      *     type - searching for either type                                 *
      *     type2 - searching for either type                                *
      * Return Value: searchList - refined list based on search terms        *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> searchByType(String type, String type2)
    {
        ArrayList<BasePokemon> searchList = new ArrayList<BasePokemon>();
        for (BasePokemon pokemon : this.pokeList) {
            if (pokemon.getType1().equals(type) && pokemon.getType2().equals(type2))
                searchList.add(pokemon);
            if (pokemon.getType1().equals(type2) && pokemon.getType2().equals(type))
                searchList.add(pokemon);
        }
        return searchList;
    }
    
      /***********************************************************************
      * Method:   sortByBST(ArrayList<BasePokemon>)                          *
      * Purpose:  sorts the inputted list by BST                             *
      * Parameters:                                                          *
      *     list - list to be sorted                                         *
      * Return Value: lesser - refined list sorted properly                  *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> sortByBST(ArrayList<BasePokemon> list) {
        ArrayList<BasePokemon> lesser = new ArrayList<BasePokemon>();
        ArrayList<BasePokemon> greater = new ArrayList<BasePokemon>();
        if (list.size() >= 1) {
            BasePokemon pivot = list.get(list.size()-1);
            for (int i = 0; i < list.size()-1; i++) {
                if (!this.forward) {
                    if (list.get(i).baseStatTotal() > pivot.baseStatTotal())
                        lesser.add(list.get(i));    
                    else
                        greater.add(list.get(i));   
                }
                else
                {
                    if (list.get(i).baseStatTotal() < pivot.baseStatTotal())
                        lesser.add(list.get(i));    
                    else
                        greater.add(list.get(i));   
                }
            }
            lesser = sortByBST(lesser);
            greater = sortByBST(greater);
            lesser.add(pivot);
            lesser.addAll(greater);
            return lesser;
        }
        return list;
    }
    
      /***********************************************************************
      * Method:   sortByDexNum(ArrayList<BasePokemon>)                       *
      * Purpose:  sorts the inputted list by dex number                      *
      * Parameters:                                                          *
      *     list - list to be sorted                                         *
      * Return Value: lesser - refined list sorted properly                  *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> sortByDexNum(ArrayList<BasePokemon> list) {
        ArrayList<BasePokemon> lesser = new ArrayList<BasePokemon>();
        ArrayList<BasePokemon> greater = new ArrayList<BasePokemon>();
        if (list.size() >= 1) {
            BasePokemon pivot = list.get(list.size()-1);
            for (int i = 0; i < list.size()-1; i++) {
                if (!this.forward) {
                    if (list.get(i).getInstanceNum() > pivot.getInstanceNum())
                        lesser.add(list.get(i));    
                    else
                        greater.add(list.get(i));   
                }
                else
                {
                    if (list.get(i).getInstanceNum() < pivot.getInstanceNum())
                        lesser.add(list.get(i));    
                    else
                        greater.add(list.get(i));   
                }
            }
            lesser = sortByDexNum(lesser);
            greater = sortByDexNum(greater);
            lesser.add(pivot);
            lesser.addAll(greater);
            return lesser;
        }
        
        return list;
    }
    
      /***********************************************************************
      * Method:   sortByAlpha(ArrayList<BasePokemon>)                        *
      * Purpose:  sorts the inputted list by alphabetical order              *
      * Parameters:                                                          *
      *     list - list to be sorted                                         *
      * Return Value: lesser - refined list sorted properly                  *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> sortByAlpha(ArrayList<BasePokemon> list) {
        ArrayList<String> alphaList = new ArrayList<String>();
        ArrayList<BasePokemon> returnList = new ArrayList<BasePokemon>();
        for (BasePokemon pokemon : list)
            alphaList.add(pokemon.getName());
        Collections.sort(alphaList);
        for (int j=0;j<alphaList.size();j++) {
            for (int i=0;i<list.size();i++) {
                if (list.get(i).getName().equals(alphaList.get(j))) {
                    returnList.add(list.get(i));
                    continue;
                }
            }
        }
        if (!this.forward)
            Collections.reverse(returnList);
        return returnList;
    }
    
      /***********************************************************************
      * Method:   sortByBST(ArrayList<BasePokemon>)                          *
      * Purpose:  sorts the inputted list in reverse order                   *
      * Parameters:                                                          *
      *     list - list to be sorted                                         *
      * Return Value: lesser - refined list sorted properly                  *
      ***********************************************************************/
    
    public ArrayList<BasePokemon> reverseList(ArrayList<BasePokemon> list) {
        Collections.reverse(list);
        if (this.forward)
            this.forward = false;
        else
            this.forward = true;
        return list;
    }
}